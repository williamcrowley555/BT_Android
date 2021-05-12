package com.example.musicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private IBinder iBinder = new MusicBinder();
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void startMediaPlayer(Uri uri) {
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        this.mediaPlayer.start();
    }

    public void stopMediaPlayer() {
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }
}