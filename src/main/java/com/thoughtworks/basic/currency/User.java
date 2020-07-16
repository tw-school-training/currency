package com.thoughtworks.basic.currency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class User {
    private List<Stock> stocks = new ArrayList<>();

    void buy(Stock stock) {
        stocks.add(stock);
    }

    String listAll(CurrencyUnit totalCurrencyUnit) {
        String stocksMessage = buildStocksMessage();
        String footer = buildFooter(totalCurrencyUnit);

        return stocksMessage + footer;
    }

    private String buildStocksMessage() {
        return stocks.stream()
                .map(Stock::buildMessage)
                .collect(Collectors.joining("\n"));
    }

    private String buildFooter(CurrencyUnit currencyUnit) {
        Money total = calculateTotal(currencyUnit);

        return String.format("\n-----------------------------\n" +
                "合计:%s", total.toString());
    }

    private Money calculateTotal(CurrencyUnit currencyUnit) {
        Money total = new Money(new BigDecimal(0), currencyUnit);
        for (Stock stock : stocks) {
            total = total.add(stock.calculateExchangedSubtotal(currencyUnit), currencyUnit);
        }
        return total;
    }

    String redeem(Map<Stock, Integer> redemption) {
        return redemption.keySet()
                .stream()
                .map(stock -> stock.redeem(redemption.get(stock)))
                .collect(Collectors.joining("\n"));
    }
}
