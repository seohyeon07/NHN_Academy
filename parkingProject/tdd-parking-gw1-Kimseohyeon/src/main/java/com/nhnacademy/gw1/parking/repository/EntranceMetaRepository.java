package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;

public interface EntranceMetaRepository {

    void save(EntranceMeta entranceMeta);

    void remove(Car car);

    EntranceMeta getByCar(Car car);
}
