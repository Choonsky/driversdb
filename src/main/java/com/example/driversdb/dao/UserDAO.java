package com.example.driversdb.dao;

import com.example.driversdb.ConnectionManager;
import com.example.driversdb.entity.User;

import java.sql.*;

public class UserDAO
{
    static Connection conn = null;
    static ResultSet rs = null;

    public static User login(User user) {

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
                System.out.println("Такой пользователь не зарегистрирован!");
                user.setValid(false);
            } else {
                String fullname = rs.getString("fullname");
                System.out.println("Вошёл пользователь " + fullname);
                user.setFullname(fullname);
                user.setValid(true);
            }
        } catch (Exception e) {
            System.out.println("Аутентификация не удалась, исключение: " + e);
        }

        return user;

    }
}