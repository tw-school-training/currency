package com.thoughtworks.basic.currency;

import java.math.BigDecimal;

class Stock {
    private final String symbol;
    private int share;
    private Money money;
    private int REDEEM_TRANSACTION_FEE_REQUIRED_MAX_SHARE = 499;
    private BigDecimal REDEEM_TRANSACTION_FEE_RATIO = BigDecimal.valueOf(0.001);

    Stock(String symbol, int share, Money money) {
        this.symbol = symbol;
        this.share = share;
        this.money = money;
    }

    String buildMessage() {
        Money subTotal = calculateSubtotal(share);

        return String.format("%s %d股 %s/股 小计:%s", symbol, share, money.toString(), subTotal.toString());
    }

    Money calculateExchangedSubtotal(CurrencyUnit currencyUnit) {
        return calculateSubtotal(share).exchange(currencyUnit);
    }

    String redeem(int share) {
        updateShare(share);
        Money transactionFee = calculateRedeemTransactionFee(share);

        return String.format("Redeem %s %d股 %s/股 交易费用:%s", symbol, share, money.toString(), transactionFee.toString());
    }

    private Money calculateSubtotal(int share) {
        return money.multiply(share);
    }

    private Money calculateRedeemTransactionFee(int redeemShare) {
        if(redeemShare > REDEEM_TRANSACTION_FEE_REQUIRED_MAX_SHARE) {
            return new Money(BigDecimal.ZERO, money.getUnit());
        }
        return calculateSubtotal(redeemShare).multiply(REDEEM_TRANSACTION_FEE_RATIO);
    }

    private void updateShare(int redeemedShare) {
        share -= redeemedShare;
    }
}
