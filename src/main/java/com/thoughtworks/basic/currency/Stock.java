package com.thoughtworks.basic.currency;

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
        Money subTotal = calculateSubtotal();

        return String.format("%s %d股 %s/股 小计:%s", symbol, share, money.toString(), subTotal.toString());
    }

    private Money calculateSubtotal() {
        return money.multiply(share);
    }

    Money calculateExchangedSubtotal() {
        return calculateSubtotal().exchange();
    }
}
