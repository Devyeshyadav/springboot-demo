package com.springapp.javabasics;

//=========================================================
//SINGLETON PATTERN (Creational Design Pattern)
//
//The Singleton Pattern ensures that a class has only ONE
//instance throughout the application and provides a global
//point of access to it.
//
//Key Characteristics:
//- Only one object is ever created.
//- Constructor is private to prevent external instantiation.
//- Access is provided via a public static method or field.
//- Often used for shared resources such as:
//   * Logging
//   * Caching
//   * Configuration settings
//   * Thread pools
//   * Connection managers
//=========================================================

public class Singleton {

	public static void main(String[] args) {

		// -----------------------------------------------------
		// main()
		// This method demonstrates how each singleton pattern
		// behaves and how objects are retrieved.
		//
		// It calls all example demonstration methods in order.
		// This lets you observe:
		// - How each singleton is accessed
		// - Whether the same instance is returned always
		// - Any differences in behavior/output
		// -----------------------------------------------------
		eagerSingletonExample();
		lazySingletonExample();
		dclSingletonExample();
		holderSingletonExample();
		enumSingletonExample();
	}

	// ---------------------------------------------------------
	// 1) EAGER INITIALIZATION SINGLETON
	//
	// - The instance is created when the class is loaded.
	// - This makes it inherently thread-safe.
	// - Downside: instance is created even if it is never used.
	//
	// This method demonstrates how the eager singleton returns
	// the same instance every time and shows that the object is
	// already created before first usage.
	// ---------------------------------------------------------
	static void eagerSingletonExample() {
		System.out.println("\n--- Eager Initialization Singleton Example ---");

		// Accessing the singleton instance
		EagerSingleton instance1 = EagerSingleton.INSTANCE;
		instance1.showMessage();

		// Calling again returns the same instance
		EagerSingleton instance2 = EagerSingleton.INSTANCE;
		System.out.println("Same instance? " + (instance1 == instance2));
	}

	// Eager Singleton nested class
	static class EagerSingleton {

		// Instance is created immediately when class is loaded
		static final EagerSingleton INSTANCE = new EagerSingleton();

		// Private constructor prevents outside object creation
		private EagerSingleton() {
			System.out.println("(EagerSingleton) Constructor executed");
		}

		void showMessage() {
			System.out.println("Hello from EagerSingleton!");
		}
	}

	// ---------------------------------------------------------
	// 2) LAZY INITIALIZATION (SYNCHRONIZED) SINGLETON
	//
	// - Instance is created only when needed (first call).
	// - Thread-safe because method is synchronized.
	// - Downside: synchronization makes it slower for high traffic.
	//
	// This method demonstrates lazy creation and confirms that
	// repeated calls return the same instance.
	// ---------------------------------------------------------
	static void lazySingletonExample() {
		System.out.println("\n--- Lazy Initialization (synchronized) Singleton Example ---");

		LazySingleton a = LazySingleton.getInstance();
		a.showMessage();

		LazySingleton b = LazySingleton.getInstance();
		System.out.println("Same instance? " + (a == b));
	}

	static class LazySingleton {

		private static LazySingleton instance;

		// Private constructor to prevent object creation
		private LazySingleton() {
			System.out.println("(LazySingleton) Constructor executed");
		}

		// Synchronized → ensures thread safety
		public static synchronized LazySingleton getInstance() {
			if (instance == null) {
				instance = new LazySingleton(); // created only once
			}
			return instance;
		}

		void showMessage() {
			System.out.println("Hello from LazySingleton!");
		}
	}

	// ---------------------------------------------------------
	// 3) DOUBLE-CHECKED LOCKING (DCL) SINGLETON
	//
	// - More efficient than synchronized method.
	// - Uses double-checking:
	// * First check: without lock (fast)
	// * Second check: inside synchronized block
	// - 'volatile' ensures proper memory visibility.
	//
	// This is the most commonly used thread-safe Singleton pattern.
	// ---------------------------------------------------------
	static void dclSingletonExample() {
		System.out.println("\n--- Double-Checked Locking (DCL) Singleton Example ---");

		DCLSingleton x = DCLSingleton.getInstance();
		x.showMessage();

		DCLSingleton y = DCLSingleton.getInstance();
		System.out.println("Same instance? " + (x == y));
	}

	static class DCLSingleton {

		// volatile ensures visibility across threads
		private static volatile DCLSingleton instance;

		private DCLSingleton() {
			System.out.println("(DCLSingleton) Constructor executed");
		}

		public static DCLSingleton getInstance() {
			if (instance == null) { // First check (no locking)
				synchronized (DCLSingleton.class) {

					// Second check (thread-safe)
					if (instance == null) {
						instance = new DCLSingleton();
					}
				}
			}
			return instance;
		}

		void showMessage() {
			System.out.println("Hello from DCLSingleton!");
		}
	}

	// ---------------------------------------------------------
	// 4) INITIALIZATION-ON-DEMAND HOLDER SINGLETON
	//
	// - Best lazy-loading singleton pattern.
	// - Uses JVM class loading mechanics.
	// - Instance is created only when Holder class is accessed.
	// - No synchronization required.
	//
	// This method demonstrates lazy + efficient + thread-safe behavior.
	// ---------------------------------------------------------
	static void holderSingletonExample() {
		System.out.println("\n--- Initialization-on-demand Holder Singleton Example ---");

		HolderSingleton p = HolderSingleton.getInstance();
		p.showMessage();

		HolderSingleton q = HolderSingleton.getInstance();
		System.out.println("Same instance? " + (p == q));
	}

	static class HolderSingleton {

		private HolderSingleton() {
			System.out.println("(HolderSingleton) Constructor executed");
		}

		// Nested static class → loaded only when needed
		private static class Holder {
			private static final HolderSingleton INSTANCE = new HolderSingleton();
		}

		public static HolderSingleton getInstance() {
			return Holder.INSTANCE;
		}

		void showMessage() {
			System.out.println("Hello from HolderSingleton!");
		}
	}

	// ---------------------------------------------------------
	// 5) ENUM SINGLETON
	//
	// - Easiest and the safest Singleton approach.
	// - Enum guarantees:
	// * Thread safety
	// * Serialization safety
	// * Protection against reflection attacks
	//
	// This method demonstrates that enum-based singleton also
	// always returns the same instance.
	// ---------------------------------------------------------
	static void enumSingletonExample() {
		System.out.println("\n--- Enum Singleton Example ---");

		EnumSingleton e1 = EnumSingleton.INSTANCE;
		e1.showMessage();

		EnumSingleton e2 = EnumSingleton.INSTANCE;
		System.out.println("Same instance? " + (e1 == e2));
	}

	enum EnumSingleton {
		INSTANCE;

		EnumSingleton() {
			System.out.println("(EnumSingleton) Constructor executed");
		}

		void showMessage() {
			System.out.println("Hello from EnumSingleton!");
		}
	}
}
