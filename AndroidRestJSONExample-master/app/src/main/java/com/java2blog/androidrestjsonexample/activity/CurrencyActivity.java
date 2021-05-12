package com.java2blog.androidrestjsonexample.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.java2blog.androidrestjsonexample.adapter.CountryAdapter;
import com.java2blog.androidrestjsonexample.asyncTask.ReadCountryJSON;
import com.java2blog.androidrestjsonexample.asyncTask.ReadCurrencyConverterRSS;
import com.java2blog.androidrestjsonexample.model.Country;
import com.java2blog.androidrestjsonexample.CustomCountryList;
import com.java2blog.androidrestjsonexample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class CurrencyActivity extends AppCompatActivity {

    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private EditText etAmount;
    private TextView tvResult;
    private Button btnSubmit;
    List<Country> countryList = new ArrayList<>();
    ArrayAdapter adapter;
    String JSONPath = "http://api.geonames.org/countryInfoJSON?username=aporter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        mapView();

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, new ArrayList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFromCurrency.setAdapter(adapter);
        spinnerToCurrency.setAdapter(adapter);

        new ReadCountryJSON(adapter, countryList).execute(JSONPath);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerFromCurrency.getSelectedItem().equals(spinnerToCurrency.getSelectedItem())) {
                    Toast.makeText(CurrencyActivity.this, "Please select the country you want to convert", Toast.LENGTH_SHORT).show();
                    return;
                }

                String amountText = etAmount.getText().toString().trim();
                if(amountText.length() == 0)
                {
                    Toast.makeText(CurrencyActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                Double amount = Double.valueOf(amountText);

                Integer spinnerFromPosition = spinnerFromCurrency.getSelectedItemPosition();
                Integer spinnerToPosition = spinnerToCurrency.getSelectedItemPosition();

                String countryCurrencyFrom = countryList.get(spinnerFromPosition).getCurrencyCode();
                String countryCurrencyTo = countryList.get(spinnerToPosition).getCurrencyCode();

                new ReadCurrencyConverterRSS(tvResult, amount).execute(getRSSPath(countryCurrencyFrom, countryCurrencyTo));
            }
        });
    }

    private String getRSSPath(String countryCurrencyFrom, String countryCurrencyTo) {
        return "https://" + countryCurrencyFrom.toLowerCase() + ".fxexchangerate.com/"+ countryCurrencyTo.toLowerCase() + ".xml";
    }

    private void mapView() {
        spinnerFromCurrency = (Spinner) findViewById(R.id.spinnerFromCurrency);
        spinnerToCurrency = (Spinner) findViewById(R.id.spinnerToCurrency);
        etAmount = (EditText) findViewById(R.id.etAmount);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }
}
