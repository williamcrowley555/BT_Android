package com.java2blog.androidrestjsonexample.model;

import java.io.Serializable;

public class Country implements Serializable {
    private Integer id;
    private String countryName;
    private String countryCode;
    private String currencyCode;
    private Long population;
    private Double area;

    public Country() {
    }

    public Country(Integer id, String countryName, String countryCode, String currencyCode, Long population, Double area) {
        this.id = id;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.currencyCode = currencyCode;
        this.population = population;
        this.area = area;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
