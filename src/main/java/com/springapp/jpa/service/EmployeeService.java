package com.springapp.jpa.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springapp.jpa.exception.EmployeeNotFoundException;
import com.springapp.jpa.repository.EmployeeRepository;
import com.springapp.model.Employee;

@Service
public class EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository repo;

	public EmployeeService(EmployeeRepository repo) {
		this.repo = repo;
	}

	public Optional<Employee> findById(Integer id) {
		return repo.findById(id);
	}
	
	// in EmployeeService
	public Employee findByIdOrThrow(Integer id) {
	    return repo.findById(id)
	               .orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	public List<Employee> findAll() {
	    return repo.findAll();
	}

	public Employee saveEmployee(Employee e) {
	    return repo.save(e);
	}

	@Transactional
	public Employee updateEmployee(Integer id, Employee newData) {
	    Employee existing = findByIdOrThrow(id);

	    existing.setEname(newData.getEname());
	    existing.setEmail(newData.getEmail());
	    existing.setDepartment(newData.getDepartment());
	    existing.setSalary(newData.getSalary());

	    return repo.save(existing);
	}

	@Transactional
	public void deleteEmployee(Integer id) {
	    Employee existing = findByIdOrThrow(id);
	    repo.delete(existing);
	}

	public List<Employee> findByDepartment(String department) {
	    return repo.findByDepartment(department);
	}

	public Employee findByEname(String ename) {
		return repo.findByEname(ename);
	}

}