package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import java.time.Clock;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Entrance {

    private final Clock clock;

    public EntranceMeta scan(Car car) {
        if (car.getCarType().equals(CarType.LARGE_CAR)) {
            throw new IllegalArgumentException("Can not enter carType : " + car.getCarType());
        }
        return new EntranceMeta(car, LocalDateTime.now(clock));
    }
}
