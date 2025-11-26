package com.springapp.jpa.exception;

public class EmployeeNotFoundException extends RuntimeException {
	public EmployeeNotFoundException(Integer id) {
		super("Employee not found with id: " + id);
	}
}
