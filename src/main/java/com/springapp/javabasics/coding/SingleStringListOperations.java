package com.springapp.javabasics.coding;

import java.util.*;
import java.util.stream.Collectors;

public class SingleStringListOperations {

    public static void main(String[] args) {

        List<String> elist = new ArrayList<>(List.of("devyesh", "yadav", "aavula", "sunny", "j"));
        elist.add(1, "sunny");

        System.out.println("Original List: " + elist);

        lengthList(elist);
        ascendingOrder(elist);
        descendingOrder(elist);
        removeDuplicates(elist);
        longestString(elist);
        shortestString(elist);
        reverseList(elist);
        convertToSet(elist);
        convertToMap(elist);
        filterStartsWithS(elist);
        filterEndsWithH(elist);
        toUppercase(elist);
        toLowercase(elist);
        countElements(elist);
        countUniqueElements(elist);
        groupByLength(elist);
        sortByLengthAsc(elist);
        sortByLengthDesc(elist);
        anyStartsWithD(elist);
        allLengthGreaterThanOne(elist);
        findFirstElement(elist);
        joinList(elist);
        convertToArray(elist);
        removeShortNames(elist);
        frequencyMap(elist);
    }

    // ----------------------------------------------------------------------
    private static void lengthList(List<String> list) {
        List<Integer> lengthList = list.stream().map(String::length).collect(Collectors.toList());
        System.out.println("Length List: " + lengthList);
    }

    private static void ascendingOrder(List<String> list) {
        List<String> asc = list.stream().sorted().collect(Collectors.toList());
        System.out.println("Ascending Order: " + asc);
    }

    private static void descendingOrder(List<String> list) {
        List<String> desc = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("Descending Order: " + desc);
    }

    private static void removeDuplicates(List<String> list) {
        List<String> distinct = list.stream().distinct().collect(Collectors.toList());
        System.out.println("Duplicates Removed: " + distinct);
    }

    private static void longestString(List<String> list) {
        String longest = list.stream()
                .max(Comparator.comparing(String::length))
                .orElse(null);
        System.out.println("Longest String: " + longest);
    }

    private static void shortestString(List<String> list) {
        String shortest = list.stream()
                .min(Comparator.comparingInt(String::length))
                .orElse(null);
        System.out.println("Shortest String: " + shortest);
    }

    private static void reverseList(List<String> list) {
        List<String> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        System.out.println("Reversed List: " + reversed);
    }

    private static void convertToSet(List<String> list) {
        Set<String> set = new HashSet<>(list);
        System.out.println("List to Set: " + set);
    }

    private static void convertToMap(List<String> list) {
        List<String> distinct = list.stream().distinct().collect(Collectors.toList());
        Map<String, Integer> map = distinct.stream()
                .collect(Collectors.toMap(s -> s, s->s.length()));
        System.out.println("String → Length Map: " + map);
    }

    private static void filterStartsWithS(List<String> list) {
        List<String> result = list.stream()
                .filter(s -> s.startsWith("s"))
                .collect(Collectors.toList());
        System.out.println("Starts with 's': " + result);
    }

    private static void filterEndsWithH(List<String> list) {
        List<String> result = list.stream()
                .filter(s -> s.endsWith("h"))
                .collect(Collectors.toList());
        System.out.println("Ends with 'h': " + result);
    }

    private static void toUppercase(List<String> list) {
        List<String> upper = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Uppercase: " + upper);
    }

    private static void toLowercase(List<String> list) {
        List<String> lower = list.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println("Lowercase: " + lower);
    }

    private static void countElements(List<String> list) {
        System.out.println("Count: " + list.size());
    }

    private static void countUniqueElements(List<String> list) {
        System.out.println("Unique Count: " + list.stream().distinct().count());
    }

    private static void groupByLength(List<String> list) {
        Map<Integer, List<String>> grouped = list.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by Length: " + grouped);
    }

    private static void sortByLengthAsc(List<String> list) {
        List<String> asc = list.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Sorted by Length ASC: " + asc);
    }

    private static void sortByLengthDesc(List<String> list) {
        List<String> desc = list.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList());
        System.out.println("Sorted by Length DESC: " + desc);
    }

    private static void anyStartsWithD(List<String> list) {
        boolean result = list.stream().anyMatch(s -> s.startsWith("d"));
        System.out.println("Any starts with 'd': " + result);
    }

    private static void allLengthGreaterThanOne(List<String> list) {
        boolean result = list.stream().allMatch(s -> s.length() > 1);
        System.out.println("All length > 1: " + result);
    }

    private static void findFirstElement(List<String> list) {
        String first = list.stream().findFirst().orElse(null);
        System.out.println("First Element: " + first);
    }

    private static void joinList(List<String> list) {
        String joined = String.join(", ", list);
        System.out.println("Joined String: " + joined);
    }

    private static void convertToArray(List<String> list) {
        String[] arr = list.toArray(new String[0]);
        System.out.println("Array: " + Arrays.toString(arr));
    }

    private static void removeShortNames(List<String> list) {
        List<String> result = list.stream()
                .filter(s -> s.length() > 3)
                .collect(Collectors.toList());
        System.out.println("Length > 3: " + result);
    }

    private static void frequencyMap(List<String> list) {
        Map<String, Long> freq = list.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("Frequency Map: " + freq);
    }
}
