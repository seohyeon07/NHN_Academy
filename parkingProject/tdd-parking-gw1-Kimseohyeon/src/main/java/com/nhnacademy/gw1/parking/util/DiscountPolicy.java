package com.nhnacademy.gw1.parking.util;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiscountPolicy {

    LIGHT_CAR(50) {
        @Override
        public boolean isSupport(Car car) {
            return car.getCarType().equals(CarType.LIGHT_CAR);
        }
    },
    PAYCO(10) {
        @Override
        public boolean isSupport(Car car) {
            return car.getUser().isPaycoMember();
        }
    };

    private final int discountRate;

    public abstract boolean isSupport(Car car);
}
