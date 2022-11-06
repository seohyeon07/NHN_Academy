package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ParkingSpace {

    private final int code;
    private Car car;

    public void parking(Car car) {
        if (this.car != null) {
            throw new IllegalStateException("Allocated space!");
        }
        this.car = car;
    }

    public boolean isEmpty() {
        return this.car == null;
    }

    public void leave() {
        car = null;
    }
}
