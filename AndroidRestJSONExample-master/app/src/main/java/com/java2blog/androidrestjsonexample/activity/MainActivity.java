package com.java2blog.androidrestjsonexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.java2blog.androidrestjsonexample.R;

public class MainActivity extends AppCompatActivity {

    Button btnCountries, btnCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mapView();

            btnCountries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                    startActivity(intent);
                }
            });

            btnCurrency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
                    startActivity(intent);
                }
            });
        }

    private void mapView() {
        btnCountries = (Button) findViewById(R.id.btnCountries);
        btnCurrency = (Button) findViewById(R.id.btnCurrency);
    }
}
