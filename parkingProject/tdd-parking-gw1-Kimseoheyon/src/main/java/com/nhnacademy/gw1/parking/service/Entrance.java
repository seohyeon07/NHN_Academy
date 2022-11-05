package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import java.time.Clock;
import java.time.LocalDateTime;

public class Entrance {

    // 스캔 - 차량 번호, 들어온 시간, 차가 들어갈 수 있나?
    private final Clock clock;

    public Entrance(Clock clock) {
        this.clock = clock;
    }


    // 차량이 스캔 될 때 주차 및 들어온 시간 저장
    public EntranceMeta scan(Car car) {
        return new EntranceMeta(car, LocalDateTime.now(clock));

    }

}
