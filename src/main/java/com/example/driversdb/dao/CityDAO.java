package com.example.driversdb.dao;

import com.example.driversdb.ConnectionManager;
import com.example.driversdb.MyLogger;
import com.example.driversdb.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * City Model DAO Object.
 *
 * @author Stanislav Nemirovsky
 */

public class CityDAO {

    private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());

    static Connection conn = null;
    static ResultSet rs = null;

    public static ArrayList<City> findAllCities() {
        ArrayList<City> cities = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.cities ORDER BY name";
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                cities.add(new City(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return cities;
    }

    public static Optional<City> findCityById(int id) {
        Optional<City> city = Optional.empty();
        String searchQuery =
                "SELECT * FROM driversdb.cities WHERE id=" + id;
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            if (rs.next()) {
                city = Optional.of(new City(rs.getInt("id"), rs.getString("name")));
            } else {
//                System.out.println("Город с ID = " + id + " не найдён в базе!");
            }
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return city;
    }

    public static ArrayList<City> findCitiesByName(String name) {
        ArrayList<City> cities = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.cities WHERE upper(name) LIKE '%" + name.toUpperCase() + "%' ORDER BY name";
        System.out.println("searchQuery = " + searchQuery);
        try
        {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                cities.add(new City(rs.getInt("id"), rs.getString("name")));
            }
//            if (cities.size() == 0) System.out.println("Города с названием " + name + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return cities;
    }
}