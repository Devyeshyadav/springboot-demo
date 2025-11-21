package com.springapp.javabasics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java08Features {

    public static void main(String[] args) {

        System.out.println("===== Java 8 Features =====");

        lambdaExpressionExample();
        functionalInterfaceExample();
        methodReferenceExample();
        streamsFilterExample();
        streamsMapExample();
        optionalExample();
        predicateExample();
        functionExample();
        consumerExample();
        forEachExample();
        defaultAndStaticMethodsExample();
        dateTimeAPIExample();

        System.out.println("===== End of Java 8 Features Demo =====");
    }

    // ---------------------------------------------------------
    // 1) Lambda Expression
    //    - Introduced to make code concise.
    //    - Removes need for anonymous classes.
    //    - Supports functional-style programming.
    // ---------------------------------------------------------
    static void lambdaExpressionExample() {
        System.out.println("\n--- Lambda Expression ---");

     // Lambda for printing a message
        Runnable message = () -> System.out.println("Lambda says Hello!");

      // Run the lambda
        message.run();
    }

    // ---------------------------------------------------------
    // 2) Functional Interface
    //    - Interface with exactly one abstract method.
    //    - Lambda expressions work ONLY with functional interfaces.
    // ---------------------------------------------------------
    static void functionalInterfaceExample() {
        System.out.println("\n--- Functional Interface ---");

        Calculator add = (a, b) -> a + b;
        System.out.println("Lambda Add: " + add.calculate(10, 20));
    }

    // ---------------------------------------------------------
    // 3) Method Reference
    //    - Shorthand syntax to call existing methods.
    //    - Improves readability by avoiding lambda boilerplate.
    // ---------------------------------------------------------
    static void methodReferenceExample() {
        System.out.println("\n--- Method Reference ---");

        List<String> names = Arrays.asList("Dev", "Ram", "Amit", "John");
        names.forEach(System.out::println);
    }

    // ---------------------------------------------------------
    // 4) Streams API – Filter
    //    - Provides functional-style operations on collections.
    //    - filter() keeps only elements matching a condition.
    //    - Enables clean, readable data processing.
    // ---------------------------------------------------------
    static void streamsFilterExample() {
        System.out.println("\n--- Streams Filter ---");

        List<Integer> numbers = Arrays.asList(5, 10, 15, 20, 25, 30);

        List<Integer> even = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Even Numbers: " + even);
    }

    // ---------------------------------------------------------
    // 5) Streams API – Map
    //    - map() transforms each element.
    //    - Used heavily in data processing pipelines.
    // ---------------------------------------------------------
    static void streamsMapExample() {
        System.out.println("\n--- Streams Map ---");

        List<Integer> numbers = Arrays.asList(5, 10, 15, 20, 25, 30);

        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println("Squares: " + squares);
    }

    // ---------------------------------------------------------
    // 6) Optional
    //    - Introduced to avoid NullPointerException.
    //    - Allows safe handling of nullable values.
    //    - Provides orElse(), isPresent(), etc.
    // ---------------------------------------------------------
    static void optionalExample() {
        System.out.println("\n--- Optional ---");

        Optional<String> optionalName = Optional.of("Java");
        System.out.println("Optional Value: " + optionalName.orElse("No Value"));

        Optional<String> empty = Optional.empty();
        System.out.println("Optional Empty: " + empty.orElse("Default"));
    }

    // ---------------------------------------------------------
    // 7) Predicate
    //    - Functional interface returning boolean.
    //    - Often used in filtering and validation.
    // ---------------------------------------------------------
    static void predicateExample() {
        System.out.println("\n--- Predicate ---");

        Predicate<Integer> greaterThan10 = x -> x > 10;
        System.out.println("Is 15 > 10? " + greaterThan10.test(15));
    }

    // ---------------------------------------------------------
    // 8) Function
    //    - Takes an input and returns an output.
    //    - Frequently used in map() operations.
    // ---------------------------------------------------------
    static void functionExample() {
        System.out.println("\n--- Function ---");

        Function<String, Integer> lengthMapper = s -> s.length();
        System.out.println("Length of 'Java8': " + lengthMapper.apply("Java8"));
    }

    // ---------------------------------------------------------
    // 9) Consumer
    //    - Functional interface that consumes input.
    //    - Returns no value (void).
    // ---------------------------------------------------------
    static void consumerExample() {
        System.out.println("\n--- Consumer ---");

        Consumer<String> printer = s -> System.out.println("Consumer: " + s);
        printer.accept("Hello Consumer");
    }

    // ---------------------------------------------------------
    // 10) forEach
    //     - Introduced for simplified iteration.
    //     - Works well with streams and lambdas.
    // ---------------------------------------------------------
    static void forEachExample() {
        System.out.println("\n--- forEach ---");

        List<Integer> numbers = Arrays.asList(5, 10, 15, 20);

        System.out.print("forEach Stream: ");
        numbers.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // ---------------------------------------------------------
    // 11) Default & Static Methods in Interface
    //     - Java 8 allows interfaces to have:
    //          * default methods (with implementation)
    //          * static methods
    //     - Helps evolve interfaces without breaking existing code.
    // ---------------------------------------------------------
    static void defaultAndStaticMethodsExample() {
        System.out.println("\n--- Default & Static Methods ---");

        MyInterface.display(); // static method

        MyInterface obj = () -> System.out.println("Abstract Method Called!");
        obj.abstractMethod();
        obj.defaultMethod(); // default method
    }

    // ---------------------------------------------------------
    // 12) Java 8 Date & Time API
    //     - Immutable, more powerful than java.util.Date.
    //     - Separate classes for date, time, and date-time:
    //         * LocalDate
    //         * LocalTime
    //         * LocalDateTime
    // ---------------------------------------------------------
    static void dateTimeAPIExample() {
        System.out.println("\n--- Date & Time API ---");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("LocalDate: " + date);
        System.out.println("LocalTime: " + time);
        System.out.println("LocalDateTime: " + dateTime);
    }
}

// Functional Interface – Only ONE abstract method allowed
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Interface demonstrating default and static methods
interface MyInterface {

    void abstractMethod();

    default void defaultMethod() {
        System.out.println("Default Method in Interface");
    }

    static void display() {
        System.out.println("Static Method in Interface");
    }
}
