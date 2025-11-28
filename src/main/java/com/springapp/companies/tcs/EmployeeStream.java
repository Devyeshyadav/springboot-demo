package com.springapp.companies.tcs;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.springapp.model.Employee;

public class EmployeeStream {

	public static void main(String[] args) {

		List<Employee> emplist = List.of(
			    new Employee(1, "John", "john@dxc.com", "IT", 5000),
			    new Employee(2, "Alice", "Alice@dxc.com", "HR", 6000),
			    new Employee(3, "Carlo", "Carlo@dxc.com", "Lead", 7000)
			);

		Map<String, Optional<Employee>> res = emplist.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
		res.forEach((k, v) -> System.out.println(k + " -> " + v));

	}
}