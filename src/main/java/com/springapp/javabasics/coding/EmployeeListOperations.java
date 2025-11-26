package com.springapp.javabasics.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeListOperations {

	public static void main(String[] args) {
		List<Employee> emplist = List.of(new Employee("6", "Frank", "frank@dxc.com", "Operations", 4800),
				new Employee("7", "Grace", "grace@dxc.com", "HR", 6200),
				new Employee("8", "Henry", "henry@dxc.com", "IT", 7100),
				new Employee("9", "Ivy", "ivy@dxc.com", "Marketing", 5300),
				new Employee("10", "Jack", "jack@dxc.com", "Lead", 7500),
				new Employee("1", "John", "john@dxc.com", "IT", 5000),
				new Employee("2", "Alice", "alice@dxc.com", "HR", 6000),
				new Employee("3", "Carlo", "carlo@dxc.com", "Lead", 7000),
				new Employee("4", "David", "david@dxc.com", "Finance", 5500),
				new Employee("5", "Emma", "emma@dxc.com", "IT", 6500));

		System.out.println("Original Employee List:");
		emplist.forEach(System.out::println);
		System.out.println("--------------------------------------------------");

		sortByIdLex(emplist);
		sortByIdNumeric(emplist);
		sortBySalaryDesc(emplist);
		findById(emplist, "3");
		anySalaryGreaterThan(emplist, 7000);
		allSalaryAtLeast(emplist, 4800);
		employeeWithMaxSalary(emplist);
		employeeWithMinSalary(emplist);
		sumOfSalaries(emplist);
		averageSalary(emplist);
		groupByDepartment(emplist);
		highestPerDeptOptional(emplist);
		highestPerDeptDirect(emplist);
		totalSalaryByDept(emplist);
		avgSalaryByDept(emplist);
		mapIdToEmployee(emplist);
		countByDept(emplist);
		topNBySalary(emplist, 3);
		bottomNBySalary(emplist, 2);
		partitionBySalaryThreshold(emplist, 6000);
		joinAllEmails(emplist);
		toCsvLines(emplist);
		namesInAlphabeticalOrder(emplist);
		deptSortedBySalaryDesc(emplist);
		detectDuplicateEmails(emplist);
		safeToMapKeepHigherSalary(emplist);
		reduceFindHighest(emplist);
		deptToSetOfNames(emplist);
		paginationExample(emplist, 2, 3);
		deptCountSortedDesc(emplist);
		distinctDepartments(emplist);
		deptToSalariesList(emplist);
		convertToArray(emplist);
		peekForDebug(emplist);
		multiLevelGrouping(emplist);
		stableSortNote(emplist);
		multiFieldComparatorSort(emplist);
		parseSafeIds(emplist);
		toJsonSimple(emplist);
		distinctUsingEquals(emplist);
		complexityNotes();
		increaseSalaryByPercent(emplist, 10); // extra useful method (non-mutating)
		sortedNamesPerDept(emplist);

		System.out.println("--------------------------------------------------");
	}

	// 1. Sort employees by empId lexicographically
	public static void sortByIdLex(List<Employee> list) {
		System.out.println("\n1) Sort by empId (lexicographic):");
		List<Employee> sorted = list.stream().sorted(Comparator.comparing(Employee::getEmpId)).toList();
		sorted.forEach(System.out::println);
	}

	// 2. Sort employees by empId numerically
	public static void sortByIdNumeric(List<Employee> list) {
		System.out.println("\n2) Sort by empId (numeric):");
		List<Employee> sorted = list.stream().sorted(Comparator.comparingInt(e -> Integer.parseInt(e.getEmpId())))
				.toList();
		sorted.forEach(System.out::println);
	}

	// 3. Sort by salary descending
	public static void sortBySalaryDesc(List<Employee> list) {
		System.out.println("\n3) Sort by salary (descending):");
		List<Employee> sorted = list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).toList();
		sorted.forEach(System.out::println);
	}

	// 4. Find employee by ID
	public static void findById(List<Employee> list, String id) {
		System.out.println("\n4) Find by ID = " + id + ":");
		Optional<Employee> emp = list.stream().filter(e -> e.getEmpId().equals(id)).findFirst();
		System.out.println(emp.orElse(null));
	}

	// 5. Check if any salary > threshold
	public static void anySalaryGreaterThan(List<Employee> list, int threshold) {
		System.out.println("\n5) Any salary > " + threshold + " ?");
		boolean any = list.stream().anyMatch(e -> e.getSalary() > threshold);
		System.out.println(any);
	}

	// 6. Check if all salaries >= threshold
	public static void allSalaryAtLeast(List<Employee> list, int threshold) {
		System.out.println("\n6) All salaries >= " + threshold + " ?");
		boolean all = list.stream().allMatch(e -> e.getSalary() >= threshold);
		System.out.println(all);
	}

	// 7. Employee with max salary
	public static void employeeWithMaxSalary(List<Employee> list) {
		System.out.println("\n7) Employee with max salary:");
		Optional<Employee> max = list.stream().max(Comparator.comparingInt(Employee::getSalary));
		System.out.println(max.orElse(null));
	}

	// 8. Employee with min salary
	public static void employeeWithMinSalary(List<Employee> list) {
		System.out.println("\n8) Employee with min salary:");
		Optional<Employee> min = list.stream().min(Comparator.comparingInt(Employee::getSalary));
		System.out.println(min.orElse(null));
	}

	// 9. Sum of all salaries
	public static void sumOfSalaries(List<Employee> list) {
		System.out.println("\n9) Sum of salaries:");
		int sum = list.stream().mapToInt(Employee::getSalary).sum();
		System.out.println(sum);
	}

	// 10. Average salary
	public static void averageSalary(List<Employee> list) {
		System.out.println("\n10) Average salary:");
		double avg = list.stream().mapToInt(Employee::getSalary).average().orElse(0.0);
		System.out.println(avg);
	}

	// 11. Department-wise list
	public static void groupByDepartment(List<Employee> list) {
		System.out.println("\n11) Group by department:");
		Map<String, List<Employee>> byDept = list.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		byDept.forEach((k, v) -> {
			System.out.println(k + " -> " + v);
		});
	}

	// 12. Department-wise highest salary (Optional)
	public static void highestPerDeptOptional(List<Employee> list) {
		System.out.println("\n12) Highest per dept (Optional):");
		Map<String, Optional<Employee>> res = list.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
		res.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 13. Department-wise highest salary (Employee or null)
	public static void highestPerDeptDirect(List<Employee> list) {
		System.out.println("\n13) Highest per dept (Employee extracted):");
		Map<String, Employee> res = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)), opt -> opt.orElse(null))));
		res.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 14. Department-wise total salary
	public static void totalSalaryByDept(List<Employee> list) {
		System.out.println("\n14) Total salary by dept:");
		Map<String, Integer> res = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));
		res.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 15. Department-wise average salary
	public static void avgSalaryByDept(List<Employee> list) {
		System.out.println("\n15) Average salary by dept:");
		Map<String, Double> res = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(Employee::getSalary)));
		res.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 16. Map empId -> Employee
	public static void mapIdToEmployee(List<Employee> list) {
		System.out.println("\n16) Map empId -> Employee:");
		Map<String, Employee> map = list.stream().collect(Collectors.toMap(Employee::getEmpId, Function.identity()));
		map.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 17. Count by department
	public static void countByDept(List<Employee> list) {
		System.out.println("\n17) Count by department:");
		Map<String, Long> counts = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		counts.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 18. Top N by salary
	public static void topNBySalary(List<Employee> list, int n) {
		System.out.println("\n18) Top " + n + " by salary:");
		List<Employee> top = list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).limit(n)
				.toList();
		top.forEach(System.out::println);
	}

	// 19. Bottom N by salary
	public static void bottomNBySalary(List<Employee> list, int n) {
		System.out.println("\n19) Bottom " + n + " by salary:");
		List<Employee> bottom = list.stream().sorted(Comparator.comparingInt(Employee::getSalary)).limit(n).toList();
		bottom.forEach(System.out::println);
	}

	// 20. Partition by salary threshold
	public static void partitionBySalaryThreshold(List<Employee> list, int threshold) {
		System.out.println("\n20) Partition by salary >= " + threshold + ":");
		Map<Boolean, List<Employee>> part = list.stream()
				.collect(Collectors.partitioningBy(e -> e.getSalary() >= threshold));
		part.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 21. Join emails into comma separated string
	public static void joinAllEmails(List<Employee> list) {
		System.out.println("\n21) All emails (comma-separated):");
		String emails = list.stream().map(Employee::getEmail).collect(Collectors.joining(", "));
		System.out.println(emails);
	}

	// 22. Convert to CSV lines
	public static void toCsvLines(List<Employee> list) {
		System.out.println("\n22) CSV lines:");
		List<String> csv = list.stream().map(e -> String.join(",", e.getEmpId(), e.getName(), e.getEmail(),
				e.getDepartment(), String.valueOf(e.getSalary()))).toList();
		csv.forEach(System.out::println);
	}

	// 23. Names in alphabetical order
	public static void namesInAlphabeticalOrder(List<Employee> list) {
		System.out.println("\n23) Names sorted alphabetically:");
		List<String> names = list.stream().map(Employee::getName).sorted().toList();
		System.out.println(names);
	}

	// 24. Sort each department list by salary descending
	public static void deptSortedBySalaryDesc(List<Employee> list) {
		System.out.println("\n24) Each dept employees sorted by salary desc:");
		Map<String, List<Employee>> res = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(
						Collectors.toList(),
						l -> l.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).toList())));
		res.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 25. Detect duplicate emails
	public static void detectDuplicateEmails(List<Employee> list) {
		System.out.println("\n25) Detect duplicate emails:");
		Set<String> seen = new HashSet<>();
		List<Employee> duplicates = list.stream().filter(e -> !seen.add(e.getEmail().toLowerCase())).toList();
		System.out.println("duplicates: " + duplicates);
	}

	// 26. Safe toMap when keys collide: keep higher salary
	public static void safeToMapKeepHigherSalary(List<Employee> list) {
		System.out.println("\n26) toMap safe: keep employee with higher salary on conflict:");
		Map<String, Employee> map = list.stream().collect(Collectors.toMap(Employee::getEmpId, Function.identity(),
				(e1, e2) -> e1.getSalary() >= e2.getSalary() ? e1 : e2));
		map.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 27. Use reduce to find highest-paid employee
	public static void reduceFindHighest(List<Employee> list) {
		System.out.println("\n27) Reduce to find highest-paid employee:");
		Optional<Employee> highest = list.stream().reduce((e1, e2) -> e1.getSalary() >= e2.getSalary() ? e1 : e2);
		System.out.println(highest.orElse(null));
	}

	// 28. Map dept -> set of names
	public static void deptToSetOfNames(List<Employee> list) {
		System.out.println("\n28) Dept -> Set of names:");
		Map<String, Set<String>> map = list.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.mapping(Employee::getName, Collectors.toSet())));
		map.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 29. Pagination example (page p, size s) - page is 1-based
	public static void paginationExample(List<Employee> list, int page, int size) {
		System.out.println("\n29) Pagination: page " + page + " size " + size);
		List<Employee> pageList = list.stream().skip((long) (page - 1) * size).limit(size).toList();
		pageList.forEach(System.out::println);
	}

	// 30. Map<Dept, Count> sorted by count desc (LinkedHashMap)
	public static void deptCountSortedDesc(List<Employee> list) {
		System.out.println("\n30) Dept counts sorted by count desc:");
		Map<String, Long> counts = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		LinkedHashMap<String, Long> sorted = counts.entrySet().stream()
				.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
		sorted.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 31. Distinct list of departments
	public static void distinctDepartments(List<Employee> list) {
		System.out.println("\n31) Distinct departments:");
		List<String> depts = list.stream().map(Employee::getDepartment).distinct().toList();
		System.out.println(depts);
	}

	// 32. Dept -> list of salaries
	public static void deptToSalariesList(List<Employee> list) {
		System.out.println("\n32) Dept -> List<Integer> salaries:");
		Map<String, List<Integer>> map = list.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.mapping(Employee::getSalary, Collectors.toList())));
		map.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 33. Convert to array
	public static void convertToArray(List<Employee> list) {
		System.out.println("\n33) Convert to Employee[] array:");
		Employee[] arr = list.stream().toArray(Employee[]::new);
		System.out.println(Arrays.toString(arr));
	}

	// 34. Use peek for debugging (prints during stream)
	public static void peekForDebug(List<Employee> list) {
		System.out.println("\n34) Using peek for debug:");
		List<Employee> processed = list.stream().peek(e -> System.out.println("processing: " + e.getEmpId()))
				.filter(e -> e.getSalary() > 5000).toList();
		System.out.println("filtered: " + processed);
	}

	// 35. Multi-level grouping: dept -> salary range -> employees
	public static void multiLevelGrouping(List<Employee> list) {
		System.out.println("\n35) Multi-level grouping (dept -> bracket):");
		Map<String, Map<String, List<Employee>>> multi = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.groupingBy(e -> {
					int s = e.getSalary();
					if (s < 6000)
						return "LOW";
					if (s < 7000)
						return "MID";
					return "HIGH";
				})));
		multi.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 36. Stable sort note - demonstrate stable sorting by salary while preserving
	// input order for ties
	public static void stableSortNote(List<Employee> list) {
		System.out.println("\n36) Stable sort by salary (ties preserve original order):");
		// To demonstrate, create list with tie salaries while preserving input order
		List<Employee> sample = new ArrayList<>(list);
		// stable sort
		List<Employee> sorted = sample.stream().sorted(Comparator.comparingInt(Employee::getSalary)).toList();
		sorted.forEach(System.out::println);
	}

	// 37. Multi-field comparator: dept asc, salary desc, name asc
	public static void multiFieldComparatorSort(List<Employee> list) {
		System.out.println("\n37) Sort by dept asc, salary desc, name asc:");
		Comparator<Employee> cmp = Comparator.comparing(Employee::getDepartment)
				.thenComparing(Comparator.comparingInt(Employee::getSalary).reversed())
				.thenComparing(Employee::getName);
		List<Employee> sorted = list.stream().sorted(cmp).toList();
		sorted.forEach(System.out::println);
	}

	// 38. parse-safe empId handling (avoid NumberFormatException)
	public static void parseSafeIds(List<Employee> list) {
		System.out.println("\n38) Parse-safe IDs (filter valid numeric ids and sort numerically):");
		List<Employee> sorted = list.stream().filter(e -> {
			try {
				Integer.parseInt(e.getEmpId());
				return true;
			} catch (NumberFormatException ex) {
				return false;
			}
		}).sorted(Comparator.comparingInt(e -> Integer.parseInt(e.getEmpId()))).toList();
		sorted.forEach(System.out::println);
	}

	// 39. Simple JSON conversion (no external libs) - for demo only
	public static void toJsonSimple(List<Employee> list) {
		System.out.println("\n39) Simple JSON (manual, demo only):");
		String json = list.stream()
				.map(e -> String.format(
						"{\"empId\":\"%s\",\"name\":\"%s\",\"email\":\"%s\",\"department\":\"%s\",\"salary\":%d}",
						escape(e.getEmpId()), escape(e.getName()), escape(e.getEmail()), escape(e.getDepartment()),
						e.getSalary()))
				.collect(Collectors.joining(",", "[", "]"));
		System.out.println(json);
	}

	private static String escape(String s) {
		return s.replace("\"", "\\\"");
	}

	// 40. distinct() using equals/hashCode (assumes Employee implements
	// equals/hashCode)
	public static void distinctUsingEquals(List<Employee> list) {
		System.out.println("\n40) distinct() using equals/hashCode (if implemented):");
		List<Employee> distinct = list.stream().distinct().toList();
		System.out.println(distinct);
	}

	// 41. Complexity notes printed
	public static void complexityNotes() {
		System.out.println("\n41) Complexity notes:");
		System.out.println(" - Sorting: O(n log n)");
		System.out.println(" - Grouping: O(n)");
		System.out.println(" - toMap: O(n)");
	}

	// 42. Increase salary by percent (non-mutating)
	public static void increaseSalaryByPercent(List<Employee> list, double percent) {
		System.out.println("\n42) Increase salary by " + percent + "% (non-mutating):");
		List<Employee> bumped = list.stream().map(e -> new Employee(e.getEmpId(), e.getName(), e.getEmail(),
				e.getDepartment(), (int) Math.round(e.getSalary() * (1 + percent / 100.0)))).toList();
		bumped.forEach(System.out::println);
	}

	// 43. Get sorted names per department
	public static void sortedNamesPerDept(List<Employee> list) {
		System.out.println("\n43) Sorted names per department:");
		Map<String, List<String>> res = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName,
						Collectors.collectingAndThen(Collectors.toList(), l -> l.stream().sorted().toList()))));
		res.forEach((k, v) -> System.out.println(k + " -> " + v));
	}

	// 44. Remove duplicates based on empId preserving order (LinkedHashMap)
	public static void removeDuplicatesByIdPreserveOrder(List<Employee> list) {
		System.out.println("\n44) Remove duplicates by empId (preserve input order):");
		Map<String, Employee> map = new LinkedHashMap<>();
		list.forEach(e -> map.putIfAbsent(e.getEmpId(), e));
		List<Employee> unique = new ArrayList<>(map.values());
		unique.forEach(System.out::println);
	}

	// Employee class (nested for one-file convenience)
	public static class Employee {
		private final String empId;
		private final String name;
		private final String email;
		private final String department;
		private final int salary;

		public Employee(String empId, String name, String email, String department, int salary) {
			this.empId = empId;
			this.name = name;
			this.email = email;
			this.department = department;
			this.salary = salary;
		}

		public String getEmpId() {
			return empId;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getDepartment() {
			return department;
		}

		public int getSalary() {
			return salary;
		}

		@Override
		public String toString() {
			return empId + " - " + name + " - " + department + " - " + salary;
		}

		// Optional: equals/hashCode (used by distinct())
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Employee))
				return false;
			Employee employee = (Employee) o;
			return salary == employee.salary && Objects.equals(empId, employee.empId)
					&& Objects.equals(name, employee.name) && Objects.equals(email, employee.email)
					&& Objects.equals(department, employee.department);
		}

		@Override
		public int hashCode() {
			return Objects.hash(empId, name, email, department, salary);
		}
	}
}
