package com.thoughtworks.basic.currency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class User {
    private List<Stock> stocks = new ArrayList<>();

    void buy(Stock stock) {
        stocks.add(stock);
    }

    String listAll() {
        String stocksMessage = stocks.stream()
                .map(Stock::buildMessage)
                .collect(Collectors.joining("\n"));
        BigDecimal total = calculateTotal();

        String footer = String.format("\n-----------------------------\n" +
                "合计:%s美元", total.stripTrailingZeros().toPlainString());

        return stocksMessage + footer;
    }

    private BigDecimal calculateTotal() {
        return stocks.stream()
                .map(Stock::calculateExchangedSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
