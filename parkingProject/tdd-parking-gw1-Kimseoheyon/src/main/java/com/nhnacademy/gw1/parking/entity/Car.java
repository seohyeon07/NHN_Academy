package com.nhnacademy.gw1.parking.entity;

public class Car {

    private final String carNumber;

    private final User user;

    public Car(User user, String carNumber) {
        this.carNumber = carNumber;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getCarNumber() {
        return this.carNumber;
    }
}
