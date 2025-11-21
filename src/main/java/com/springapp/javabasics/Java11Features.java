package com.springapp.javabasics;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Java11Features
 *
 * Interview-ready comments: - Each method demonstrates a Java 11 feature. -
 * Comments explain what the feature is and a short reason why it's useful. Use
 * these short lines to answer questions in 20-30 seconds during an interview.
 */
public class Java11Features {

	public static void main(String[] args) throws Exception {

		System.out.println("===== Java 11 Features =====");
		
		stringEnhancements();
		collectionToArray();
		fileAPIDemo();
		httpClientDemo();
		varInLambda();
	}

	/**
	 * 1) String enhancements (isBlank, strip, repeat, lines)
	 *
	 * What: Java 11 added convenience methods to String: - isBlank(): checks for
	 * empty or only-whitespace strings. - strip(), stripLeading(), stripTrailing():
	 * Unicode-aware trimming. - repeat(int): repeat a string N times. - lines():
	 * returns a stream of lines from the string.
	 *
	 * Why: These make common string handling safer and more concise (less manual
	 * trimming / splitting). Interview line: "Java 11 provides useful helpers
	 * (isBlank/strip/repeat/lines) to simplify string handling and avoid manual
	 * trimming/splitting logic."
	 */
	public static void stringEnhancements() {
		String s = "  Hello Java 11  ";

		// isBlank(): true if string is empty or contains only whitespace
		System.out.println("Is blank? " + s.isBlank());

		// strip(): Unicode-aware trim (preferred over trim())
		System.out.println("Strip: '" + s.strip() + "'");
		System.out.println("Strip Leading: '" + s.stripLeading() + "'");
		System.out.println("Strip Trailing: '" + s.stripTrailing() + "'");

		// repeat(): repeat string N times
		System.out.println("Repeat: " + "Hi ".repeat(3));

		// lines(): returns a Stream<String> of lines
		System.out.println("Lines:");
		"A\nB\nC".lines().forEach(System.out::println);
	}

	/**
	 * 2) Collection.toArray(IntFunction) — simplified array conversion
	 *
	 * What: Collection.toArray accepts an IntFunction to create a typed array, e.g.
	 * String[]::new. Why: Avoids casting and makes conversion to arrays type-safe
	 * and succinct. Interview line: "Using collection.toArray(String[]::new) gives
	 * a type-safe, concise way to convert collections to arrays."
	 */
	public static void collectionToArray() {
		List<String> list = List.of("Java", "11", "Features");

		// toArray with constructor reference to create a typed array
		String[] arr = list.toArray(String[]::new);
		System.out.println("Array: " + String.join(", ", arr));
	}

	/**
	 * 3) File API additions (Files.writeString / Files.readString)
	 *
	 * What: Convenience methods to read/write small text files with a single call.
	 * Why: Reduces boilerplate (no need to create streams/writers for simple
	 * read/write). Interview line: "Files.readString and Files.writeString make
	 * simple file operations concise and readable."
	 */
	public static void fileAPIDemo() throws IOException {
		Path path = Path.of("sample.txt");

		// Write a small text file in one line
		Files.writeString(path, "Java 11 File API Demo");

		// Read the full file content in one line
		String content = Files.readString(path);
		System.out.println("File Content: " + content);
	}

	/**
	 * 4) Standard HttpClient (java.net.http.HttpClient)
	 *
	 * What: A modern HTTP client included in the JDK; supports synchronous and
	 * asynchronous requests. Why: Replaces older HttpURLConnection and removes the
	 * need for third-party simple clients for many use cases. Interview line: "Java
	 * 11 standardizes a modern HttpClient with a cleaner API and async support,
	 * making HTTP calls easier without extra libraries."
	 */
	public static void httpClientDemo() throws Exception {
		HttpClient client = HttpClient.newHttpClient();

		// Build a GET request
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get")).GET().build();

		// Send synchronously and get body as a String
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println("HTTP Status: " + response.statusCode());
		System.out.println("HTTP Body: " + response.body());
	}

	/**
	 * 5) 'var' in lambda parameters
	 *
	 * What: Java 11 allows using 'var' for lambda parameter types, enabling
	 * annotations or consistent local syntax. Note: You cannot mix var and non-var
	 * parameters in the same lambda — all parameters must either use var or none.
	 * Why: Useful when you need to add annotations to parameters or prefer
	 * consistent use of var. Interview line: "Java 11 lets you use 'var' in lambda
	 * params which helps when parameter annotations are required or to keep syntax
	 * consistent."
	 */
	public static void varInLambda() {
		List<String> names = List.of("Dev", "Yesh", "Java");

		// Using 'var' in lambda parameter (allowed in Java 11)
		names.forEach((var name) -> System.out.println("Name: " + name));

		// Example note (do not mix var with explicit types in same param list):
		// names.forEach((var a, b) -> ...); // invalid if mixing
	}
}
