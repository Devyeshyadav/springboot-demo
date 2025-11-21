package com.springapp.javabasics;

import java.util.*;

public class JavaMapOperations {

    public static void main(String[] args) {

        System.out.println("===== Java Map Types =====");

        hashMapExample();
        linkedHashMapExample();
        treeMapExample();
        hashtableExample();
        concurrentHashMapExample();
        weakHashMapExample();
        identityHashMapExample();
    }

    // ------------------------------------------------------------
    // 1. HashMap
    // ------------------------------------------------------------
    // * Stores key-value pairs
    // * Unordered (no insertion order)
    // * Allows one null key & multiple null values
    // * Not synchronized (not thread-safe)
    public static void hashMapExample() {
        System.out.println("\n--- HashMap Example ---");

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);

        System.out.println("HashMap (unordered): " + map);
    }

    // ------------------------------------------------------------
    // 2. LinkedHashMap
    // ------------------------------------------------------------
    // * Maintains insertion order
    // * Allows null key and null values
    // * Slightly slower than HashMap due to order maintenance
    public static void linkedHashMapExample() {
        System.out.println("\n--- LinkedHashMap Example ---");

        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("A", 10);
        map.put("C", 30);
        map.put("B", 20);

        System.out.println("LinkedHashMap (insertion order): " + map);
    }

    // ------------------------------------------------------------
    // 3. TreeMap
    // ------------------------------------------------------------
    // * Stores keys in sorted order
    // * Does NOT allow null keys
    // * Implements NavigableMap (floor, ceiling, etc.)
    public static void treeMapExample() {
        System.out.println("\n--- TreeMap Example ---");

        Map<String, Integer> map = new TreeMap<>();
        map.put("C", 10);
        map.put("A", 30);
        map.put("B", 20);

        System.out.println("TreeMap (sorted order): " + map);
    }

    // ------------------------------------------------------------
    // 4. Hashtable
    // ------------------------------------------------------------
    // * Legacy class (older than HashMap)
    // * Thread-safe (synchronized)
    // * Does NOT allow null key or null values
    public static void hashtableExample() {
        System.out.println("\n--- Hashtable Example ---");

        Map<String, Integer> map = new Hashtable<>();
        map.put("A", 10);
        map.put("B", 20);

        System.out.println("Hashtable (thread-safe): " + map);
    }

    // ------------------------------------------------------------
    // 5. ConcurrentHashMap
    // ------------------------------------------------------------
    // * Thread-safe & high performance
    // * No locking of entire map (segment locking)
    // * Does NOT allow null keys or null values
    public static void concurrentHashMapExample() {
        System.out.println("\n--- ConcurrentHashMap Example ---");

        Map<String, Integer> map = new java.util.concurrent.ConcurrentHashMap<>();
        map.put("A", 10);
        map.put("B", 20);

        System.out.println("ConcurrentHashMap: " + map);
    }

    // ------------------------------------------------------------
    // 6. WeakHashMap
    // ------------------------------------------------------------
    // * Keys are weak references
    // * Entries removed once key is garbage collected
    // * Useful for caches
    public static void weakHashMapExample() {
        System.out.println("\n--- WeakHashMap Example ---");

        Map<Object, String> map = new WeakHashMap<>();
        Object key1 = new Object();
        map.put(key1, "Value1");

        System.out.println("WeakHashMap before GC: " + map);

        key1 = null; // eligible for garbage collection
        System.gc();

        try { Thread.sleep(100); } catch (Exception ignored) {}

        System.out.println("WeakHashMap after GC (key may be removed): " + map);
    }

    // ------------------------------------------------------------
    // 7. IdentityHashMap
    // ------------------------------------------------------------
    // * Compares keys using '==' instead of .equals()
    // * Rare use : when identity comparison is needed
    public static void identityHashMapExample() {
        System.out.println("\n--- IdentityHashMap Example ---");

        Map<String, Integer> map = new IdentityHashMap<>();

        String a1 = new String("A");
        String a2 = new String("A"); // different object

        map.put(a1, 10);
        map.put(a2, 20);

        System.out.println("IdentityHashMap (compares by reference): " + map);
    }
}
