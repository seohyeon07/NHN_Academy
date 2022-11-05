package com.nhnacademy.gw1.parking.entity;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class UserTest {

    User user;

    int amount;

    @BeforeEach
    void setUp() {
        amount = 3000;
        user = new User(amount);
    }

    @Test
    @DisplayName("유저 생성 성공")
    void user_create_success() {

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("주차 요금 정산 성공")
    void payment_success() {

        user.pay(1000);

        Assertions.assertThat(user.getAmount()).isEqualTo(2000);
    }

    @Test
    @DisplayName("주차 요금 정산 실패")
    void payment_fail() {

        Assertions.assertThatThrownBy(() -> user.pay(4000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Not have enough money!");
    }


}