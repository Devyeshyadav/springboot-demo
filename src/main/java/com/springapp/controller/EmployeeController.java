package com.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.model.Employee;
import com.springapp.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService EmployeeService) {
        this.employeeService = EmployeeService;
    }
	
	@GetMapping("/{id}")
    public Employee get(@PathVariable int id) {
        return employeeService.getByID(id);
    }
	

}
