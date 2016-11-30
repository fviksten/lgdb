package com.lgdb;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016-11-29.
 */
public interface UserRepository {

    void saveUser(User user) throws SQLException;
    boolean userExists(User user);
}
