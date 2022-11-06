package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import com.nhnacademy.gw1.parking.repository.EntranceMetaRepository;
import java.time.Clock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

class ParkingSystemTest {

    ParkingSystem parkingSystem;
    Clock clock;
    ParkingLot parkingLot;
    Entrance entrance;
    Exit exit;
    Car car;
    EntranceMeta entranceMeta;

    EntranceMetaRepository entranceMetaRepository;

    @BeforeEach
    void setUp() {
        clock = Mockito.mock(Clock.class);
        parkingLot = Mockito.mock(ParkingLot.class);
        entrance = Mockito.mock(Entrance.class);
        exit = Mockito.mock(Exit.class);
        entranceMetaRepository = Mockito.mock(EntranceMetaRepository.class);
        parkingSystem = ParkingSystem.builder()
                .clock(clock)
                .parkingLot(parkingLot)
                .entrance(entrance)
                .exit(exit)
                .entranceMetaRepository(entranceMetaRepository)
                .build();
        car = Mockito.mock(Car.class);
        entranceMeta = Mockito.mock(EntranceMeta.class);

    }

    @Test
    @DisplayName("주차장 시스템 주차 성공")
    void parkingSystem_parking_car_success() {

        InOrder inOrder = Mockito.inOrder(entrance, parkingLot, entranceMetaRepository);

        Mockito.when(entrance.scan(car)).thenReturn(entranceMeta);

        parkingSystem.enterCar(car);

        inOrder.verify(entrance).scan(car);
        inOrder.verify(parkingLot).parkingCar(car);
        inOrder.verify(entranceMetaRepository).save(entranceMeta);

    }

    @Test
    @DisplayName("주차장 시스템 출차 성공")
    void parkingSystem_leave_car_success() {

        InOrder inOrder = Mockito.inOrder(entranceMetaRepository, exit, parkingLot,
                entranceMetaRepository);

        Mockito.when(entranceMetaRepository.getByCar(car)).thenReturn(entranceMeta);

        parkingSystem.exitCar(car);

        inOrder.verify(entranceMetaRepository).getByCar(car);
        inOrder.verify(exit).pay(entranceMeta);
        inOrder.verify(parkingLot).leave(car);
        inOrder.verify(entranceMetaRepository).remove(car);
    }

}