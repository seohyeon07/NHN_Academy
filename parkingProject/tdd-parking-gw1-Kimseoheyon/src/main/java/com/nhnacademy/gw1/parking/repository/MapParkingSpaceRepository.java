package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.service.ParkingSpace;
import java.util.HashMap;
import java.util.Map;

public class MapParkingSpaceRepository implements ParkingSpaceRepository {

    private final static int MAX_SIZE = 10;
    private final Map<Integer, ParkingSpace> parkingSpaces = new HashMap<>();

    public MapParkingSpaceRepository() {
        for (int i = 1; i <= MAX_SIZE; i++) {
            parkingSpaces.put(i, new ParkingSpace(i));
        }
    }

    @Override
    public ParkingSpace findByCode(int code) {
        return parkingSpaces.get(code);
    }

    @Override
    public ParkingSpace findEmptyParkingSpace() {
        return parkingSpaces.values()
                .stream()
                .filter(ParkingSpace::isEmpty)
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
