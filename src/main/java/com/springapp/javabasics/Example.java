package com.springapp.javabasics;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Example {

	public static void main(String[] args) {

		int[] arr = {1, 2, 4, 5, 10};

		int min = Arrays.stream(arr).min().orElse(0);
		int max = Arrays.stream(arr).max().orElse(0);

		// Convert array to Set for fast lookup
		Set<Integer> set = Arrays.stream(arr).boxed().collect(Collectors.toSet());

		List<Integer> missing = IntStream.rangeClosed(min, max)
		        .filter(num -> !set.contains(num))
		        .boxed()
		        .toList();

		System.out.println(missing);

	}
}