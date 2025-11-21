package com.springapp.javabasics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Shared resource class
class Counter {
    private int count = 0;

    // synchronized ensures thread-safe increment
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

// Worker task (Runnable)
class CounterTask implements Runnable {
    private Counter counter;

    public CounterTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();  // shared resource access
        }
    }
}

public class MultiThreadingDemo {

    public static void main(String[] args) {

        System.out.println("=== Java Multithreading Demo ===");

        Counter counter = new Counter();

        // Using ExecutorService instead of manually creating threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Submit 4 tasks (each increments counter 1000 times)
        for (int i = 0; i < 4; i++) {
            executor.submit(new CounterTask(counter));
        }

        // Shutdown executor properly
        executor.shutdown();

        // Wait for all threads to finish
        while (!executor.isTerminated()) {
            // wait
        }

        System.out.println("Final Counter Value = " + counter.getCount());
        System.out.println("Expected Value = 4000");
        System.out.println("=== End ===");
    }
}
