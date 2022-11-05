package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import com.nhnacademy.gw1.parking.util.PriceCalculator;
import java.time.Clock;
import java.time.LocalDateTime;


// 출구
public class Exit {

    private final Clock clock;

    private final PriceCalculator priceCalculator;

    // 출차시 요금 정산


    public Exit(Clock clock, PriceCalculator priceCalculator) {
        this.clock = clock;
        this.priceCalculator = priceCalculator;
    }


    // 주차 요금 계산 후 결제 완료
    public void pay(EntranceMeta entranceMeta) {

        // 주차 요금 반환...
        LocalDateTime entranceTime = entranceMeta.getEntranceTime();
        LocalDateTime leaveTime = LocalDateTime.now(clock);
        int charge = priceCalculator.calculate(entranceTime, leaveTime);

        entranceMeta.getCar().getUser().pay(charge);
    }


}
