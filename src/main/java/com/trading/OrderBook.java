package com.trading;

import java.util.concurrent.atomic.AtomicLong;

public class OrderBook {

    private final AtomicLong priceInCents;

    public OrderBook(double initialPrice) {
        this.priceInCents = new AtomicLong((long) (initialPrice * 100));
    }

    public double getPrice() {
        return priceInCents.get() / 100.0;
    }

    public void updatePrice(double newPrice) {
        priceInCents.set((long) (newPrice * 100));
    }
}