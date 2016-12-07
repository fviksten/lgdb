package com.lgdb;

import java.sql.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-12-05.
 */
public interface AdminRepository {
    void saveCountry(String countryName);
    List<Country> getCountries ();

    void saveCompany(Company company);
}
