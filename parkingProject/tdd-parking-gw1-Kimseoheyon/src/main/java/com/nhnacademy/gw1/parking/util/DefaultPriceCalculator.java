package com.nhnacademy.gw1.parking.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DefaultPriceCalculator extends PriceCalculator {

    private static final int DAILY_MAX_CHARGE = 10000;

    public int calculate(LocalDateTime entranceTime, LocalDateTime leaveTime) {

        int entranceDate = entranceTime.getDayOfMonth();
        int exitDate = leaveTime.getDayOfMonth();

        if (entranceDate < exitDate) {
            int totalDate = exitDate - entranceDate;

            return totalDate * DAILY_MAX_CHARGE;
        }

        int charge = BASE_CHARGE;
        int parkingSeconds = (int) ChronoUnit.SECONDS.between(entranceTime, leaveTime);

        if (parkingSeconds < THIRTY_MINUTE_TO_SECONDS) {

            return charge;
        }

        int excessTime = parkingSeconds - THIRTY_MINUTE_TO_SECONDS;

        charge += excessTime / TEN_MINUTE_TO_SECONDS * EXTRA_CHARGE;
        charge += excessTime % TEN_MINUTE_TO_SECONDS > 0 ? 500 : 0;

        return Math.min(charge, DAILY_MAX_CHARGE);

    }
}
