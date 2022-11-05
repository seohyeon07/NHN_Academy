package com.nhnacademy.gw1.parking.util;

import java.time.LocalDateTime;

abstract public class PriceCalculator {

    protected final static int THIRTY_MINUTE_TO_SECONDS = 1800;
    protected final static int TEN_MINUTE_TO_SECONDS = 600;

    protected final static int BASE_CHARGE = 1000;
    protected final static int EXTRA_CHARGE = 500;

    abstract public int calculate(LocalDateTime entranceTime, LocalDateTime leaveTime);
}
