package com.example.driversdb;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Connection manager object
 *
 * @author Stanislav Nemirovsky
 */

public class ConnectionManager {

    private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());

    private static Connection connection;
    private final static String url = "jdbc:postgresql://localhost:5432/driversdb";
    private final static String user = "postgres";
    private final static String password = "password";

    public static Connection Connect() {

        MyLogger.init();

        try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
            LOGGER.log( Level.SEVERE, "Не найден драйвер БД!");
            }

            try {
                connection = DriverManager.getConnection(url, user, password);
                LOGGER.log( Level.INFO, "Подключение к БД произведено!");
            } catch (SQLException e) {
                LOGGER.log( Level.SEVERE, "Не удалось подключиться к БД!" + e.toString(), e);
            }

        return connection;
    }
}