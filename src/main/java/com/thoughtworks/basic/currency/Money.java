package com.thoughtworks.basic.currency;

import java.math.BigDecimal;

class Money {
    private BigDecimal value;
    private CurrencyUnit unit;

    Money(BigDecimal value, CurrencyUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return value.stripTrailingZeros().toPlainString() + unit.toString();
    }

    private BigDecimal getExchangeRate(CurrencyUnit toUnit) {
        if (unit == toUnit) {
            return new BigDecimal(1);
        }
        Rate rate = new Rate();
        return rate.findExchangeRate(unit, toUnit);
    }

    Money multiply(int amount) {
        return new Money(value.multiply(BigDecimal.valueOf(amount)), unit);
    }

    Money exchange(CurrencyUnit toUnit) {
        return new Money(getExchangeRate(toUnit).multiply(value), toUnit);
    }

    Money add(Money addend, CurrencyUnit currencyUnit) {
        return new Money(value.add(addend.value), currencyUnit);
    }
}
