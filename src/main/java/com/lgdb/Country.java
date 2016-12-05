package com.lgdb;

/**
 * Created by Administrator on 2016-12-05.
 */
public class Country {

    public int id;
    public String name;

    public Country(int countryId, String countryName) {
        this.id = countryId;
        this.name = countryName;
    }

    public int getCountryId() {
        return id;
    }

    public void setCountryId(int countryId) {
        this.id = countryId;
    }

    public String getCountryName() {
        return name;
    }

    public void setCountryName(String countryName) {
        this.name = countryName;
    }
}
