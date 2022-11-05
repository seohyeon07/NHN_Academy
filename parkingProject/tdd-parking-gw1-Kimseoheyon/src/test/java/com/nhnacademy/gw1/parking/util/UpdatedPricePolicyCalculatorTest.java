package com.nhnacademy.gw1.parking.util;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UpdatedPricePolicyCalculatorTest {

    PriceCalculator defaultPriceCalculator = new PriceCalculator(new UpdatedPricePolicy());


    @ParameterizedTest
    @CsvSource({
            "1800,0",
            "1801,1000",
            "3660,1500",
            "21600,15000"
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
            "1,15000",
            "2,30000",
            "3,45000"
    })
    void defaultPrice_calculator_daily_success(int days, int expectedCharge) {
        LocalDateTime baseTime = LocalDateTime.of(2022, 11, 5, 14, 0);
        LocalDateTime leaveTime = baseTime.plusDays(days);

        int result = defaultPriceCalculator.calculate(baseTime, leaveTime);

        Assertions.assertThat(result).isEqualTo(expectedCharge);

    }


}