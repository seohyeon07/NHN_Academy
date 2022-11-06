package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.service.ParkingSpace;

public interface ParkingSpaceRepository {

    ParkingSpace findByCode(int code);

    ParkingSpace findEmptyParkingSpace();

    ParkingSpace findByCar(Car car);
}
