package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import com.nhnacademy.gw1.parking.util.PriceCalculator;
import java.time.Clock;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Exit {

    private final Clock clock;

    private final PriceCalculator priceCalculator;

    private final DiscountService discountService;

    public void pay(EntranceMeta entranceMeta) {

        LocalDateTime entranceTime = entranceMeta.getEntranceTime();
        LocalDateTime leaveTime = LocalDateTime.now(clock);
        int charge = priceCalculator.calculate(entranceTime, leaveTime);
        int discountedCharge = discountService.calculateDiscountedCharge(entranceMeta.getCar(),
                charge);

        entranceMeta.getCar().getUser().pay(discountedCharge);
    }
}
