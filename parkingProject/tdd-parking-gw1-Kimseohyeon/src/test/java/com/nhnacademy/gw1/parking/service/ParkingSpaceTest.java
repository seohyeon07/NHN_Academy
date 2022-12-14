package com.nhnacademy.gw1.parking.service;


import com.nhnacademy.gw1.parking.entity.Car;
import java.lang.reflect.Field;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ParkingSpaceTest {

    ParkingSpace parkingSpace;
    Car car;

    @BeforeEach
    void setUp() {
        parkingSpace = new ParkingSpace(1);
        car = Mockito.mock(Car.class);
    }

    @Test
    @DisplayName("주차 칸에 차량 주차 성공")
    void parkingSpace_parking_success() {
        Assertions.assertThatCode(() -> parkingSpace.parking(car))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("주차 칸에 차량 주차 실패")
    void parkingSpace_parking_fail() {
        parkingSpace.parking(car);

        Assertions.assertThatThrownBy(() -> parkingSpace.parking(car))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Allocated space!");
    }

    @Test
    @DisplayName("출차 성공")
    void parkingSpace_leave_success() throws NoSuchFieldException, IllegalAccessException {
        Field carField = ParkingSpace.class.getDeclaredField("car");
        carField.setAccessible(true);
        carField.set(parkingSpace, car);

        parkingSpace.leave();

        Object result = carField.get(parkingSpace);

        Assertions.assertThat(result).isNull();
    }
}