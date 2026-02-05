package com.trading;

public class OrderBook {

    private double price;

    public OrderBook(double initialPrice) {
        this.price = initialPrice;
    }

    public synchronized double getPrice() {
        return price;
    }

    public synchronized void updatePrice(double newPrice) {
        this.price = newPrice;
    }
}