package com.springapp.companies.tcs;

import java.util.Comparator;
import java.util.List;

import com.springapp.model.Employee;

public class EmployeeStream {

	public static void main(String[] args) {

		List<Employee> emplist = List.of(
			    new Employee("1", "John", "john@dxc.com", "IT", 5000),
			    new Employee("2", "Alice", "Alice@dxc.com", "HR", 6000),
			    new Employee("3", "Carlo", "Carlo@dxc.com", "Lead", 7000)
			);

		List<Employee> sal = emplist.stream()
				.filter(e -> e.getSalary() <= 4000)
				.sorted(Comparator.comparing(Employee::getDepartment))
//				.map(e -> e)
				.toList();

		System.out.println(sal);
	}
}