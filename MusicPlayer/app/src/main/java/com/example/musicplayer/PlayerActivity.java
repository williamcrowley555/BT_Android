package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicplayer.service.MusicService;
import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.File;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    private boolean mIsBound;
    private MusicService musicService;

    Button btnPlay, btnNext, btnPrev, btnff, btnfr;
    TextView txtSongName, txtSongStart, txtSongStop;
    ImageView ivMusicLogo;
    SeekBar seekMusic;
    BarVisualizer visualizer;

    String sName;
    static MediaPlayer mediaPlayer;
    int position;
    ArrayList<File> mySongs;
    Thread updateSeekbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (visualizer != null) {
            visualizer.release();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

//        Bind MusicService
        Intent i = new Intent(PlayerActivity.this, MusicService.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);

        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mapView();
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            mySongs = (ArrayList) bundle.getParcelableArrayList("songs");
            position = bundle.getInt("position", 0);

            txtSongName.setSelected(true);
            Uri uri = Uri.parse(mySongs.get(position).toString());
            sName = mySongs.get(position).getName();
            txtSongName.setText(sName);

            musicService.startMediaPlayer(uri);

            mediaPlayer = musicService.getMediaPlayer();

//        Change seekbar's background color
            seekMusic.setMax(mediaPlayer.getDuration());
            seekMusic.getProgressDrawable().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.MULTIPLY);
            seekMusic.getThumb().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_IN);

//        Seek to the postion that user selected
            seekMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            });

            updateSongEndTime(txtSongStop, mediaPlayer);

//        Update song's current time on every second
            final Handler handler = new Handler();
            final int delay = 1000;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String currentTime = createTime(mediaPlayer.getCurrentPosition());
                    txtSongStart.setText(currentTime);

                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekMusic.setProgress(currentPosition);
                    handler.postDelayed(this, delay);
                }
            }, delay);

//        Visualizer
            int audioSessionId = mediaPlayer.getAudioSessionId();
            if (audioSessionId != -1) {
                visualizer.setAudioSessionId(audioSessionId);
            }

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        btnPlay.setBackgroundResource(R.drawable.ic_play);
                        mediaPlayer.pause();
                    }
                    else {
                        btnPlay.setBackgroundResource(R.drawable.ic_pause);
                        mediaPlayer.start();
                    }
                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    musicService.stopMediaPlayer();
                    position = (position + 1) % mySongs.size();

                    Uri u = Uri.parse(mySongs.get(position).toString());
                    musicService.startMediaPlayer(u);
                    mediaPlayer = musicService.getMediaPlayer();
                    sName = mySongs.get(position).getName();
                    txtSongName.setText(sName);
                    btnPlay.setBackgroundResource(R.drawable.ic_pause);
                    seekMusic.setMax(mediaPlayer.getDuration());
                    updateSongEndTime(txtSongStop, mediaPlayer);
                    startAnimation(ivMusicLogo);

                    int audioSessionId = mediaPlayer.getAudioSessionId();
                    if (audioSessionId != -1) {
                        visualizer.setAudioSessionId(audioSessionId);
                    }
                }
            });

            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    musicService.stopMediaPlayer();
                    position = (position -  1) < 0  ? mySongs.size() - 1 : position - 1;

                    Uri u = Uri.parse(mySongs.get(position).toString());
                    musicService.startMediaPlayer(u);
                    mediaPlayer = musicService.getMediaPlayer();
                    sName = mySongs.get(position).getName();
                    txtSongName.setText(sName);
                    btnPlay.setBackgroundResource(R.drawable.ic_pause);
                    seekMusic.setMax(mediaPlayer.getDuration());
                    updateSongEndTime(txtSongStop, mediaPlayer);
                    startAnimation(ivMusicLogo);

                    int audioSessionId = mediaPlayer.getAudioSessionId();
                    if (audioSessionId != -1) {
                        visualizer.setAudioSessionId(audioSessionId);
                    }
                }
            });

            btnff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000);
                    }
                }
            });

            btnfr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
                    }
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    btnNext.performClick();
                }
            });

            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService.stopMediaPlayer();
            musicService = null;
            mIsBound = false;
        }
    };

    public void startAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivMusicLogo, "rotation", 0f, 360f);
        animator.setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void updateSongEndTime(TextView textView, MediaPlayer mediaPlayer) {
        String endTime = createTime(mediaPlayer.getDuration());
        txtSongStop.setText(endTime);
    }

    public String createTime(int duration) {
        String time = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        time += min + ":";

        if (sec < 10) {
            time += "0";
        }

        time += sec;

        return time;
    }

    private void mapView() {
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnff = (Button) findViewById(R.id.btnFastForward);
        btnfr = (Button) findViewById(R.id.btnFastRewind);

        txtSongName = (TextView) findViewById(R.id.txtSongName);
        txtSongStart = (TextView) findViewById(R.id.txtMusicStart);
        txtSongStop = (TextView) findViewById(R.id.txtMusicStop);

        ivMusicLogo = (ImageView) findViewById(R.id.ivMusicLogo);

        seekMusic = (SeekBar) findViewById(R.id.seekbar);

        visualizer = (BarVisualizer) findViewById(R.id.blast);
    }
}