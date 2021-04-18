package com.java2blog.androidrestjsonexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.java2blog.androidrestjsonexample.model.Country;
import com.java2blog.androidrestjsonexample.R;
import com.java2blog.androidrestjsonexample.adapter.CountryAdapter;
import com.java2blog.androidrestjsonexample.asyncTask.ReadCountryJSON;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    String JSONPath = "http://api.geonames.org/countryInfoJSON?username=aporter";
    List<Country> countryList = new ArrayList<>();
    CountryAdapter countryAdapter;
    ListView listView;

      // In case if you deploy rest web service, then use below link and replace below ip address with yours
    //http://192.168.2.22:8080/JAXRSJsonExample/rest/countries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        mapView();
        countryAdapter = new CountryAdapter(this, R.layout.row_country, countryList);
        listView.setAdapter(countryAdapter);
        new ReadCountryJSON(countryAdapter, countryList).execute(JSONPath);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CountryActivity.this, CountryInfoActivity.class);
                intent.putExtra("country", countryList.get(position));
                startActivity(intent);
            }
        });
    }

    private void mapView() {
        listView = (ListView) findViewById(R.id.list);
    }

    }
