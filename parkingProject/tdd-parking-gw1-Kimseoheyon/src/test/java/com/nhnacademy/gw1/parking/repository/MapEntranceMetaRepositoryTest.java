package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.EntranceMeta;
import java.lang.reflect.Field;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MapEntranceMetaRepositoryTest {

    MapEntranceMetaRepository mapEntranceMetaRepository;

    Car car;

    EntranceMeta entranceMeta;
    Map<Car, EntranceMeta> mapReflection;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        mapEntranceMetaRepository = new MapEntranceMetaRepository();
        entranceMeta = Mockito.mock(EntranceMeta.class);
        car = Mockito.mock(Car.class);
        Field mapField = MapEntranceMetaRepository.class.getDeclaredField("entranceMetaMap");
        mapField.setAccessible(true);
        mapReflection = (Map<Car, EntranceMeta>) mapField.get(mapEntranceMetaRepository);

    }

    @Test
    @DisplayName("입차 시간 등록 성공")
    void entranceMeta_save_success() throws IllegalAccessException {

        Mockito.when(entranceMeta.getCar()).thenReturn(car);
        mapEntranceMetaRepository.save(entranceMeta);

        Assertions.assertThat(mapReflection).containsValue(entranceMeta);

    }

    @Test
    @DisplayName("출차 완료된 차량 입차 시간 삭제")
    void entranceMeta_remove_success() {

        mapEntranceMetaRepository.remove(car);

        Assertions.assertThat(mapReflection).doesNotContainValue(entranceMeta);
    }

    @Test
    @DisplayName("입차시간 정보 확인 성공")
    void entranceTime_check_success() {

        mapReflection.put(car, entranceMeta);

        EntranceMeta result = mapEntranceMetaRepository.getByCar(car);

        Assertions.assertThat(result).isEqualTo(entranceMeta);
    }

    @Test
    @DisplayName("입차시간 정보 확인 실패")
    void entranceTime_check_fail() {

        Assertions.assertThatThrownBy(() -> mapEntranceMetaRepository.getByCar(car))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car not found!");

    }

}