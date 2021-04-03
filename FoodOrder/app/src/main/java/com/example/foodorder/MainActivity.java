package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RelativeLayout item;
    RelativeLayout item2;
    RelativeLayout item3;
    RelativeLayout item4;
    ImageButton locate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        locate = findViewById(R.id.locate);
        locate.setOnClickListener(clickLocateListener);

        item = findViewById(R.id.taco);
        item.setOnClickListener(clickItemListener);
        item2 = findViewById(R.id.pizza);
        item2.setOnClickListener(clickItemListener);
        item3 = findViewById(R.id.burito);
        item3.setOnClickListener(clickItemListener);
        item4 = findViewById(R.id.sandwich);
        item4.setOnClickListener(clickItemListener);

    }

    private final View.OnClickListener clickItemListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, OrderActivity.class);
            i.putExtra("item_id", v.getId());
            startActivity(i);
        }
    };

    private final View.OnClickListener clickLocateListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String url = "https://goo.gl/maps/t9EBkzobZ48reNdbA";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    };
}