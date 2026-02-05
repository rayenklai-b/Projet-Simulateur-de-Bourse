package com.trading;

public class Main {

    public static void main(String[] args) {

        OrderBook orderBook = new OrderBook(50.0);

        for (int i = 1; i <= 5; i++) {
            Trader trader = new Trader("Trader-" + i, orderBook);
            new Thread(trader).start();
        }
    }
}
