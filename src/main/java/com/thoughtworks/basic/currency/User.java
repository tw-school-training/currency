package com.thoughtworks.basic.currency;

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
        int total = calculateTotal();

        String footer = String.format("\n-----------------------------\n" +
                "合计:%d美元", total);

        return stocksMessage + footer;
    }

    private int calculateTotal() {
        return stocks.stream().mapToInt(Stock::calculateSubtotal)
                .sum();
    }
}
