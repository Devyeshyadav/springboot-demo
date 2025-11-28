package com.springapp.javabasics.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SingleIntegerListOperations {

	public static void main(String[] args) {

		List<Integer> numlist = new ArrayList<>(List.of(1, 3, 4, 6, 7, 9, 2, 1));

		ascendingOrder(numlist);
		descendingOrder(numlist);
		highestvalue(numlist);
		lowestvalue(numlist);
		reverse(numlist);
		converttoSet(numlist);
		count(numlist);
		converttoArray(numlist);
		frequencyMap(numlist);
	}

	private static void ascendingOrder(List<Integer> numlist) {
		List<Integer> asc = numlist.stream().sorted().collect(Collectors.toList());
		System.out.println("Ascending Order: " + asc);
	}
	
	private static void descendingOrder(List<Integer> numlist) {
		List<Integer> dsc = numlist.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println("Descending Order: " + dsc);
	}
	
	private static void highestvalue(List<Integer> numlist) {
		int max = Collections.max(numlist);
		System.out.println("Max element is "+ max);
	}

	private static void lowestvalue(List<Integer> numlist) {
		int min = Collections.min(numlist);
		System.out.println("Max element is "+ min);
	}
	
	private static void reverse(List<Integer> numlist) {
		Collections.reverse(numlist);
		System.out.println("Reversed Order: " + numlist);
	}
	
	private static void converttoSet(List<Integer> numlist) {
	Set<Integer> set = new HashSet<>(numlist); //remove Duplicates
		System.out.println("Converted to Set : " + set);
	}
	
	private static void count(List<Integer> numlist) {
		int count = numlist.size();
		System.out.println("count is "+ count);
	}
	
	private static void converttoArray(List<Integer> numlist) {
		Integer[] arr = numlist.toArray(new Integer[0]);
		System.out.println("converttoArray is "+ arr);
	}
	
	private static void frequencyMap(List<Integer> numlist) {
		Map<Object, Long> map = numlist.stream().collect(Collectors.groupingBy(s->s,Collectors.counting()));
		System.out.println("frequencyMap is "+ map);
	}
	
}
