package com.springapp.javabasics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MultiThreadingDemo {

	public static void main(String[] args) throws Exception {

	    System.out.println("===== Java Multithreading Demo =====");

	    threadClassDemo();       // Using Thread class
	    runnableInterfaceDemo(); // Using Runnable interface
	    executorServiceDemo();   // Using ExecutorService
	}

	
	// --- Method 1: Using Thread class ---
	static void threadClassDemo() {
	    System.out.println("\n--- Thread Class Example ---");

	    Thread t1 = new Thread() {
	        @Override
	        public void run() {
	            System.out.println("Thread-1 running...");
	        }
	    };

	    Thread t2 = new Thread() {
	        @Override
	        public void run() {
	            System.out.println("Thread-2 running...");
	        }
	    };

	    t1.start();
	    t2.start();
	}



	// --- Method 2: Using Runnable interface ---
	static void runnableInterfaceDemo() {
	    System.out.println("\n--- Runnable Interface Example ---");

	    Runnable task1 = () -> System.out.println("Runnable Task-1 executed");
	    Runnable task2 = () -> System.out.println("Runnable Task-2 executed");

	    new Thread(task1).start();
	    new Thread(task2).start();
	}



	// --- Method 3: Using ExecutorService ---
	static void executorServiceDemo() throws Exception {
	    System.out.println("\n--- ExecutorService Example ---");

	    ExecutorService executor = Executors.newFixedThreadPool(2);

	    executor.submit(() -> System.out.println("Executor Task-1"));
	    executor.submit(() -> System.out.println("Executor Task-2"));

	    executor.shutdown();
	    while (!executor.isTerminated()) { }
	}

}
