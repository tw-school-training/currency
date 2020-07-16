package com.thoughtworks.basic.currency;

import java.math.BigDecimal;

class Stock {
    private final String symbol;
    private final int share;
    private Money money;
    private int REDEEM_TRANSACTION_FEE_REQUIED_MAX_SHARE = 499;

    Stock(String symbol, int share, Money money) {
        this.symbol = symbol;
        this.share = share;
        this.money = money;
    }

    String buildMessage() {
        Money subTotal = calculateSubtotal();

        return String.format("%s %d股 %s/股 小计:%s", symbol, share, money.toString(), subTotal.toString());
    }

    private Money calculateSubtotal() {
        return money.multiply(share);
    }

    Money calculateExchangedSubtotal(CurrencyUnit currencyUnit) {
        return calculateSubtotal().exchange(currencyUnit);
    }

    Money calculateRedeemTransactionFee(int share) {
        if(share > REDEEM_TRANSACTION_FEE_REQUIED_MAX_SHARE) {
            return new Money(BigDecimal.ZERO, money.getUnit());
        }
        return null;
    }
}
