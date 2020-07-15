package com.thoughtworks.basic.currency;

class Stock {
    private final String symbol;
    private final int share;
    private final int price;

    Stock(String symbol, int share, int price) {
        this.symbol = symbol;
        this.share = share;
        this.price = price;
    }

    String buildMessage() {
        int subTotal = calculateSubtotal();
        return String.format("%s %d股 %d美元/股 小计:%d美元", symbol, share, price, subTotal);
    }

    int calculateSubtotal() {
        return share * price;
    }
}
