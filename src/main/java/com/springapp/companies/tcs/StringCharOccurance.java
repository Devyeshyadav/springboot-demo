package com.springapp.companies.tcs;

import java.util.Map;
import java.util.stream.Collectors;

public class StringCharOccurance {

	public static void main(String[] args) {
		String str = "devyesh";

		Map<Character, Long> freq = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		System.out.println(freq);

		System.out.println(str.chars().count());

	}

}
