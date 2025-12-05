package com.springapp.javabasics;

//=========================================================
//SOLID is an acronym for five design principles that make
//software more maintainable, scalable, testable, and flexible.
//These principles help avoid tight coupling, reduce bugs,
//promote clean architecture, and ensure that code is easy to
//extend without modifying existing parts.
//=========================================================

public class SolidPrinciples {

    public static void main(String[] args) {

        // -----------------------------------------------------
        // main()
        // 
        // Demonstrates each SOLID principle in sequence. For each
        // principle, a dedicated method prints an explanation,
        // runs a small "bad" (violating) demonstration, then a
        // "good" (correct) demonstration to compare behaviors.
        //
        // This is intentionally linear for readability and to
        // make it easy to show sample outputs for teaching/interview use.
        // -----------------------------------------------------
        singleResponsibilityExample();
        openClosedExample();
        liskovSubstitutionExample();
        interfaceSegregationExample();
        dependencyInversionExample();
    }

    // ---------------------------------------------------------
    // 1) SINGLE RESPONSIBILITY PRINCIPLE (SRP)
    //
    // - Definition:
    //   A class should have only one reason to change. Each class
    //   should have a single responsibility.
    //
    // - Bad example: One class handles both data and presentation or
    //   business and persistence logic. That couples responsibilities
    //   and makes maintenance and testing harder.
    //
    // - Good example: Separate concerns into multiple classes:
    //   e.g., Model (data), Repository (persistence), Service (business),
    //   and Printer/Formatter (presentation).
    //
    // This method prints both versions so you can see how separation
    // improves clarity and testability.
    // ---------------------------------------------------------
    static void singleResponsibilityExample() {
        System.out.println("\n--- Single Responsibility Principle (SRP) Example ---");

        // BAD: Violates SRP — UserManager mixes business + persistence + formatting
        System.out.println("\nBad (violates SRP):");
        UserManagerBad userManagerBad = new UserManagerBad();
        userManagerBad.createUser("alice@example.com");
        // This class does many things — changes to storage format or printing
        // would require changing UserManagerBad.

        // GOOD: Separate responsibilities
        System.out.println("\nGood (follows SRP):");
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserPrinter userPrinter = new UserPrinter();

        User goodUser = userService.createUser("bob@example.com");
        userPrinter.print(goodUser);
    }

    // Bad SRP: single class doing many tasks (create, persist, print)
    static class UserManagerBad {
        void createUser(String email) {
            // 1) Create user object (business logic)
            User u = new User(email);

            // 2) Persist user (persistence logic)
            System.out.println("(UserManagerBad) Persisting user to DB: " + u.getEmail());

            // 3) Print confirmation (presentation logic)
            System.out.println("(UserManagerBad) Printing: User created with email => " + u.getEmail());
        }
    }

    // Good SRP: small focused classes

    // Simple domain model
    static class User {
        private final String email;

        User(String email) {
            this.email = email;
        }

        String getEmail() {
            return email;
        }
    }

    // Responsibility: persist user (dummy implementation)
    static class UserRepository {
        User save(User u) {
            System.out.println("(UserRepository) Saving user to DB: " + u.getEmail());
            // In a real app this returns the persisted entity
            return u;
        }
    }

    // Responsibility: business operations related to users
    static class UserService {
        private final UserRepository repository;

        UserService(UserRepository repository) {
            this.repository = repository;
        }

        User createUser(String email) {
            User u = new User(email);
            return repository.save(u);
        }
    }

    // Responsibility: presentation/printing
    static class UserPrinter {
        void print(User u) {
            System.out.println("(UserPrinter) User created: " + u.getEmail());
        }
    }

    // ---------------------------------------------------------
    // 2) OPEN/CLOSED PRINCIPLE (OCP)
    //
    // - Definition:
    //   Software entities (classes, modules, functions) should be
    //   open for extension, but closed for modification.
    //
    // - Bad example: Modifying an existing class when you add new
    //   behavior (e.g., adding conditional logic for new types).
    //
    // - Good example: Use abstraction (interfaces/abstract classes)
    //   and extend behavior by adding new implementations without
    //   modifying existing code.
    //
    // This method demonstrates both approaches by showing a payment
    // processor that is extended properly.
    // ---------------------------------------------------------
    static void openClosedExample() {
        System.out.println("\n--- Open/Closed Principle (OCP) Example ---");

        // BAD: PaymentProcessorBad changes when adding new payment types
        System.out.println("\nBad (violates OCP):");
        PaymentProcessorBad paymentBad = new PaymentProcessorBad();
        paymentBad.process("creditcard", 100.0);
        paymentBad.process("paypal", 50.0);
        // Suppose we add "crypto" — we must modify PaymentProcessorBad,
        // which risks introducing bugs.

        // GOOD: Use abstraction and new implementations to extend behavior
        System.out.println("\nGood (follows OCP):");
        PaymentService paymentService = new PaymentService();
        paymentService.register(new CreditCardPayment());
        paymentService.register(new PaypalPayment());
        paymentService.processPayment("creditcard", 120.0);
        paymentService.processPayment("paypal", 30.0);
        // To add "crypto", create CryptoPayment and register it — no existing code change.
    }

    // Bad OCP: conditional logic that must be modified for new types
    static class PaymentProcessorBad {
        void process(String type, double amount) {
            if ("creditcard".equalsIgnoreCase(type)) {
                System.out.println("(PaymentProcessorBad) Processing credit card: " + amount);
            } else if ("paypal".equalsIgnoreCase(type)) {
                System.out.println("(PaymentProcessorBad) Processing PayPal: " + amount);
            } else {
                System.out.println("(PaymentProcessorBad) Unknown payment type: " + type);
            }
        }
    }

    // Good OCP: abstraction-based design

    // Strategy interface for payment
    interface Payment {
        String getName();
        void pay(double amount);
    }

    static class CreditCardPayment implements Payment {
        public String getName() { return "creditcard"; }
        public void pay(double amount) {
            System.out.println("(CreditCardPayment) Charging credit card: " + amount);
        }
    }

    static class PaypalPayment implements Payment {
        public String getName() { return "paypal"; }
        public void pay(double amount) {
            System.out.println("(PaypalPayment) Processing PayPal payment: " + amount);
        }
    }

    // PaymentService manages registered payment implementations
    static class PaymentService {
        private final java.util.Map<String, Payment> registry = new java.util.HashMap<>();

        void register(Payment payment) {
            registry.put(payment.getName().toLowerCase(), payment);
        }

        void processPayment(String type, double amount) {
            Payment p = registry.get(type.toLowerCase());
            if (p != null) {
                p.pay(amount);
            } else {
                System.out.println("(PaymentService) No payment handler for: " + type);
            }
        }
    }

    // ---------------------------------------------------------
    // 3) LISKOV SUBSTITUTION PRINCIPLE (LSP)
    //
    // - Definition:
    //   Subtypes must be substitutable for their base types without
    //   altering desirable properties of the program (correctness).
    //
    // - Bad example: Subclass changes expected behavior or throws
    //   exceptions when used in place of parent.
    //
    // - Good example: Subclass honors the contract and behaves
    //   predictably; use interfaces and avoid violating postconditions.
    //
    // This method demonstrates a Rectangle/Square classic LSP pitfall.
    // ---------------------------------------------------------
    static void liskovSubstitutionExample() {
        System.out.println("\n--- Liskov Substitution Principle (LSP) Example ---");

        // BAD: Square extends Rectangle and breaks LSP because setting width
        // also affects height — clients of Rectangle that rely on independent
        // width/height may break.
        System.out.println("\nBad (violates LSP):");
        Rectangle rect = new Rectangle(5, 10);
        useRectangle(rect); // expected area = 50

        Rectangle squareAsRect = new Square(5);
        useRectangle(squareAsRect); // surprising behavior: setters behave differently

        // GOOD: Design with separate abstractions or immutable value objects
        System.out.println("\nGood (follows LSP):");
        Shape r = new Rectangle2(5, 10); // behaves as expected
        Shape s = new Square2(5);        // separate abstraction with its own contract
        printArea(r);
        printArea(s);
    }

    // Client code that expects rectangle semantics
    static void useRectangle(Rectangle r) {
        System.out.println("(useRectangle) Before: width=" + r.getWidth() + ", height=" + r.getHeight());
        r.setWidth(20); // client expects this to only change width
        System.out.println("(useRectangle) After setWidth(20): width=" + r.getWidth() + ", height=" + r.getHeight());
        System.out.println("(useRectangle) Area: " + r.area());
    }

    // Simple Rectangle (bad for LSP example)
    static class Rectangle {
        private int width;
        private int height;

        Rectangle(int w, int h) {
            this.width = w;
            this.height = h;
        }

        int getWidth() { return width; }
        int getHeight() { return height; }

        void setWidth(int w) { this.width = w; }
        void setHeight(int h) { this.height = h; }

        int area() { return width * height; }
    }

    // Square subclass that overrides setters — breaks LSP
    static class Square extends Rectangle {
        Square(int size) {
            super(size, size);
        }

        @Override
        void setWidth(int w) {
            super.setWidth(w);
            super.setHeight(w); // unexpected coupling of width & height
        }

        @Override
        void setHeight(int h) {
            super.setHeight(h);
            super.setWidth(h);
        }
    }

    // Better approach: use a common Shape interface and different implementations
    interface Shape {
        int area();
    }

    static class Rectangle2 implements Shape {
        private final int width;
        private final int height;

        Rectangle2(int w, int h) {
            this.width = w;
            this.height = h;
        }

        public int area() { return width * height; }
    }

    static class Square2 implements Shape {
        private final int size;

        Square2(int size) { this.size = size; }

        public int area() { return size * size; }
    }

    static void printArea(Shape s) {
        System.out.println("(printArea) Area = " + s.area());
    }

    // ---------------------------------------------------------
    // 4) INTERFACE SEGREGATION PRINCIPLE (ISP)
    //
    // - Definition:
    //   Many client-specific interfaces are better than one general-purpose
    //   interface. Clients should not be forced to depend on methods they do not use.
    //
    // - Bad example: A large "fat" interface that forces implementations
    //   to provide unsupported methods.
    //
    // - Good example: Split interfaces by role/behavior so classes only implement
    //   what they need.
    //
    // This method demonstrates both approaches using Printer examples.
    // ---------------------------------------------------------
    static void interfaceSegregationExample() {
        System.out.println("\n--- Interface Segregation Principle (ISP) Example ---");

        // BAD: FatPrinter implements PrinterBad but must supply scan/fax methods it doesn't need
        System.out.println("\nBad (violates ISP):");
        PrinterBad fatPrinter = new FatPrinter();
        fatPrinter.print("hello");     // okay
        fatPrinter.scan();             // okay
        fatPrinter.fax("doc");         // okay

        // GOOD: Segregated interfaces allow focused implementations
        System.out.println("\nGood (follows ISP):");
        PrinterSimple simple = new SimplePrinter();
        simple.print("hello ISP");

        ScannerDevice scanner = new SimpleScanner();
        scanner.scan();
    }

    // Bad (fat) interface with multiple responsibilities
    interface PrinterBad {
        void print(String doc);
        void scan();
        void fax(String doc);
    }

    // Implementation forced to implement everything even if not needed
    static class FatPrinter implements PrinterBad {
        public void print(String doc) { System.out.println("(FatPrinter) Printing: " + doc); }
        public void scan() { System.out.println("(FatPrinter) Scanning..."); }
        public void fax(String doc) { System.out.println("(FatPrinter) Faxing: " + doc); }
    }

    // Good: segregate interfaces
    interface PrinterSimple { void print(String doc); }
    interface ScannerDevice { void scan(); }
    interface FaxDevice { void fax(String doc); }

    static class SimplePrinter implements PrinterSimple {
        public void print(String doc) { System.out.println("(SimplePrinter) Printing: " + doc); }
    }

    static class SimpleScanner implements ScannerDevice {
        public void scan() { System.out.println("(SimpleScanner) Scanning document..."); }
    }

    // ---------------------------------------------------------
    // 5) DEPENDENCY INVERSION PRINCIPLE (DIP)
    //
    // - Definition:
    //   High-level modules should not depend on low-level modules.
    //   Both should depend on abstractions. Abstractions should not
    //   depend on details — details should depend on abstractions.
    //
    // - Bad example: High-level class instantiates concrete low-level classes.
    //
    // - Good example: High-level depends on abstraction (interface), and concrete
    //   implementations are injected (constructor injection).
    //
    // This method shows how to invert dependencies to make code more modular and testable.
    // ---------------------------------------------------------
    static void dependencyInversionExample() {
        System.out.println("\n--- Dependency Inversion Principle (DIP) Example ---");

        // BAD: High-level class directly creates a concrete low-level logger
        System.out.println("\nBad (violates DIP):");
        ApplicationBad appBad = new ApplicationBad();
        appBad.doWork();

        // GOOD: High-level depends on Logger abstraction; concrete implementations are injected
        System.out.println("\nGood (follows DIP):");
        Logger logger = new ConsoleLogger(); // could also inject FileLogger, MockLogger, etc.
        ApplicationGood appGood = new ApplicationGood(logger);
        appGood.doWork();
    }

    // Bad: high-level module depends on low-level concrete implementation
    static class ApplicationBad {
        private final ConsoleLogger logger = new ConsoleLogger(); // direct dependency

        void doWork() {
            logger.log("ApplicationBad working...");
        }
    }

    // Abstraction for logging
    interface Logger {
        void log(String message);
    }

    // Low-level module: concrete console logger
    static class ConsoleLogger implements Logger {
        public void log(String message) {
            System.out.println("(ConsoleLogger) " + message);
        }
    }

    // Low-level module: another concrete logger (example)
    static class FileLogger implements Logger {
        public void log(String message) {
            // Dummy implementation for demonstration
            System.out.println("(FileLogger) Writing log to file: " + message);
        }
    }

    // Good: high-level depends on abstraction; concrete logger injected
    static class ApplicationGood {
        private final Logger logger;

        ApplicationGood(Logger logger) {
            this.logger = logger; // dependency injection via constructor
        }

        void doWork() {
            logger.log("ApplicationGood doing work...");
        }
    }
}
