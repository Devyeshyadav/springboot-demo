package com.springapp.javabasics;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Java17Features
 *
 * Each method demonstrates a Java 17 (or Java 14–17 era) language/library
 * feature. Comments include: - What the feature is - Why it's useful - A short
 * 20–30 second interview line you can say verbatim
 */
public class Java17Features {

	public static void main(String[] args) {

		System.out.println("===== Java 17 Features =====");
		
		patternMatchingInstanceOf();
		switchExpressionExample();
		textBlockExample();
		recordExample();
		sealedClassExample();
		randomGeneratorExample();
	}

	/**
	 * 1) Pattern Matching for instanceof
	 *
	 * What: Lets you test and cast in the same expression: "if (obj instanceof
	 * String s)". Why: Removes boilerplate casting and improves readability and
	 * safety. Interview line: "Pattern matching for instanceof lets you test and
	 * extract a typed variable in one step, avoiding manual casts and making code
	 * clearer."
	 */
	public static void patternMatchingInstanceOf() {
		Object obj = "Hello Java 17";

		// Pattern matching combines the type check and cast
		if (obj instanceof String s) {
			System.out.println("Length using pattern matching: " + s.length());
		}
	}

	/**
	 * 2) Switch Expression (refined since Java 14, stable in later releases)
	 *
	 * What: Switch can now be an expression producing a value and supports
	 * comma-separated labels and arrow syntax. Why: More concise, less error-prone
	 * (no fall-through by default), returns values directly. Interview line:
	 * "Switch expressions let you return values directly from a switch and use
	 * concise arrow labels, reducing boilerplate and accidental fall-through."
	 */
	public static void switchExpressionExample() {
		String day = "SAT";

		int result = switch (day) {
		case "MON", "TUE", "WED", "THU", "FRI" -> 1;
		case "SAT", "SUN" -> 2;
		default -> 0;
		};

		System.out.println("Switch result = " + result);
	}

	/**
	 * 3) Text Blocks (multiline string literals)
	 *
	 * What: Triple-quote string literals for multiline text (JSON, SQL, HTML,
	 * etc.). Why: Makes multiline literals readable and preserves formatting
	 * without lots of concatenation or escape sequences. Interview line: "Text
	 * blocks let you embed nicely formatted multiline strings (JSON, SQL, HTML)
	 * directly in code, which improves readability and reduces escaping."
	 */
	public static void textBlockExample() {
		String json = """
				{
				    "name": "Java17",
				    "version": 17
				}
				""";
		System.out.println("Text Block JSON:\n" + json);
	}

	/**
	 * 4) Records (compact immutable data carriers introduced in Java 16)
	 *
	 * What: Concise syntax for immutable data classes with auto-generated
	 * constructor, accessors, equals/hashCode and toString. Why: Reduces
	 * boilerplate for DTOs and encourages immutable design. Interview line:
	 * "Records provide a compact way to declare immutable data carriers where the
	 * JDK generates constructor, accessors, equals, hashCode and toString for you."
	 */
	public static void recordExample() {
		Person p = new Person("Devyesh", 30);
		System.out.println("Record: " + p.name() + " Age: " + p.age());
	}

	/**
	 * 5) Sealed Classes (finalized in Java 17)
	 *
	 * What: Restricts which classes can extend or implement a base type using
	 * `permits`. Why: Improves modeling of closed hierarchies, enables better
	 * reasoning and compiler checks (and helps pattern matching). Interview line:
	 * "Sealed classes let you control the permitted subclasses of a type, making
	 * closed hierarchies explicit and safer."
	 */
	public static void sealedClassExample() {
		Shape circle = new Circle(5);
		System.out.println("Area of circle = " + circle.area());

		Shape square = new Square(4);
		System.out.println("Area of square = " + square.area());
	}

	/**
	 * 6) New RandomGenerator API (modern RNGs in java.util.random)
	 *
	 * What: A flexible factory-based API to obtain different algorithms and
	 * generators (better than legacy Random). Why: Allows choosing high-quality
	 * algorithms and improved performance/quality for simulations and
	 * crypto-friendly RNGs where applicable. Interview line: "The new
	 * RandomGenerator API provides an extensible way to pick different PRNG
	 * algorithms via a factory and offers better control than the old
	 * java.util.Random."
	 */
	public static void randomGeneratorExample() {
		// Choose an algorithm by name (implementation-dependent availability)
		RandomGenerator rng = RandomGeneratorFactory.of("L128X256MixRandom").create();

		System.out.println("Random int: " + rng.nextInt(100));
		System.out.println("Random double: " + rng.nextDouble());
	}
}

/* ===================== Supporting types ===================== */

/**
 * Record (immutable data carrier). Auto-generates constructor, accessors,
 * equals(), hashCode(), toString().
 */
record Person(String name, int age) {
}

/**
 * Sealed class example: - `Shape` permits Circle and Square only. - Subclasses
 * are declared `final` to prevent further extension.
 */
sealed abstract class Shape permits Circle, Square {
	public abstract double area();
}

final class Circle extends Shape {
	private final double radius;

	Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return Math.PI * radius * radius;
	}
}

final class Square extends Shape {
	private final double side;

	Square(double side) {
		this.side = side;
	}

	@Override
	public double area() {
		return side * side;
	}
}
