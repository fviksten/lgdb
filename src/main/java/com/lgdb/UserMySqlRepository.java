package com.lgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Administrator on 2016-11-29.
 */
@Component
public class UserMySqlRepository implements UserRepository {

    @Autowired
    DataSource dataSource;

    @Override
    public void saveUser(User user) throws SQLException {

        System.out.println("Inside saveUser:");
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password) " +
                     "VALUES (?,?)")) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            try {
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }//End saveUser

}
