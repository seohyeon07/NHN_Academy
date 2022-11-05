package com.nhnacademy.gw1.parking.service;


import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import com.nhnacademy.gw1.parking.entity.User;
import com.nhnacademy.gw1.parking.util.PriceCalculator;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExitTest {

    Exit exit;
    Clock clock;
    PriceCalculator priceCalculator;
    Car car;
    User user;
    EntranceMeta entranceMeta;

    @BeforeEach
    void setUp() {
        Instant instant = Instant.parse("2022-11-05T10:15:30.00Z");
        ZoneId zoneId = ZoneId.of("GMT+9");
        clock = Clock.fixed(instant, zoneId);
        priceCalculator = Mockito.mock(PriceCalculator.class);
        entranceMeta = Mockito.mock(EntranceMeta.class);
        car = Mockito.mock(Car.class);
        user = Mockito.mock(User.class);
        exit = new Exit(clock, priceCalculator);
    }

    @Test
    @DisplayName("출구에서 주차 요금 정산")
    void payment_success() {
        LocalDateTime entranceTime = LocalDateTime.now(clock);

        Mockito.when(entranceMeta.getEntranceTime()).thenReturn(entranceTime);
        Mockito.when(priceCalculator.calculate(Mockito.eq(entranceTime),
                Mockito.any(LocalDateTime.class))).thenReturn(1000);
        Mockito.when(entranceMeta.getCar()).thenReturn(car);
        Mockito.when(car.getUser()).thenReturn(user);

        exit.pay(entranceMeta);

        Mockito.verify(priceCalculator)
                .calculate(Mockito.eq(entranceTime), Mockito.any(LocalDateTime.class));
        Mockito.verify(user).pay(Mockito.anyInt());
    }


}