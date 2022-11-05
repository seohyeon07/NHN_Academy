package com.nhnacademy.gw1.parking.util;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DefaultPriceCalculatorTest {

    DefaultPriceCalculator defaultPriceCalculator = new DefaultPriceCalculator();


    @ParameterizedTest
    @CsvSource({
            "1800,1000",
            "1801,1500",
            "3000,2000",
            "3660,3000",
            "21600,10000"
    })
    @DisplayName("기본 요금표 결제 테스트")
    void defaultPrice_calculator_success(int sec, int expectedCharge) {
        LocalDateTime baseTime = LocalDateTime.of(2022, 11, 5, 14, 0);
        LocalDateTime leaveTime = baseTime.plusSeconds(sec);

        int result = defaultPriceCalculator.calculate(baseTime, leaveTime);

        Assertions.assertThat(result).isEqualTo(expectedCharge);
    }

    @ParameterizedTest
    @CsvSource({
            "1,10000",
            "2,20000",
            "3,30000"
    })
    void defaultPrice_calculator_daily_success(int days, int expectedCharge) {
        LocalDateTime baseTime = LocalDateTime.of(2022, 11, 5, 14, 0);
        LocalDateTime leaveTime = baseTime.plusDays(days);

        int result = defaultPriceCalculator.calculate(baseTime, leaveTime);

        Assertions.assertThat(result).isEqualTo(expectedCharge);

    }


}