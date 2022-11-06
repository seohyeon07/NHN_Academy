package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.util.DiscountPolicy;
import java.util.Arrays;

public class DiscountService {

    public int calculateDiscountedCharge(Car car, int charge) {
        int totalDiscountRate = getTotalDiscountRate(car);
        return charge - (charge * totalDiscountRate / 100);
    }

    private int getTotalDiscountRate(Car car) {
        return Arrays.stream(DiscountPolicy.values())
                .filter(discountPolicy -> discountPolicy.isSupport(car))
                .mapToInt(DiscountPolicy::getDiscountRate)
                .sum();
    }
}
