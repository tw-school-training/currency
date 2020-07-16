package com.thoughtworks.basic.currency;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

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

    @Test
    public void should_return_all_redeemed_stocks_when_redeem_given_user_stocks_with_different_currency_unit() {
        //given
        User user = new User();
        Stock ibmStock = new Stock("IBM", 1000, new Money(new BigDecimal(25), CurrencyUnit.USD));
        Stock novartisStock = new Stock("Novartis", 1000, new Money(new BigDecimal(100), CurrencyUnit.CHF));
        user.buy(ibmStock);
        user.buy(novartisStock);
        Map<Stock, Integer> redemption = new LinkedHashMap<>();
        redemption.put(novartisStock, 400);
        redemption.put(ibmStock, 600);

        //when
        String redemptionMessage = user.redeem(redemption);
        String stockList = user.listAll(CurrencyUnit.USD);

        //then
        assertEquals("Redeem Novartis 400股 100瑞士法郎/股 交易费用:40瑞士法郎\n" +
                "Redeem IBM 600股 25美元/股 交易费用:0美元", redemptionMessage);
        assertEquals("IBM 400股 25美元/股 小计:10000美元\n" +
                "Novartis 600股 100瑞士法郎/股 小计:60000瑞士法郎\n" +
                "-----------------------------\n" +
                "合计:40000美元", stockList);
    }
}
