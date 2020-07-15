package com.thoughtworks.basic.currency;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockTest {
    @Test
    public void should_return_one_stock_message_when_build_message_given_one_stock() {
        //given
        Stock ibmStock = new Stock("IBM", 1000, 25);

        //when
        String message = ibmStock.buildMessage();

        //then
        assertEquals(message, "IBM 1000股 25美元/股 小计:25000美元");
    }
}
