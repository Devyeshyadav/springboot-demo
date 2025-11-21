package com.springapp.companies.tcs;

import java.util.Comparator;
import java.util.List;

import com.springapp.model.Employee;

public class EmployeeStream {

	public static void main(String[] args) {

		List<Employee> emplist = List.of(
			    new Employee("1", "John", 7000, "IT"),
			    new Employee("2", "Alice", 4000, "HR"),
			    new Employee("3", "Carlo", 5000, "Lead")
			);

		List<Employee> sal = emplist.stream()
				.filter(e -> e.getSalary() <= 4000)
				.sorted(Comparator.comparing(Employee::getDep))
//				.map(e -> e)
				.toList();

		System.out.println(sal);
	}
}