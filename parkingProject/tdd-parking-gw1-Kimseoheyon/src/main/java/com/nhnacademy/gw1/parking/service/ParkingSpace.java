package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;

public class ParkingSpace {

    private final int code;
    private Car car;

    public ParkingSpace(int code) {
        this.code = code;
    }

    public void parking(Car car) {
        if (this.car != null) { // 현재 칸에 차량이 있는지 확인 후 차량 배치
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

    public Car getCar() {
        return car;
    }
}
