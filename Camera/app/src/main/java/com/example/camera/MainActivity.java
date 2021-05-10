package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    Camera camera;
    FrameLayout frameLayout;
    Show show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        
        camera = Camera.open();

        show = new Show(this,camera);
        frameLayout.addView(show);
    }
}