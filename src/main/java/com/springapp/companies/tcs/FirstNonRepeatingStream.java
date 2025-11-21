package com.springapp.companies.tcs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingStream {

	public static void main(String[] args) {
		String input = "swiss";
        Character result = firstNonRepeatingChar(input);
        System.out.println("First non-repeating character: " + result);
	}

	private static Character firstNonRepeatingChar(String input) {
		if (input == null || input.isEmpty()) {
            return null;
        }

        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
	}

}
