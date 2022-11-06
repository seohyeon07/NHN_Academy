package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.repository.ParkingSpaceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParkingLot {

    private final ParkingSpaceRepository parkingSpaceRepository;

    public void parkingCar(Car car) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findEmptyParkingSpace();
        parkingSpace.parking(car);
    }

    public void parkingCar(Car car, int code) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findByCode(code);
        parkingSpace.parking(car);
    }

    public void leave(Car car) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findByCar(car);
        parkingSpace.leave();
    }
}
