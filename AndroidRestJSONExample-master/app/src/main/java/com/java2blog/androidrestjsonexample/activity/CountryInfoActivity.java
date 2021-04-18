package com.java2blog.androidrestjsonexample.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.java2blog.androidrestjsonexample.R;
import com.java2blog.androidrestjsonexample.adapter.CountryAdapter;
import com.java2blog.androidrestjsonexample.asyncTask.ReadCountryInfoJSON;
import com.java2blog.androidrestjsonexample.asyncTask.ReadCountryJSON;
import com.java2blog.androidrestjsonexample.model.Country;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CountryInfoActivity extends AppCompatActivity {

    private ImageView ivCountryFlag;
    private TextView tvCountryName;
    private TextView tvPopulation;
    private TextView tvArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);

        mapView();

        Intent intent = getIntent();
        Country country = (Country) intent.getSerializableExtra("country");
        String path = "https://img.geonames.org/flags/x/" + country.getCountryCode().toLowerCase() + ".gif";

        new ReadCountryInfoJSON(country, ivCountryFlag, tvCountryName, tvPopulation, tvArea).execute(path);
    }

    private void mapView() {
        ivCountryFlag = (ImageView) findViewById(R.id.countryFlag);
        tvCountryName = (TextView) findViewById(R.id.tvCountryName);
        tvPopulation = (TextView) findViewById(R.id.tvPopulation);
        tvArea = (TextView) findViewById(R.id.tvArea);
    }

}
