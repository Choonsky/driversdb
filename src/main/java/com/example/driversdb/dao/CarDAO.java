package com.example.driversdb.dao;

import com.example.driversdb.ConnectionManager;
import com.example.driversdb.MyLogger;
import com.example.driversdb.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Car Model DAO Object.
 *
 * @author Stanislav Nemirovsky
 */

public class CarDAO {

    private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());

    static Connection conn = null;
    static ResultSet rs = null;

    public static ArrayList<Car> findAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.cars ORDER BY type, model";
        try {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("type"), rs.getString("model"),
                        rs.getString("plate"), rs.getInt("id_driver")));
            }
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return cars;
    }

    public static Optional<Car> findCarById(int id) {
        Optional<Car> car = Optional.empty();
        String searchQuery =
                "SELECT * from driversdb.cars where id=" + id;
        try {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            if (rs.next()) {
                car = Optional.of(new Car(rs.getInt("id"), rs.getString("type"), rs.getString("model"),
                        rs.getString("plate"), rs.getInt("id_driver")));
            } else {
//                System.out.println("Автомобиль с ID = " + id + " не найдён в базе!");
            }
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return car;
    }

    public static ArrayList<Car> findCarsByPlate(String plate) {
        ArrayList<Car> cars = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.cars WHERE upper(plate) LIKE '%" + plate.toUpperCase() + "%' ORDER BY type, model";
        try {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("type"), rs.getString("model"),
                        rs.getString("plate"), rs.getInt("id_driver")));
            }
//            if (cars.size() == 0) System.out.println("Автомобили с номером " + plate + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return cars;
    }

    public static ArrayList<Car> findCarsByDriverId(int driverId) {
        ArrayList<Car> cars = new ArrayList<>();
        String searchQuery =
                "SELECT * FROM driversdb.cars WHERE id_driver=" + driverId + " ORDER BY type, model";
        try {
            conn = ConnectionManager.Connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            while (rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("type"), rs.getString("model"),
                        rs.getString("plate"), rs.getInt("id_driver")));
            }
//            if (cars.size() == 0) System.out.println("Автомобили с ID владельца " + driverId + " не найдёны в базе!");
        } catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return cars;
    }
}
