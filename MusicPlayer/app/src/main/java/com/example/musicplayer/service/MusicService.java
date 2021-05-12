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
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("========================== ONBIND ========================");
        return iBinder;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void startMediaPlayer(MediaPlayer mediaPlayer, Uri uri) {
        this.mediaPlayer = mediaPlayer;
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        this.mediaPlayer.start();
    }

    public void stopMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
    }

    public String hello() {
        return "HELLO BINDER";
    }
}
