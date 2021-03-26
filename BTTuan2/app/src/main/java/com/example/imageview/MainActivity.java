package com.example.imageview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bt1);

//        BT2
//        setContentView(R.layout.bt2);
//        final Button callButton = (Button) findViewById(R.id.call_btn);
//        final EditText phoneNumber = (EditText) findViewById(R.id.phone_input);
//
//        callButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber.getText()));
//                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(callIntent);
//            }
//        });

//        BT3
//        setContentView(R.layout.bt3);
//        final Button dateButton = (Button) findViewById(R.id.date_btn);
//        final AlertDialog alert = new AlertDialog.Builder(this).create();
//        dateButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Date today = new Date();
//                String message = "Today is: " + today.toLocaleString();
//                alert.setMessage(message);
//                alert.show();
//            }
//        });

//        BT5
//        setContentView(R.layout.bt5);
    }
}