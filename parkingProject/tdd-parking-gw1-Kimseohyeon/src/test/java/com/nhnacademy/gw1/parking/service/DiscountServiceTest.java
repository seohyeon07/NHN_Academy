package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DiscountServiceTest {

    DiscountService discountService;
    Car car;
    User user;

    int charge;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();
        car = Mockito.mock(Car.class);
        user = Mockito.mock(User.class);
        charge = 10000;
    }


    @Test
    @DisplayName("할인이 없을 경우")
    void no_discount_case() {
        Mockito.when(car.getCarType()).thenReturn(CarType.NORMAL_CAR);
        Mockito.when(car.getUser()).thenReturn(user);
        Mockito.when(user.isPaycoMember()).thenReturn(false);

        int result = discountService.calculateDiscountedCharge(car, charge);

        Assertions.assertThat(result).isEqualTo(charge);
    }

    @Test
    @DisplayName("경차 할인 경우")
    void lightCar_discount_case() {
        Mockito.when(car.getCarType()).thenReturn(CarType.LIGHT_CAR);
        Mockito.when(car.getUser()).thenReturn(user);
        Mockito.when(user.isPaycoMember()).thenReturn(false);

        int result = discountService.calculateDiscountedCharge(car, charge);

        Assertions.assertThat(result).isEqualTo(5000);
    }

    @Test
    @DisplayName("페이코회원 할인 경우")
    void payco_discount_case() {
        Mockito.when(car.getCarType()).thenReturn(CarType.NORMAL_CAR);
        Mockito.when(car.getUser()).thenReturn(user);
        Mockito.when(user.isPaycoMember()).thenReturn(true);

        int result = discountService.calculateDiscountedCharge(car, charge);

        Assertions.assertThat(result).isEqualTo(9000);
    }

    @Test
    @DisplayName("경차, 페이코 할인 경우")
    void lightCar_payco_discount_case() {
        Mockito.when(car.getCarType()).thenReturn(CarType.LIGHT_CAR);
        Mockito.when(car.getUser()).thenReturn(user);
        Mockito.when(user.isPaycoMember()).thenReturn(true);

        int result = discountService.calculateDiscountedCharge(car, charge);

        Assertions.assertThat(result).isEqualTo(4000);
    }
}