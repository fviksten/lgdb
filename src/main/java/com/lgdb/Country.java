package com.lgdb;

/**
 * Created by Administrator on 2016-12-05.
 */
public class Country {

    public int id;
    public String name;

    public Country(int id, String name) {
        this.id = id;
        this.name =name;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int countryId) {
        this.id = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
