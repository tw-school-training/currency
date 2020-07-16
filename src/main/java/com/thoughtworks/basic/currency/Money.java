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

    BigDecimal getExchangeRate(CurrencyUnit toUnit) {
        if (this.unit == toUnit) {
            return new BigDecimal(1);
        }
        Rate rate = new Rate();
        return rate.findExchangeRate(this.unit, toUnit);
    }

    Money multiply(int amount) {
        return new Money(value.multiply(BigDecimal.valueOf(amount)), unit);
    }

    Money exchange() {
        return new Money(getExchangeRate(CurrencyUnit.USD).multiply(value), CurrencyUnit.USD);
    }

    Money add(Money addend) {
        return new Money(value.add(addend.value), CurrencyUnit.USD);
    }
}
