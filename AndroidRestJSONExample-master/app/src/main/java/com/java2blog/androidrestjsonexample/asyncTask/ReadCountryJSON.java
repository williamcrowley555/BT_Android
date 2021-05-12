package com.java2blog.androidrestjsonexample.asyncTask;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.java2blog.androidrestjsonexample.model.Country;
import com.java2blog.androidrestjsonexample.adapter.CountryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadCountryJSON extends AsyncTask<String, Void, String> {

    private List<Country> countryList;
    private ArrayAdapter arrayAdapter;
    private CountryAdapter adapter;


    public ReadCountryJSON(ArrayAdapter arrayAdapter, List<Country> countryList) {
        this.arrayAdapter = arrayAdapter;
        this.countryList = countryList;
    }

    public ReadCountryJSON(CountryAdapter adapter, List<Country> countryList) {
        this.adapter = adapter;
        this.countryList = countryList;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }

            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray geonamesArray = jsonObject.getJSONArray("geonames");

            for (int i = 0; i < geonamesArray.length(); ++i) {
                JSONObject object = geonamesArray.getJSONObject(i);
                Integer id = i;
                String countryName = object.getString("countryName");
                String countryCode = object.getString("countryCode");
                String currencyCode = object.getString("currencyCode");
                Long population = object.getLong("population");
                Double area = object.getDouble("areaInSqKm");

                countryList.add(new Country(id, countryName, countryCode, currencyCode, population, area));
            }

            if (arrayAdapter != null) {
                List<String> countries = new ArrayList<>();

                for (Country country : countryList) {
                    countries.add(country.getCountryName());
                }

                arrayAdapter.addAll(countries);
                arrayAdapter.notifyDataSetChanged();
            } else if (adapter != null) {
                adapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
