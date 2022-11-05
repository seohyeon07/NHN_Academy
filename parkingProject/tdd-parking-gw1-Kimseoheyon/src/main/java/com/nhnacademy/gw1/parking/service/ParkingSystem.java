package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import com.nhnacademy.gw1.parking.repository.EntranceMetaRepository;
import java.time.Clock;
import lombok.Builder;

@Builder
public class ParkingSystem {

    private final Clock clock;
    private final ParkingLot parkingLot;
    private final Entrance entrance;
    private final Exit exit;
    private final EntranceMetaRepository entranceMetaRepository;
    // 입구에서 스캔메소드 - 주차 공간 확인 - 입차 시

    // 주차 시스템에서 차가 들어오면 스캔하고 주차한다.
    public void enterCar(Car car) {
        EntranceMeta entranceMeta = entrance.scan(car); // 스캔한 리턴 값 시간에 저장 하기
        parkingLot.parkingCar(car); // 파킹 후
        entranceMetaRepository.save(entranceMeta); // 저장
    }


    // 차량 출차
    public void exitCar(Car car) {
        EntranceMeta entranceMeta = entranceMetaRepository.getByCar(car);
        exit.pay(entranceMeta);
        parkingLot.leave(car);
        entranceMetaRepository.remove(car);
    }


}
