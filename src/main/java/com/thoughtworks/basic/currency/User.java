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
        Money total = calculateTotal();

        return String.format("\n-----------------------------\n" +
                "合计:%s", total.toString());
    }

    private Money calculateTotal() {
        Money total = new Money(new BigDecimal(0), CurrencyUnit.USD);
        for (Stock stock : stocks) {
            total = total.add(stock.calculateExchangedSubtotal());
        }
        return total;
    }
}
