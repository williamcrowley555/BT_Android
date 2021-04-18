package com.java2blog.androidrestjsonexample.model;
public class Country{
    Integer id;
    String countryName;

    public Country(int i, String countryName) {
        super();
        this.id = i;
        this.countryName = countryName;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
