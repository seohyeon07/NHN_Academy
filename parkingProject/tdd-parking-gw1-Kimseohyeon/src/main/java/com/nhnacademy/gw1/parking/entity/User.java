package com.nhnacademy.gw1.parking.entity;

public class User {

    private int amount;
    private boolean paycoMember;

    public User(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void pay(int charge) {
        if (amount < charge) {
            throw new IllegalStateException("Not have enough money!");
        }
        amount -= charge;
    }

    public boolean isPaycoMember() {
        return paycoMember;
    }

    public void joinPaycoMember() {
        if (this.paycoMember) {
            throw new IllegalStateException("Already PayMember!");
        }
        paycoMember = true;
    }
}
