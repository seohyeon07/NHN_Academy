package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.service.ParkingSpace;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MapParkingSpaceRepositoryTest {

    MapParkingSpaceRepository mapParkingSpaceRepository = new MapParkingSpaceRepository();
    Map<Integer, ParkingSpace> parkingSpaces;
    ParkingSpace parkingSpace;
    Car car;

    int code = 1;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        parkingSpaces = new HashMap<>();
        parkingSpace = Mockito.mock(ParkingSpace.class);
        car = Mockito.mock(Car.class);
        parkingSpaces.put(code, parkingSpace);

        Field parkingSpacesField = MapParkingSpaceRepository.class.getDeclaredField(
                "parkingSpaces");
        parkingSpacesField.setAccessible(true);
        parkingSpacesField.set(mapParkingSpaceRepository, parkingSpaces);
    }

    @Test
    @DisplayName("원하는 주차 공간 꺼내기")
    void get_wanted_parkingSpace() {
        ParkingSpace result = mapParkingSpaceRepository.findByCode(code);

        Assertions.assertThat(result).isEqualTo(parkingSpace);
    }

    @Test
    @DisplayName("빈 주차공간 찾기 성공")
    void find_empty_space_success() {
        Mockito.when(parkingSpace.isEmpty()).thenReturn(true);

        ParkingSpace result = mapParkingSpaceRepository.findEmptyParkingSpace();

        Assertions.assertThat(result).isEqualTo(parkingSpace);
    }

    @Test
    @DisplayName("빈 주차공간 찾기 실패")
    void find_empty_space_fail() {
        Assertions.assertThatThrownBy(() -> mapParkingSpaceRepository.findEmptyParkingSpace())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Full ParkingLot!");
    }

    @Test
    @DisplayName("주차한 자리 찾기 성공")
    void find_by_car_success() {
        Mockito.when(parkingSpace.getCar()).thenReturn(car);

        ParkingSpace result = mapParkingSpaceRepository.findByCar(car);

        Assertions.assertThat(result).isEqualTo(parkingSpace);
    }

    @Test
    @DisplayName("주차한 자리 찾기 실패")
    void find_by_car_fail() {
        Car otherCar = Mockito.mock(Car.class);
        Mockito.when(parkingSpace.getCar()).thenReturn(otherCar);

        Assertions.assertThatThrownBy(() -> mapParkingSpaceRepository.findByCar(car))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not found car!");
    }
}