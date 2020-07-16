package com.thoughtworks.basic.currency;

import java.math.BigDecimal;
import java.util.Objects;

class Money {
    private BigDecimal value;
    private CurrencyUnit unit;
    private Rate rate;

    Money(BigDecimal value, CurrencyUnit unit) {
        this.value = value;
        this.unit = unit;
        rate = new Rate();
    }

    Money multiply(int multiplier) {
        BigDecimal formattedMultiplier = BigDecimal.valueOf(multiplier);
        return multiply(formattedMultiplier);
    }

    Money multiply(BigDecimal multiplier) {
        return new Money(value.multiply(multiplier), unit);
    }

    Money exchange(CurrencyUnit toUnit) {
        return new Money(getExchangeRate(toUnit).multiply(value), toUnit);
    }

    Money add(Money addend, CurrencyUnit currencyUnit) {
        return new Money(value.add(addend.value), currencyUnit);
    }

    CurrencyUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value.stripTrailingZeros().toPlainString() + unit.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value.stripTrailingZeros().equals(money.value.stripTrailingZeros()) &&
                unit == money.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    private BigDecimal getExchangeRate(CurrencyUnit toUnit) {
        if (unit == toUnit) {
            return new BigDecimal(1);
        }
        return rate.findExchangeRate(unit, toUnit);
    }
}
