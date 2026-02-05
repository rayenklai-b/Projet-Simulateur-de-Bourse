package com.trading;

import java.util.Random;

public class Trader implements Runnable {

    private final String name;
    private final OrderBook orderBook;
    private final Random random = new Random();

    public Trader(String name, OrderBook orderBook) {
        this.name = name;
        this.orderBook = orderBook;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {

            double currentPrice = orderBook.getPrice();

            double change = (random.nextDouble() - 0.5) * 2;
            double newPrice = currentPrice + change;

            orderBook.updatePrice(newPrice);

            System.out.println(name +
                    " saw price " + currentPrice +
                    " -> updated to " + newPrice);
        }
    }
}
