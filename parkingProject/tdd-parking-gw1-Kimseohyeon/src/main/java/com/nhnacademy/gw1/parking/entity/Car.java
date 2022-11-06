package com.nhnacademy.gw1.parking.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Car {

    private final User user;

    private final String carNumber;

    private final CarType carType;

    public Car(User user, String carNumber) {
        this(user, carNumber, CarType.NORMAL_CAR);
    }

    public User getUser() {
        return this.user;
    }

    public CarType getCarType() {
        return this.carType;
    }

    public String getCarNumber() {
        return this.carNumber;
    }
}
