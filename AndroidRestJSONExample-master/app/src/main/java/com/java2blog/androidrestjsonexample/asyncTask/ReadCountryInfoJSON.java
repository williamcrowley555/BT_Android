package com.java2blog.androidrestjsonexample.asyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.java2blog.androidrestjsonexample.adapter.CountryAdapter;
import com.java2blog.androidrestjsonexample.model.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ReadCountryInfoJSON extends AsyncTask<String, Void, Bitmap> {

    private Country country;
    private ImageView ivCountryFlag;
    private TextView tvCountryName;
    private TextView tvPopulation;
    private TextView tvArea;

    public ReadCountryInfoJSON(Country country, ImageView ivCountryFlag, TextView tvCountryName, TextView tvPopulation, TextView tvArea) {
        this.country = country;
        this.ivCountryFlag = ivCountryFlag;
        this.tvCountryName = tvCountryName;
        this.tvPopulation = tvPopulation;
        this.tvArea = tvArea;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmapCountryFlag = null;
        URL url = null;

        try {
            url = new URL(strings[0]);
            InputStream inputStream = url.openConnection().getInputStream();
            bitmapCountryFlag = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmapCountryFlag;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        Bitmap bitmapCountryFlag = bitmap;

        ivCountryFlag.setImageBitmap(bitmapCountryFlag);
        tvCountryName.setText(country.getCountryName());
        tvPopulation.setText(country.getPopulation().toString());
        tvArea.setText(country.getArea().toString());
    }
}
