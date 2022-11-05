package com.nhnacademy.gw1.parking.util;

public interface PricePolicy {

    int getBaseCharge();

    int getBaseSeconds();

    int getExtraCharge();

    int getExtraSeconds();

    int getFreeSeconds();

    int getDailyMaxCharge();
}
