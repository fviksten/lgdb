package com.lgdb;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016-12-05.
 */
public class Company {

    public int id;
    public String name;
    public Date founded;
    public String defund;
    public int countryId;

    private Date dateDefund = null;

    public void setDateDefund() throws ParseException {

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = format.parse(defund);
        dateDefund = new Date(date.getTime());

    }



    public Date getDateDefund() {
        return dateDefund;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    public String getDefund() {
        return defund;
    }

    public void setDefund(String defund) {
        this.defund = defund;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
