package com.thoughtworks.basic.currency;

import java.math.BigDecimal;

class Stock {
    private final String symbol;
    private final int share;
    private Money money;

    Stock(String symbol, int share, Money money) {
        this.symbol = symbol;
        this.share = share;
        this.money = money;
    }

    String buildMessage() {
        BigDecimal subTotal = calculateSubtotal();
        int value = money.getValue();
        CurrencyUnit unit = money.getUnit();

        return String.format("%s %d股 %d%s/股 小计:%s%s", symbol, share, value, unit, subTotal.stripTrailingZeros().toPlainString(), unit);
    }

    private BigDecimal calculateSubtotal() {
        return BigDecimal.valueOf(share * money.getValue());
    }

    BigDecimal calculateExchangedSubtotal() {
        return money.getExchangeRate(CurrencyUnit.USD).multiply(BigDecimal.valueOf(share * money.getValue()));
    }
}
