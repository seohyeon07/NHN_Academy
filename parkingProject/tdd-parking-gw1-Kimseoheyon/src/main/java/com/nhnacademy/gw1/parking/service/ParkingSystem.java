package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import com.nhnacademy.gw1.parking.repository.EntranceMetaRepository;
import java.time.Clock;
import lombok.Builder;

@Builder
public class ParkingSystem {

    private final Clock clock;
    private final ParkingLot parkingLot;
    private final Entrance entrance;
    private final Exit exit;
    private final EntranceMetaRepository entranceMetaRepository;


    public void enterCar(Car car) {
        EntranceMeta entranceMeta = entrance.scan(car);
        parkingLot.parkingCar(car);
        entranceMetaRepository.save(entranceMeta);
    }

    public void exitCar(Car car) {
        EntranceMeta entranceMeta = entranceMetaRepository.getByCar(car);
        exit.pay(entranceMeta);
        parkingLot.leave(car);
        entranceMetaRepository.remove(car);
    }
}
