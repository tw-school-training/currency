package com.thoughtworks.basic.currency;

import java.math.BigDecimal;

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

    CurrencyUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + unit.toString();
    }

    BigDecimal getExchangeRate(CurrencyUnit toUnit) {
        if (this.unit == toUnit) {
            return new BigDecimal(1);
        }
        Rate rate = new Rate();
        return rate.findExchangeRate(this.unit, toUnit);
    }

    Money multiply(int amount) {
        return new Money(value * amount, unit);
    }
}
