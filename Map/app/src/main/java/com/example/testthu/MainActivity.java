package com.example.testthu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btncb,btnorder;
    EditText ephone,emess,ecomment;
    CheckBox id1,id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnorder = findViewById(R.id.order);
        ephone = findViewById(R.id.phone);
        emess = findViewById(R.id.mess);
        ecomment = findViewById(R.id.comment);
        id1 = findViewById(R.id.checkbox_id1);
        id2 = findViewById(R.id.checkbox_id2);
        btncb = findViewById(R.id.cb);
        btncb.setOnClickListener(clickConvertListener);
        btnorder.setOnClickListener(Bai2);
    }

    private final View.OnClickListener clickConvertListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Uri gmmIntentUri = Uri.parse("geo:10.76525062566938, 106.69666179550275?q=200+Nguyễn+Thái+Học");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
    }
    };

    private final View.OnClickListener Bai2 = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) ==
            PackageManager.PERMISSION_GRANTED)
            {
                sendMess();
            }
            else{
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},100);
            }
        }
    };

    public void sendMess()
    {
        String sphone = ephone.getText().toString().trim();
        String smess = emess.getText().toString().trim();
        String sid1 = id1.getText().toString().trim();
        String sid2 = id2.getText().toString().trim();
        String chuoi = sphone + "\n" + smess + "\n";
        if(!sphone.equals("") && !smess.equals(""))
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sphone,null,smess,null,null);
            Toast.makeText(MainActivity.this, "Send Successfully", Toast.LENGTH_SHORT).show();
            if (id1.isChecked())
            {
                ecomment.setText(chuoi+= sid1);
            }
            if (id2.isChecked())
            {
                ecomment.setText((chuoi+= sid2));
            }
            else
            {
                ecomment.setText(chuoi);
            }

        }
        else {
            Toast.makeText(MainActivity.this, "Enter Value", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            sendMess();
        }
        else {
            Toast.makeText(MainActivity.this, "Permisson Denied", Toast.LENGTH_SHORT).show();
        }
    }
}