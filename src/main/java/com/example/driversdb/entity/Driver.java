package com.example.driversdb.entity;

import java.util.Objects;

/**
 * Driver Model Object.
 *
 * @author Stanislav Nemirovsky
 */

public class Driver {

    private int id;
    private String familyName;
    private String firstName;
    private String secondName;
    private int cityId;

    public Driver() {}

    public Driver(int id, String familyName, String firstName, String secondName, int cityId) {
        this.id = id;
        this.familyName = familyName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return getCityId() == driver.getCityId() &&
                getFamilyName().equals(driver.getFamilyName()) &&
                getFirstName().equals(driver.getFirstName()) &&
                getSecondName().equals(driver.getSecondName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFamilyName(), getFirstName(), getSecondName(), getCityId());
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", familyName='" + familyName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
