package com.thoughtworks.basic.currency;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockTest {
    @Test
    public void should_return_one_stock_message_when_build_message_given_one_stock() {
        //given
        Stock ibmStock = new Stock("IBM", 1000, new Money(25, CurrencyUnit.USD));

        //when
        String message = ibmStock.buildMessage();

        //then
        assertEquals("IBM 1000股 25美元/股 小计:25000美元", message);
    }

    @Test
    public void should_return_one_stock_message_when_build_message_given_one_stock_of_different_currency_with_USD() {
        //given
        Stock ibmStock = new Stock("Novartis", 400, new Money(150, CurrencyUnit.CHF));

        //when
        String message = ibmStock.buildMessage();

        //then
        assertEquals("Novartis 400股 150瑞士法郎/股 小计:60000瑞士法郎", message);
    }
}
