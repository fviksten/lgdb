package com.lgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-12-05.
 */
@Component
public class AdminMySqlRepository implements AdminRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public void saveCountry(String countryName) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Countries (Country)" +
                     "VALUES (?)")) {
            ps.setString(1, countryName);
            try {
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//End saveCountry

    @Override
    public List<Country> getCountries() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM countries")) {
            try (ResultSet rs = ps.executeQuery()) {
                List<Country> countries = new ArrayList<>();
                while (rs.next()) countries.add(rsCountry(rs));
                return countries;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveCompany(Company company) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO companies(Name,Founded,Defund,Country_ID)" +
                     "VALUES (?,?,?,?)")) {
            ps.setString(1, company.getName());
            ps.setDate(2, company.getFounded());
            ps.setDate(3, company.getDateDefund());
            ps.setInt(4, company.getCountryId());
            try {
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Country rsCountry(ResultSet rs) throws SQLException {
        return new Country(
                rs.getInt("ID"),
                rs.getString("Country"));
    }//END getCountries.

}
