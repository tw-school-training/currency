package com.thoughtworks.basic.currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class Rate {
    private Map<CurrencyUnit, Map<CurrencyUnit, BigDecimal>> rates = new HashMap<>();

    Rate() {
        Map<CurrencyUnit, BigDecimal> toUnitAndRateMap = new HashMap<>();
        toUnitAndRateMap.put(CurrencyUnit.USD, new BigDecimal(0.5));
        rates.put(CurrencyUnit.CHF, toUnitAndRateMap);
    }

    BigDecimal findExchangeRate(CurrencyUnit fromUnit, CurrencyUnit toUnit) {
        return rates.get(fromUnit).get(toUnit);
    }
}
