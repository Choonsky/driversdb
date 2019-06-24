package com.example.driversdb.dao;

import com.example.driversdb.ConnectionManager;
import com.example.driversdb.MyLogger;
import com.example.driversdb.entity.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User Model DAO Object.
 *
 * @author Stanislav Nemirovsky
 */

public class UserDAO {

    private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());

    static Connection conn = null;
    static ResultSet rs = null;

    public static User login(User user) {

        MyLogger.init();

        Statement stmt;

        String username = user.getUsername();
        String password = user.getPassword();

        String searchQuery =
                "SELECT * FROM driversdb.users WHERE username='"
                        + username
                        + "' AND password='"
                        + password
                        + "'";
        try
        {
            conn = ConnectionManager.Connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean isResponse = rs.next();

            if (!isResponse) {
                user.setValid(false);
            } else {
                String fullname = rs.getString("fullname");
                user.setFullname(fullname);
                user.setValid(true);
            }
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }

        return user;

    }
}