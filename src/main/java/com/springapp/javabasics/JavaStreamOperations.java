package com.springapp.javabasics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaStreamOperations {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 3, 6, 7, 4);

        System.out.println("===== Java Stream Operations =====");
        System.out.println("Original List: " + numbers);

        filterExample(numbers);
        mapExample(numbers);
        sortedNaturalExample(numbers);
        sortedCustomExample(numbers);
        distinctExample();
        limitExample(numbers);
        skipExample(numbers);
        countExample(numbers);
        anyMatchExample(numbers);
        allMatchExample(numbers);
        noneMatchExample(numbers);
        findFirstExample(numbers);
        findAnyExample(numbers);
        reduceExample(numbers);
        forEachExample(numbers);
        collectExample(numbers);
        streamOfExample();
    }

    // 1) filter() – keeps elements matching a condition
    // Why: Used for selecting values based on a predicate.
    // Interview: “filter() extracts elements that match a condition.”
    private static void filterExample(List<Integer> numbers) {
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("\nfilter() - Even Numbers: " + evenNumbers);
    }

    // 2) map() – transforms each element
    // Why: Converts values (e.g., number -> square, object -> field)
    // Interview: “map() transforms each element into another value.”
    private static void mapExample(List<Integer> numbers) {
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("map() - Squares: " + squares);
    }

    // 3) sorted() – natural sorting
    // Interview: “sorted() sorts stream elements in natural order.”
    private static void sortedNaturalExample(List<Integer> numbers) {
        List<Integer> sortedList = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("sorted() - Natural Sorted List: " + sortedList);
    }

    // 4) sorted(Comparator) – custom sorting
    // Interview: “sorted(Comparator) is used for custom sorting logic.”
    private static void sortedCustomExample(List<Integer> numbers) {
        List<Integer> reverseSorted = numbers.stream()
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList());
        System.out.println("sorted(Comparator) - Reverse Sorted: " + reverseSorted);
    }

    // 5) distinct() – removes duplicates
    // Interview: “distinct() filters out duplicate elements.”
    private static void distinctExample() {
        List<Integer> distinctNumbers = Arrays.asList(1, 2, 2, 3, 3, 4).stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinct() - Unique Elements: " + distinctNumbers);
    }

    // 6) limit() – takes first N elements
    // Interview: “limit() is used for pagination / restricting results.”
    private static void limitExample(List<Integer> numbers) {
        List<Integer> first3 = numbers.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("limit() - First 3 Numbers: " + first3);
    }

    // 7) skip() – skips first N elements
    // Interview: “skip() works with limit() for pagination.”
    private static void skipExample(List<Integer> numbers) {
        List<Integer> skip3 = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("skip() - Skip First 3: " + skip3);
    }

    // 8) count() – number of elements
    // Interview: “count() returns the number of elements in the stream.”
    private static void countExample(List<Integer> numbers) {
        long count = numbers.stream().count();
        System.out.println("count() - Total Count: " + count);
    }

    // 9) anyMatch() – true if ANY element matches
    // Interview: “anyMatch() returns true for at least one matching element.”
    private static void anyMatchExample(List<Integer> numbers) {
        boolean anyMatch = numbers.stream().anyMatch(n -> n > 5);
        System.out.println("anyMatch() - Any Number > 5? " + anyMatch);
    }

    // 10) allMatch() – true if ALL elements match
    // Interview: “allMatch() ensures every element matches the predicate.”
    private static void allMatchExample(List<Integer> numbers) {
        boolean allMatch = numbers.stream().allMatch(n -> n > 0);
        System.out.println("allMatch() - All Numbers > 0? " + allMatch);
    }

    // 11) noneMatch() – true if NO element matches
    // Interview: “noneMatch() checks that no element satisfies the condition.”
    private static void noneMatchExample(List<Integer> numbers) {
        boolean noneMatch = numbers.stream().noneMatch(n -> n < 0);
        System.out.println("noneMatch() - None < 0? " + noneMatch);
    }

    // 12) findFirst() – gets the first element
    // Interview: “findFirst() is deterministic—returns the first element.”
    private static void findFirstExample(List<Integer> numbers) {
        int first = numbers.stream().findFirst().orElse(-1);
        System.out.println("findFirst() - First Element: " + first);
    }

    // 13) findAny() – returns any element (useful in parallel streams)
    // Interview: “findAny() is preferred in parallel for faster retrieval.”
    private static void findAnyExample(List<Integer> numbers) {
        int any = numbers.stream().findAny().orElse(-1);
        System.out.println("findAny() - Any Element: " + any);
    }

    // 14) reduce() – aggregate to single value
    // Interview: “reduce() is used for sum, max, min, aggregation.”
    private static void reduceExample(List<Integer> numbers) {
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("reduce() - Sum: " + sum);
    }

    // 15) forEach() – terminal action on each element
    // Interview: “forEach() is used to iterate the stream.”
    private static void forEachExample(List<Integer> numbers) {
        System.out.print("forEach() - Print Numbers: ");
        numbers.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // 16) collect() – gathers stream results into List/Set/Map
    // Interview: “collect() is the most common terminal operation.”
    private static void collectExample(List<Integer> numbers) {
        List<Integer> greaterThan4 = numbers.stream()
                .filter(n -> n > 4)
                .collect(Collectors.toList());
        System.out.println("collect() - Numbers > 4: " + greaterThan4);
    }

    // 17) Stream.of() – manually create stream
    // Interview: “Stream.of() creates a stream from given values.”
    private static void streamOfExample() {
        System.out.println("Stream.of() Example:");
        List<String> words = Arrays.asList("Java", "Spring", "Streams");
        words.stream().forEach(System.out::println);
    }
}
