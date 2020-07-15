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
        String stocksMessage = buildStocksMessage();
        String footer = buildFooter();

        return stocksMessage + footer;
    }

    private String buildStocksMessage() {
        return stocks.stream()
                .map(Stock::buildMessage)
                .collect(Collectors.joining("\n"));
    }

    private String buildFooter() {
        BigDecimal total = calculateTotal();

        return String.format("\n-----------------------------\n" +
                "合计:%s美元", total.stripTrailingZeros().toPlainString());
    }

    private BigDecimal calculateTotal() {
        return stocks.stream()
                .map(Stock::calculateExchangedSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
