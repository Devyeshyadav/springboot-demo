package com.springapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "emp_id", nullable = false, updatable = false)
	private String empId;

	@Column(name = "e_name")
	private String eName;

	@Column(name = "email")
	private String email;

	@Column(name = "department")
	private String department;

	@Column(name = "salary")
	private int salary;

	// ----- Getters and Setters -----

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Employee(String empId, String eName, String email, String department, int salary) {
		super();
		this.empId = empId;
		this.eName = eName;
		this.email = email;
		this.department = department;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", eName=" + eName + ", email=" + email + ", department=" + department
				+ ", salary=" + salary + "]";
	}
}
