package com.thoughtworks.basic.currency;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class StockTest {
    @Test
    public void should_return_one_stock_message_when_build_message_given_one_stock() {
        //given
        Stock ibmStock = new Stock("IBM", 1000, new Money(new BigDecimal(25), CurrencyUnit.USD));

        //when
        String message = ibmStock.buildMessage();

        //then
        assertEquals("IBM 1000股 25美元/股 小计:25000美元", message);
    }

    @Test
    public void should_return_one_stock_message_when_build_message_given_one_stock_of_different_currency_with_USD() {
        //given
        Stock ibmStock = new Stock("Novartis", 400, new Money(new BigDecimal(150), CurrencyUnit.CHF));

        //when
        String message = ibmStock.buildMessage();

        //then
        assertEquals("Novartis 400股 150瑞士法郎/股 小计:60000瑞士法郎", message);
    }

    @Test
    public void should_return_transaction_fee_0_when_redeem_given_shares_greater_than_499() {
        //given
        Stock ibmStock = new Stock("Novartis", 1000, new Money(new BigDecimal(100), CurrencyUnit.CHF));

        //when
        Money transactionFee = ibmStock.calculateRedeemTransactionFee(500);

        //then
        assertEquals(new Money(BigDecimal.ZERO, CurrencyUnit.CHF), transactionFee);
    }
}
