package com.thoughtworks.basic.currency;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class UserTest {
    @Test
    public void should_return_all_stocks_when_list_given_user_several_stocks() {
        //given
        User user = new User();
        Stock ibmStock = new Stock("IBM", 1000, new Money(new BigDecimal(25), CurrencyUnit.USD));
        Stock geStock = new Stock("GE", 400, new Money(new BigDecimal(100), CurrencyUnit.USD));
        user.buy(ibmStock);
        user.buy(geStock);

        //when
        String stockList = user.listAll(CurrencyUnit.USD);

        //then
        assertEquals("IBM 1000股 25美元/股 小计:25000美元\n" +
                "GE 400股 100美元/股 小计:40000美元\n" +
                "-----------------------------\n" +
                "合计:65000美元", stockList);
    }

    @Test
    public void should_return_all_stocks_when_list_given_user_several_stocks_with_different_currency_unit() {
        //given
        User user = new User();
        Stock ibmStock = new Stock("IBM", 1000, new Money(new BigDecimal(25), CurrencyUnit.USD));
        Stock geStock = new Stock("Novartis", 400, new Money(new BigDecimal(150), CurrencyUnit.CHF));
        user.buy(ibmStock);
        user.buy(geStock);

        //when
        String stockList = user.listAll(CurrencyUnit.USD);

        //then
        assertEquals("IBM 1000股 25美元/股 小计:25000美元\n" +
                "Novartis 400股 150瑞士法郎/股 小计:60000瑞士法郎\n" +
                "-----------------------------\n" +
                "合计:55000美元", stockList);
    }
}
