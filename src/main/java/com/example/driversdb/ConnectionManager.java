package com.example.driversdb;

import java.sql.*;

public class ConnectionManager {

    private static Connection connection;
    private final static String url = "jdbc:postgresql://localhost:5432/driversdb";
    private final static String user = "postgres";
    private final static String password = "password";

    public static Connection Connect() {

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Not on the classpath!");
            }

            try {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
            } catch (SQLException e) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            }

        return connection;
    }
}