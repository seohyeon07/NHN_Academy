package com.nhnacademy.gw1.parking.entity;

import java.time.LocalDateTime;

public class EntranceMeta {

    private final Car car;

    private final LocalDateTime entranceTime;

    public EntranceMeta(Car car, LocalDateTime entranceTime) {
        this.car = car;
        this.entranceTime = entranceTime;
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getEntranceTime() {
        return entranceTime;
    }
}
