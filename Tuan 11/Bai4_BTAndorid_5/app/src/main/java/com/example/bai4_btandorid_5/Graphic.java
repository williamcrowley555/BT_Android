package com.example.bai4_btandorid_5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Graphic extends Activity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GraphicsView(this,mp));
    }

    public void onDestroy()
    {
        if(null != mp)
        {
            mp.release();
        }
        super.onDestroy();
    }
}