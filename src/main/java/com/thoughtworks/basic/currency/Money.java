package com.thoughtworks.basic.currency;

class Money {
    private final int value;
    private final CurrencyUnit unit;

    Money(int value, CurrencyUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    int getValue() {
        return value;
    }

    String getUnit() {
        return unit.toString();
    }
}
