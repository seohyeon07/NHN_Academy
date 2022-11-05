package com.nhnacademy.gw1.parking.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    Car car;
    User user;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
    }

    @Test
    @DisplayName("차 생성 성공")
    void CreateCar_success() {
        String carNumber = "12가1234";
        car = new Car(user, carNumber);

        assertThat(car).isNotNull();
        assertThat(car.getCarNumber()).isEqualTo(carNumber);

    }

}