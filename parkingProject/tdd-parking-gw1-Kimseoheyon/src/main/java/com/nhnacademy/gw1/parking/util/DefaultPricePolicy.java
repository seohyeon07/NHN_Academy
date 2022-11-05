package com.nhnacademy.gw1.parking.util;

public class DefaultPricePolicy implements PricePolicy {

    @Override
    public int getBaseCharge() {
        return 1000;
    }

    @Override
    public int getBaseSeconds() {
        return 1800;
    }

    @Override
    public int getExtraCharge() {
        return 500;
    }

    @Override
    public int getExtraSeconds() {
        return 600;
    }

    @Override
    public int getFreeSeconds() {
        return 0;
    }

    @Override
    public int getDailyMaxCharge() {
        return 10_000;
    }
}
