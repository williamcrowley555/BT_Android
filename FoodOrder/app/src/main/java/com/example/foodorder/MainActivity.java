package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RelativeLayout item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        item = findViewById(R.id.taco);
        item.setOnClickListener(clickItemListener);
    }

    private final View.OnClickListener clickItemListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, OrderActivity.class);
            i.putExtra("item_id", v.getId());
            startActivity(i);
        }
    };
}