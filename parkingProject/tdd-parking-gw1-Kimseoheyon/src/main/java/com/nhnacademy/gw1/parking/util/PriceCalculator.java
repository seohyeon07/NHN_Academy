package com.nhnacademy.gw1.parking.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PriceCalculator {

    private final PricePolicy pricePolicy;

    public PriceCalculator(PricePolicy pricePolicy) {
        this.pricePolicy = pricePolicy;
    }

    public int calculate(LocalDateTime entranceTime, LocalDateTime leaveTime) {
        int elapsedDate = calculateElapsedDate(entranceTime, leaveTime);
        if (elapsedDate > 0) {
            return elapsedDate * pricePolicy.getDailyMaxCharge();
        }

        long parkingSeconds = ChronoUnit.SECONDS.between(entranceTime, leaveTime);
        if (parkingSeconds <= pricePolicy.getFreeSeconds()) {
            return 0;
        }

        return calculateCharge(parkingSeconds);

    }

    private int calculateElapsedDate(LocalDateTime entranceTime, LocalDateTime leaveTime ) {
        int entranceDate = entranceTime.getDayOfMonth();
        int exitDate = leaveTime.getDayOfMonth();

        return exitDate - entranceDate;
    }

    private int calculateCharge(long parkingSeconds) {
        int charge = pricePolicy.getBaseCharge();

        long excessTime = parkingSeconds - pricePolicy.getFreeSeconds() - pricePolicy.getBaseSeconds();

        if (excessTime < 0) {
            return charge;
        }

        charge += excessTime / pricePolicy.getExtraSeconds() * pricePolicy.getExtraCharge();
        charge += excessTime % pricePolicy.getExtraSeconds() > 0 ? 500 : 0;

        return Math.min(charge, pricePolicy.getDailyMaxCharge());
    }
}