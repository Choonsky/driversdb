package com.example.driversdb.dao;

import com.example.driversdb.ConnectionManager;
import com.example.driversdb.MyLogger;
import com.example.driversdb.entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Driver Model DAO Object.
 *
 * @author Stanislav Nemirovsky
 */

public class DriverDAO {

    private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());

    static Connection conn = null;
    static ResultSet rs = null;

    public static ArrayList<Driver> findAllDrivers() {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers ORDER BY family_name, first_name, second_name";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

    public static Optional<Driver> findDriverById(int id) {
        Optional<Driver> driver = Optional.empty();
        String searchQuery =
                "SELECT * from driversdb.drivers where id=" + id;
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            if (rs.next()) {
                driver = Optional.of(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            } else {
//                System.out.println("Водитель с ID = " + id + " не найдён в базе!");
            }
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return driver;
    }

    public static ArrayList<Driver> findDriversByCityId(int cityId) {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers WHERE id_city=" + cityId;
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
//            if (drivers.size() == 0) System.out.println("Владельцы из города с ID " + cityId + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

    public static ArrayList<Driver> findDriversByFirstName(String firstName) {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers WHERE upper(first_name) LIKE '%" + firstName.toUpperCase() + "%'";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
//            if (drivers.size() == 0) System.out.println("Владельцы с именем " + firstName + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

    public static ArrayList<Driver> findDriversByFamilyName(String familyName) {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers WHERE upper(family_name) LIKE '%" + familyName.toUpperCase() + "%'";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
//            if (drivers.size() == 0) System.out.println("Владельцы с фамилией " + familyName + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

    public static ArrayList<Driver> findDriversBySecondName(String secondName) {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers WHERE upper(second_name) LIKE '%" + secondName.toUpperCase() + "%'";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
//            if (drivers.size() == 0) System.out.println("Владельцы с отчеством " + secondName + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

    public static ArrayList<Driver> findDriversByFirstNameAndSecondName(String firstName, String secondName) {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers WHERE upper(first_name) LIKE '%" + firstName.toUpperCase() + "%' AND " +
                        "upper(second_name) LIKE '%" + secondName.toUpperCase() + "%'";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
//            if (drivers.size() == 0) System.out.println("Владельцы с именем " + firstName + " и отчеством " + secondName + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

    public static ArrayList<Driver> findDriversByFirstNameAndFamilyName(String firstName, String familyName) {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers WHERE upper(first_name) LIKE '%" + firstName.toUpperCase() + "%' AND " +
                        "upper(family_name) LIKE '%" + familyName.toUpperCase() + "%'";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
//            if (drivers.size() == 0) System.out.println("Владельцы с именем " + firstName + " и фамилией " + familyName + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

    public static ArrayList<Driver> findDriversBySecondNameAndFamilyName(String secondName, String familyName) {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers WHERE upper(second_name) LIKE '%" + secondName.toUpperCase() + "%' AND " +
                        "upper(family_name) LIKE '%" + familyName.toUpperCase() + "%'";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
//            if (drivers.size() == 0) System.out.println("Владельцы с отчеством " + secondName + " и фамилией " + familyName + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

    public static ArrayList<Driver> findDriversByFirstNameAndSecondNameAndFamilyName(String firstName, String secondName, String familyName) {
        ArrayList<Driver> drivers = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.drivers WHERE upper(first_name) LIKE '%" + firstName.toUpperCase() + "%' AND " +
                        "upper(second_name) LIKE '%" + secondName.toUpperCase() + "%' AND upper(family_name) LIKE '%" + familyName.toUpperCase() + "%'";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("id"), rs.getString("family_name"), rs.getString("first_name"),
                        rs.getString("second_name"), rs.getInt("id_city")));
            }
//            if (drivers.size() == 0) System.out.println("Владельцы с именем " + firstName + ", отчеством " + secondName +
//                        " и фамилией " + familyName + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return drivers;
    }

}
