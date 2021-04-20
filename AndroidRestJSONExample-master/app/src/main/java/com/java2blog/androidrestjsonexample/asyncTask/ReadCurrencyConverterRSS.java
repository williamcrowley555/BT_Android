package com.java2blog.androidrestjsonexample.asyncTask;

import android.os.AsyncTask;
import android.widget.TextView;

import com.java2blog.androidrestjsonexample.adapter.CountryAdapter;
import com.java2blog.androidrestjsonexample.model.Country;
import com.java2blog.androidrestjsonexample.parser.XMLDOMParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ReadCurrencyConverterRSS extends AsyncTask<String, Void, String> {

    private TextView tvResult;
    private Double amount;

    public ReadCurrencyConverterRSS(TextView tvResult, Double amount) {
        this.tvResult = tvResult;
        this.amount = amount;
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

        XMLDOMParser parser = new XMLDOMParser();
        Document document = parser.getDocument(s);

        NodeList nodeList = document.getElementsByTagName("item");
        Element element = (Element) nodeList.item(0);

        String description = parser.getValueCDATA(element,"description");
        Double result = amount * getBaseAmount(description);

        tvResult.setText(result.toString());
    }

    private Double getBaseAmount(String description) {
        String value = "";
        int startIndex;
        int endIndex;

        startIndex = description.indexOf("= ") + 2;
        value = description.substring(startIndex);
        endIndex = value.indexOf(" ") + startIndex + 1;
        value = description.substring(startIndex, endIndex);

        return Double.valueOf(value);
    }
}
