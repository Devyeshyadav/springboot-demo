package com.springapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@Column(name = "emp_id")
	private Integer empid;

	@Column(name = "e_name")
	private String ename;

	@Column(name = "email")
	private String email;

	@Column(name = "department")
	private String department;

	@Column(name = "salary")
	private int salary;
}
