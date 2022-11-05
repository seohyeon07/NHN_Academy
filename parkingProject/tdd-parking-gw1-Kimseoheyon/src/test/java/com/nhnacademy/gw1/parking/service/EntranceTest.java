package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EntranceTest {

    Entrance entrance;
    Clock clock;
    Car car;


    @BeforeEach
    void setUp() {
        Instant instant = Instant.parse("2022-11-05T10:15:30.00Z");
        ZoneId zoneId = ZoneId.of("GMT+9");
        clock = Clock.fixed(instant, zoneId);
        entrance = new Entrance(clock);
        car = Mockito.mock(Car.class);

    }

    @Test
    @DisplayName("주차장 입구 차량 스캔 성공")
    void entrance_scan_success() {

        EntranceMeta entranceTime = entrance.scan(car);

        Assertions.assertThat(entranceTime.getCar()).isEqualTo(car);
        Assertions.assertThat(entranceTime.getEntranceTime()).isEqualTo(LocalDateTime.now(clock));


    }

}