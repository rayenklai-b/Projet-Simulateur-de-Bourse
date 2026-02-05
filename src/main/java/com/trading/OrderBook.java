package com.trading;

public class OrderBook {

    private double price;

    public OrderBook(double initialPrice) {
        this.price = initialPrice;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }
}