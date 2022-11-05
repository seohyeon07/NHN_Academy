package com.nhnacademy.gw1.parking.service;


import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.repository.ParkingSpaceRepository;
import java.lang.reflect.Field;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class ParkingLotTest {

    ParkingLot parkingLot;
    ParkingSpace parkingSpace;
    ParkingSpaceRepository parkingSpaceRepository;
    Car car;

    Field parkingSpaces;

    @BeforeEach
    void setUp() {
        parkingSpaceRepository = Mockito.mock(ParkingSpaceRepository.class);
        parkingLot = new ParkingLot(parkingSpaceRepository);
        car = Mockito.mock(Car.class);
        parkingSpace = Mockito.mock(ParkingSpace.class);
    }

    @Test
    @DisplayName("주차 성공")
    void parking_success() {

        Mockito.when(parkingSpaceRepository.findEmptyParkingSpace()).thenReturn(parkingSpace);

        parkingLot.parkingCar(car); // when

        Mockito.verify(parkingSpace, Mockito.atLeastOnce()).parking(car); // then

    }

    @Test
    @DisplayName("주차 실패")
    void parking_fail() {
        Mockito.when(parkingSpaceRepository.findEmptyParkingSpace())
                .thenThrow(new IllegalStateException("Full ParkingLot!"));

        Assertions.assertThatThrownBy(() -> parkingLot.parkingCar(car))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Full ParkingLot!");

    }

    @Test
    @DisplayName("출차 성공")
    public void parkingSpace_leave_success() {
        Mockito.when(parkingSpaceRepository.findByCar(car)).thenReturn(parkingSpace);

        parkingLot.leave(car);

        Mockito.verify(parkingSpace).leave();

    }

    @Test
    @DisplayName("출차 실패")
    public void parkingSpace_leave_fail() {
        Mockito.when(parkingSpaceRepository.findByCar(car))
                .thenThrow(new IllegalArgumentException("Not found car!"));

        Assertions.assertThatThrownBy(() -> parkingLot.leave(car))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not found car!");

    }

}