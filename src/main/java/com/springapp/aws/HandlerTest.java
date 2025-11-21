package com.springapp.aws;

import java.util.HashMap;
import java.util.Map;

public class HandlerTest {

	public static void main(String[] args) {
		// Create a fake event payload
		Map<String, Object> event = new HashMap<>();
		event.put("name", "Devyesh");
		event.put("age", 25);
		event.put("city", "Hyderabad");

		// Instantiate handler
		Handler handler = new Handler();

		// Invoke Lambda like AWS does
		String response = handler.handleRequest(event, null);

		System.out.println("Lambda Response:");
		System.out.println(response);
	}

}
