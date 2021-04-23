package com.java2blog.androidrestjsonexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.java2blog.androidrestjsonexample.model.Country;
import com.java2blog.androidrestjsonexample.R;

import java.util.List;

public class CountryAdapter extends BaseAdapter {
    private Context context;
    private Integer layout;
    private List<Country> countryList;

    public CountryAdapter(Context context, Integer layout, List<Country> countryList) {
        this.context = context;
        this.layout = layout;
        this.countryList = countryList;
    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView textViewCountryName = (TextView) convertView.findViewById(R.id.txtCountryName);

        Country country = countryList.get(position);

        textViewCountryName.setText(country.getCountryName());
        return convertView;
    }
}
