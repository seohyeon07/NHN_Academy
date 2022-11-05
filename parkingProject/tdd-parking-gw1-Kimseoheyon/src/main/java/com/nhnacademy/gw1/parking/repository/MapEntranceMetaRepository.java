package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapEntranceMetaRepository implements EntranceMetaRepository {

    private final Map<Car, EntranceMeta> entranceMetaMap = new HashMap<>();

    @Override
    public void save(EntranceMeta entranceMeta) {
        entranceMetaMap.put(entranceMeta.getCar(), entranceMeta);

    }

    @Override
    public void remove(Car car) {
        entranceMetaMap.remove(car);

    }

    @Override
    public EntranceMeta getByCar(Car car) {
        return Optional.ofNullable(entranceMetaMap.get(car))
                .orElseThrow(() -> new IllegalArgumentException("Car not found!"));
    }
}
