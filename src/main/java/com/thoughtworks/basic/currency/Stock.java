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
        int subTotal = calculateSubtotal();
        int value = money.getValue();
        String unit = money.getUnit();

        return String.format("%s %d股 %d%s/股 小计:%d%s", symbol, share, value, unit, subTotal, unit);
    }

    int calculateSubtotal() {
        return share * money.getValue();
    }
}
