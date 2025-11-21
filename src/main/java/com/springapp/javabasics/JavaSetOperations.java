package com.springapp.javabasics;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class JavaSetOperations {

	public static void main(String[] args) {

		System.out.println("===== Java Set Types =====");

		hashSetExample();
		linkedHashSetExample();
		treeSetExample();
		copyOnWriteArraySetExample();
		enumSetExample();
	}

	// ------------------------------------------------------------------
	// 1) HashSet
	// ------------------------------------------------------------------
	// * Stores unique elements (no duplicates)
	// * Backed by HashMap
	// * Does NOT maintain order (unordered)
	// * Allows one null element
	// Interview: "HashSet is used for fast lookup and unique elements."
	private static void hashSetExample() {
		System.out.println("\n--- HashSet Example ---");

		Set<Integer> set = new HashSet<>();
		set.add(3);
		set.add(1);
		set.add(2);
		set.add(2); // duplicate ignored
		set.add(null);

		System.out.println("HashSet (unordered): " + set);
	}

	// ------------------------------------------------------------------
	// 2) LinkedHashSet
	// ------------------------------------------------------------------
	// * Maintains insertion order
	// * Slower than HashSet due to linked list maintenance
	// * Allows one null element
	// Interview: "LinkedHashSet preserves insertion order."
	private static void linkedHashSetExample() {
		System.out.println("\n--- LinkedHashSet Example ---");

		Set<String> set = new LinkedHashSet<>();
		set.add("Java");
		set.add("Spring");
		set.add("Streams");
		set.add("Spring"); // duplicate ignored

		System.out.println("LinkedHashSet (insertion order): " + set);
	}

	// ------------------------------------------------------------------
	// 3) TreeSet
	// ------------------------------------------------------------------
	// * Stores elements in sorted order
	// * Does NOT allow null
	// * Red-Black Tree implementation
	// Interview: "TreeSet is used when sorted unique elements are required."
	private static void treeSetExample() {
		System.out.println("\n--- TreeSet Example ---");

		Set<Integer> set = new TreeSet<>();
		set.add(5);
		set.add(1);
		set.add(3);
		set.add(2);

		System.out.println("TreeSet (sorted): " + set);
	}

	// ------------------------------------------------------------------
	// 4) CopyOnWriteArraySet
	// ------------------------------------------------------------------
	// * Thread-safe
	// * Uses CopyOnWriteArrayList internally
	// * Best for mostly-read scenarios
	// * Slower for write operations
	// Interview: "Used in concurrent apps when iteration frequency > modification."
	private static void copyOnWriteArraySetExample() {
		System.out.println("\n--- CopyOnWriteArraySet Example ---");

		Set<String> set = new java.util.concurrent.CopyOnWriteArraySet<>();
		set.add("A");
		set.add("B");
		set.add("A"); // duplicate ignored

		System.out.println("CopyOnWriteArraySet: " + set);
	}

	// ------------------------------------------------------------------
	// 5) EnumSet
	// ------------------------------------------------------------------
	// * Only works with Enums
	// * Very fast (uses bitwise operations)
	// * Does NOT allow null values
	// Interview: "EnumSet is the best choice for enum-based collections."
	private static void enumSetExample() {
		System.out.println("\n--- EnumSet Example ---");

		EnumSet<Level> set = EnumSet.of(Level.LOW, Level.MEDIUM);

		System.out.println("EnumSet: " + set);
	}

	// Enum for EnumSet example
	private enum Level {
		LOW, MEDIUM, HIGH
	}
}
