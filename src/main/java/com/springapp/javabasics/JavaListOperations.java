package com.springapp.javabasics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class JavaListOperations {

	public static void main(String[] args) {

		System.out.println("===== Java List Types =====");

		arrayListExample();
		linkedListExample();
		vectorExample();
		arraysAsListExample();
		listOfExample();
		listSortingExample();
	}

	// ------------------------------------------------------------------
	// 1) ArrayList
	// ------------------------------------------------------------------
	// * Dynamic array-based implementation
	// * Fast random access (O(1))
	// * Slower inserts/deletes in the middle (shifting)
	// * Not thread-safe
	// Interview: "Most commonly used List due to speed & flexibility."
	private static void arrayListExample() {
		System.out.println("\n--- ArrayList Example ---");

		List<String> list = new ArrayList<>();
		list.add("Java");
		list.add("Spring");
		list.add("Hibernate");
		list.add("Spring"); // duplicates allowed

		System.out.println("ArrayList: " + list);
	}

	// ------------------------------------------------------------------
	// 2) LinkedList
	// ------------------------------------------------------------------
	// * Doubly-linked list implementation
	// * Fast inserts/deletes at ends (O(1))
	// * Slower random access (O(n))
	// Interview: "Use LinkedList when frequent add/remove."
	private static void linkedListExample() {
		System.out.println("\n--- LinkedList Example ---");

		List<Integer> list = new LinkedList<>();
		list.add(10);
		list.add(20);
		list.add(30);

		((LinkedList<Integer>) list).addFirst(5); // LinkedList feature
		((LinkedList<Integer>) list).addLast(40);

		System.out.println("LinkedList: " + list);
	}

	// ------------------------------------------------------------------
	// 3) Vector
	// ------------------------------------------------------------------
	// * Synchronized (thread-safe)
	// * Slower than ArrayList
	// * Legacy class
	// Interview: "Avoid Vector unless thread safety is mandatory."
	private static void vectorExample() {
		System.out.println("\n--- Vector Example ---");

		Vector<String> vector = new Vector<>();
		vector.add("A");
		vector.add("B");
		vector.add("C");

		System.out.println("Vector: " + vector);
	}

	// ------------------------------------------------------------------
	// 4) Arrays.asList()
	// ------------------------------------------------------------------
	// * Returns a fixed-size list backed by an array
	// * Cannot add/remove elements
	// * set() allowed
	// Interview: "Arrays.asList gives a fixed-size view."
	private static void arraysAsListExample() {
		System.out.println("\n--- Arrays.asList Example ---");

		List<String> list = Arrays.asList("one", "two", "three");

		// list.add("four"); // UnsupportedOperationException

		System.out.println("Arrays.asList (fixed-size): " + list);
	}

	// ------------------------------------------------------------------
	// 5) List.of() (Java 9+)
	// ------------------------------------------------------------------
	// * Immutable list (unmodifiable)
	// * No nulls allowed
	// * Very fast, memory efficient
	// Interview: "List.of() creates immutable lists."
	private static void listOfExample() {
		System.out.println("\n--- List.of Example ---");

		List<String> list = List.of("A", "B", "C");

		// list.add("D"); // UnsupportedOperationException

		System.out.println("List.of (immutable): " + list);
	}

	// ------------------------------------------------------------------
	// 6) Sorting a List
	// ------------------------------------------------------------------
	// * Collections.sort() for natural order
	// * Comparator for reverse or custom sorting
	// Interview: "List sorting is done using Collections or Streams."
	private static void listSortingExample() {
		System.out.println("\n--- List Sorting Example ---");

		List<String> list = new ArrayList<>(Arrays.asList("sunny", "aavula", "devyesh", "yadav"));

		// Sort ascending
		Collections.sort(list);
		System.out.println("Sorted ASC: " + list);

		// Sort descending
		Collections.sort(list, Collections.reverseOrder());
		System.out.println("Sorted DESC: " + list);

		// Stream-based sorting
		List<String> streamSorted = list.stream().sorted().toList();
		System.out.println("Stream Sorted: " + streamSorted);
	}
}
