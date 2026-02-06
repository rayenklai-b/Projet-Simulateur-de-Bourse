package com.trading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        OrderBook orderBook = new OrderBook(50.0);
        
        int numberOfTraders = 50_000;
        long startTime = System.currentTimeMillis();
        
        System.out.println("Starting simulation with " + numberOfTraders + " traders...");
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            for (int i = 1; i <= numberOfTraders; i++) {
                Trader trader = new Trader("Trader-" + i, orderBook);
                executor.submit(trader);
            }
            
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("\n=== Trading Complete ===");
        System.out.println("Final price: " + orderBook.getPrice());
        System.out.println("Total traders: " + numberOfTraders);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
        System.out.println("Throughput: " + (numberOfTraders * 1000 / (endTime - startTime)) + " traders/sec");
    }
}