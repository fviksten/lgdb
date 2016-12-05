package com.lgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private Country rsCountry(ResultSet rs) throws SQLException {
        return new Country(
                rs.getInt("ID"),
                rs.getString("Country"));
    }//END getCountries.

}
