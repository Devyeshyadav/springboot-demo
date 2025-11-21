package com.springapp.javabasics;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//=========================================================
//Functional Interfaces in Java (PCFS)
//Predicate | Consumer | Function | Supplier
//=========================================================

public class FunctionalInterfaces {

	public static void main(String[] args) {
		predicateExample();
		consumerExample();
		functionExample();
		supplierExample();

	}

	// ---------------------------------------------------------
	// 1) Predicate<T>
	// - Takes one input (T)
	// - Returns boolean
	// - Used for conditional checks (filtering)
	// ---------------------------------------------------------
	static void predicateExample() {
		System.out.println("\n--- Predicate Example ---");

		Predicate<Integer> isEven = n -> n % 2 == 0;

		System.out.println("Is 10 Even? " + isEven.test(10));
		System.out.println("Is 7 Even? " + isEven.test(7));
	}

	// ---------------------------------------------------------
	// 2) Consumer<T>
	// - Takes one input (T)
	// - Returns nothing (void)
	// - Used for performing operations (printing, logging)
	// ---------------------------------------------------------
	static void consumerExample() {
		System.out.println("\n--- Consumer Example ---");

		Consumer<String> printer = msg -> System.out.println("Message: " + msg);

		printer.accept("Hello from Consumer!");
	}

	// ---------------------------------------------------------
	// 3) Function<T,R>
	// - Takes one input (T)
	// - Returns one output (R)
	// - Used for transformations (mapping)
	// ---------------------------------------------------------
	static void functionExample() {
		System.out.println("\n--- Function Example ---");

		Function<String, Integer> lengthCalculator = s -> s.length();

		System.out.println("Length of 'Java': " + lengthCalculator.apply("Java"));
	}

	// ---------------------------------------------------------
	// 4) Supplier<T>
	// - Takes no input
	// - Returns a value (T)
	// - Used to supply/generated values (random, config, defaults)
	// ---------------------------------------------------------
	static void supplierExample() {
		System.out.println("\n--- Supplier Example ---");

		Supplier<String> greetingSupplier = () -> "Hello from Supplier!";

		System.out.println(greetingSupplier.get());
	}
}