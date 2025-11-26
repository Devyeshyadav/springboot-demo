package com.springapp.javabasics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example {

	public static void main(String[] args) {

		List<String> elist = new ArrayList<>(List.of("devyesh", "yadav", "aavula", "sunny","j"));
		
		Map<Object, List<String>> map = elist.stream().collect(Collectors.groupingBy(String::length));

		System.out.println(map);
	}
}