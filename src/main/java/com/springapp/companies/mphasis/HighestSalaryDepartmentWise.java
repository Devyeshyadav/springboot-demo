package com.springapp.companies.mphasis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HighestSalaryDepartmentWise {

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(new Employee(101, "Ravi", 85000, "IT"),
				new Employee(102, "Arjun", 65000, "HR"), new Employee(103, "Kiran", 92000, "IT"),
				new Employee(104, "Priya", 70000, "HR"), new Employee(105, "Meena", 77000, "Finance"),
				new Employee(106, "Rohan", 88000, "Finance"));

		// Stream logic: highest salary per department
		Map<String, Employee> highestSalaryByDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), Optional::get)));

		// Print results
		System.out.println("Department-wise Highest Salary:");
		highestSalaryByDept.forEach((dept, emp) -> {
			System.out.println(dept + " -> " + emp);
		});
	}

	// Employee class
	static class Employee {
		private int id;
		private String name;
		private double salary;
		private String department;

		public Employee(int id, String name, double salary, String department) {
			this.id = id;
			this.name = name;
			this.salary = salary;
			this.department = department;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public double getSalary() {
			return salary;
		}

		public String getDepartment() {
			return department;
		}

		@Override
		public String toString() {
			return id + " - " + name + " - " + salary + " - " + department;
		}

	}
}
