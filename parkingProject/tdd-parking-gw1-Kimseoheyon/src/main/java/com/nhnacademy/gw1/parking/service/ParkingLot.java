package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.repository.ParkingSpaceRepository;

public class ParkingLot {

    private final ParkingSpaceRepository parkingSpaceRepository;

    public ParkingLot(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    public void parkingCar(Car car) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findEmptyParkingSpace();
        parkingSpace.parking(car); // 주차 완료
    }

    public void leave(Car car) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findByCar(car);
        parkingSpace.leave();
    }
}
