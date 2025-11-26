package com.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.jpa.service.EmployeeService;
import com.springapp.model.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    
    // 1️⃣ Get employee by ID
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return service.findByIdOrThrow(id);
    }

    // 2️⃣ Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.findAll();
    }

    // 3️⃣ Create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = service.saveEmployee(employee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 4️⃣ Update an existing employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedData) {
        return service.updateEmployee(id, updatedData);
    }

    // 5️⃣ Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        service.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    // 6️⃣ Search employees by department
    @GetMapping("/search")
    public List<Employee> searchByDepartment(@RequestParam String department) {
        return service.findByDepartment(department);
    }
}
