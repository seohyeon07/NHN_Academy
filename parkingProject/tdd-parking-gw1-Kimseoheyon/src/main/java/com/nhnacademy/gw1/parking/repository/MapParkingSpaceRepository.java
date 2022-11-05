package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.service.ParkingSpace;
import java.util.HashMap;
import java.util.Map;

public class MapParkingSpaceRepository implements ParkingSpaceRepository {

    private final int MAX_SIZE = 10;
    private final Map<Integer, ParkingSpace> parkingSpaces = new HashMap<>();

    public MapParkingSpaceRepository() {
        for (int i = 1; i <= MAX_SIZE; i++) {
            parkingSpaces.put(i, new ParkingSpace(i));
        }
    }

    @Override
    public ParkingSpace findEmptyParkingSpace() {
        // 차량 주차.. 공간이 있으면 저장, or 예외
        return parkingSpaces.values()
                .stream()
                .filter(v -> v.isEmpty())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Full ParkingLot!"));
    }

    @Override
    public ParkingSpace findByCar(Car car) {
        return parkingSpaces.values()
                .stream()
                .filter(space -> space.getCar().equals(car))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found car!"));
    }
}
