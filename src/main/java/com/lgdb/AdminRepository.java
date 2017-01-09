package com.lgdb;

import java.sql.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-12-05.
 */
public interface AdminRepository {
    void saveCountry(Country country);
    List<Country> getCountries ();
    Country getCountry(int id);
    void alterCountry(Country country);
    void saveCompany(Company company);
    void deleteCountry(int id);
}
