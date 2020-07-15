package com.thoughtworks.basic.currency;

enum CurrencyUnit {
    USD{
        @Override
        public String toString() {
            return "美元";
        }
    }, CHF {
        @Override
        public String toString() {
            return "瑞士法郎";
        }
    }
}
