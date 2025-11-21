package com.springapp.javabasics;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Example {

	public static void main(String[] args) {

		List<Integer> elist = List.of(1,3,5,7,10);
		
		int min = Collections.min(elist);
		int max = Collections.max(elist);
		

		for(int i = min;i <= max ;i++) {
			if(!elist.contains(i)) {
				System.out.println(i);
			}
		}
        
	}
}