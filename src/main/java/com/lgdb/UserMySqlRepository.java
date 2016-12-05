package com.lgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) throws SQLException {

        System.out.println("Inside saveUser:");

        //Hash password:
        user.setPassword(passwordEncoder.encode(user.getPassword()));

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

    @Override
    public boolean userExists(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id " +
                     "FROM Users WHERE username = ?")) {
            ps.setString(1, user.getUsername());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("no user found!");
                    return false;
                }else{
                    System.out.println("user found: ");
                    System.out.println(rs.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Här är något väldigt fel: ...");
        return true;
    }

}
