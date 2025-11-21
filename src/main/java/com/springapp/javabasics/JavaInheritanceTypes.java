package com.springapp.javabasics;

public class JavaInheritanceTypes {

    public static void main(String[] args) {

        System.out.println("===== Java Inheritance Types =====");

        singleInheritanceExample();
        multiLevelInheritanceExample();
        hierarchicalInheritanceExample();
        multipleInheritanceUsingInterfacesExample();
        hybridInheritanceExample();
    }

    // =====================================================================
    // 1. SINGLE INHERITANCE
    // =====================================================================
    /*
       * Description:
       * Single inheritance means a class inherits from exactly ONE parent class.
       * 
       * Structure:
       *    Parent → Child
       *
       * Benefit:
       *    Code reuse and simple inheritance hierarchy.
       */
    static class Animal {
        void eat() {
            System.out.println("Animal eats food");
        }
    }

    static class Dog extends Animal {
        void bark() {
            System.out.println("Dog barks");
        }
    }

    public static void singleInheritanceExample() {
        System.out.println("\n--- Single Inheritance Example ---");

        Dog dog = new Dog();
        dog.eat();  // inherited from Animal
        dog.bark(); // child class method
    }


    // =====================================================================
    // 2. MULTILEVEL INHERITANCE
    // =====================================================================
    /*
       * Description:
       * Multilevel inheritance means a class is inherited from another derived class.
       *
       * Structure:
       *    Grandparent → Parent → Child
       *
       * Benefit:
       *    Step-by-step inheritance of behavior.
       */
    static class Vehicle {
        void engine() {
            System.out.println("Vehicle has an engine");
        }
    }

    static class Car extends Vehicle {
        void wheels() {
            System.out.println("Car has wheels");
        }
    }

    static class SportsCar extends Car {
        void turbo() {
            System.out.println("SportsCar has turbo boost");
        }
    }

    public static void multiLevelInheritanceExample() {
        System.out.println("\n--- Multilevel Inheritance Example ---");

        SportsCar sc = new SportsCar();
        sc.engine(); // from Vehicle
        sc.wheels(); // from Car
        sc.turbo();  // from SportsCar
    }


    // =====================================================================
    // 3. HIERARCHICAL INHERITANCE
    // =====================================================================
    /*
       * Description:
       * Hierarchical inheritance means multiple child classes inherit from one parent class.
       *
       * Structure:
       *             Parent
       *            /      \
       *      Child1      Child2
       *
       * Benefit:
       *    Shared functionality from the same parent.
       */
    static class Shape {
        void draw() {
            System.out.println("Drawing a shape");
        }
    }

    static class Circle extends Shape {
        void area() {
            System.out.println("Area of Circle");
        }
    }

    static class Square extends Shape {
        void perimeter() {
            System.out.println("Perimeter of Square");
        }
    }

    public static void hierarchicalInheritanceExample() {
        System.out.println("\n--- Hierarchical Inheritance Example ---");

        Circle c = new Circle();
        c.draw();
        c.area();

        Square s = new Square();
        s.draw();
        s.perimeter();
    }


    // =====================================================================
    // 4. MULTIPLE INHERITANCE (Using Interfaces)
    // =====================================================================
    /*
       * Description:
       * Java does NOT support multiple inheritance using classes (due to ambiguity).
       * But it supports multiple inheritance using interfaces.
       *
       * Structure:
       *    Class implements Interface1, Interface2
       *
       * Benefit:
       *    A class can achieve multiple behavior types without ambiguity.
       */
    interface Flyable {
        void fly();
    }

    interface Runnable {
        void run();
    }

    static class Bird implements Flyable, Runnable {
        public void fly() {
            System.out.println("Bird flies in the sky");
        }

        public void run() {
            System.out.println("Bird runs on the ground");
        }
    }

    public static void multipleInheritanceUsingInterfacesExample() {
        System.out.println("\n--- Multiple Inheritance (Interfaces) Example ---");

        Bird b = new Bird();
        b.fly();
        b.run();
    }


    // =====================================================================
    // 5. HYBRID INHERITANCE (Achieved Using Interfaces)
    // =====================================================================
    /*
       * Description:
       * Hybrid inheritance = Combination of multiple + multilevel inheritance.
       * Java does NOT support this using classes but DOES support using interfaces.
       *
       * Structure:
       *        A       C
       *         \     /
       *           B
       *           |
       *        HybridClass
       *
       * Benefit:
       *    Helps to combine behaviors from different interface hierarchies.
       */
    interface A {
        void showA();
    }

    interface B extends A {   // multilevel (A -> B)
        void showB();
    }

    interface C {             // independent branch
        void showC();
    }

    static class HybridClass implements B, C {  // multiple + multilevel = hybrid
        public void showA() { System.out.println("A method"); }
        public void showB() { System.out.println("B method"); }
        public void showC() { System.out.println("C method"); }
    }

    public static void hybridInheritanceExample() {
        System.out.println("\n--- Hybrid Inheritance (Interfaces) Example ---");

        HybridClass h = new HybridClass();
        h.showA();
        h.showB();
        h.showC();
    }
}
