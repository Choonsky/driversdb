package com.example.driversdb.entity;

import java.util.Objects;

/**
 * Car Model Object.
 *
 * @author Stanislav Nemirovsky
 */

public class Car {

    private int id;
    private String type;
    private String model;
    private String plate;
    private int driverId;

    public Car() {}

    public Car(int id, String type, String model, String plate, int driverId) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.plate = plate;
        this.driverId = driverId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getDriverId() == car.getDriverId() &&
                getType().equals(car.getType()) &&
                Objects.equals(getModel(), car.getModel()) &&
                getPlate().equals(car.getPlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getModel(), getPlate(), getDriverId());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", driverId=" + driverId +
                '}';
    }
}
