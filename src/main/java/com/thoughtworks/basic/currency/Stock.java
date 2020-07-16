package com.thoughtworks.basic.currency;

import java.math.BigDecimal;

class Stock {
    private final String symbol;
    private int share;
    private Money money;
    private int REDEEM_TRANSACTION_FEE_REQUIED_MAX_SHARE = 499;
    private BigDecimal REDEEM_TRANSACTION_FEE_RATIO = BigDecimal.valueOf(0.001);

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

    private Money calculateRedeemTransactionFee(int share) {
        if(share > REDEEM_TRANSACTION_FEE_REQUIED_MAX_SHARE) {
            return new Money(BigDecimal.ZERO, money.getUnit());
        }
        return calculateRedeemSubtotal(share).multiply(REDEEM_TRANSACTION_FEE_RATIO);
    }

    private Money calculateRedeemSubtotal(int share) {
        return money.multiply(share);
    }

    String redeem(int share) {
        updateShare(share);
        Money transactionFee = calculateRedeemTransactionFee(share);

        return String.format("Redeem %s %d股 %s/股 交易费用:%s", symbol, share, money.toString(), transactionFee.toString());
    }

    private void updateShare(int redeemedShare) {
        share -= redeemedShare;
    }
}
