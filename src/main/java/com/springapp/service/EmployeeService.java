package com.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springapp.dto.EmployeeProjection;
import com.springapp.exception.EmployeeNotFoundException;
import com.springapp.model.Employee;
import com.springapp.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    // Basic: get by id (throws if not found)
    public Employee getById(String empId) {
        return repo.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
    }

    // Find by name
    public List<Employee> getByName(String name) {
        return repo.findByEName(name);
    }

    // Find by department
    public List<Employee> getByDepartment(String department) {
        return repo.findByDepartment(department);
    }

    // Salary greater than
    public List<Employee> getBySalaryGreaterThan(int salary) {
        return repo.findBySalaryGreaterThan(salary);
    }

    // Find optional by email
    public Optional<Employee> getByEmail(String email) {
        return repo.findByEmail(email);
    }

    // Exists by email
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    // Count by department
    public long countByDepartment(String department) {
        return repo.countByDepartment(department);
    }

    // JPQL example
    public List<Employee> fetchByNameJPQL(String name) {
        return repo.fetchByNameJPQL(name);
    }

    // Native example
    public List<Employee> fetchByMinSalaryNative(int minSalary) {
        return repo.fetchByMinSalaryNative(minSalary);
    }

    // Projection example
    public List<EmployeeProjection> fetchProjectionByDepartment(String department) {
        return repo.fetchProjectionByDepartment(department);
    }

    // Paging example
    public Page<Employee> getAllPaged(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(pageable);
    }

    // Create or update
    public Employee save(Employee emp) {
        return repo.save(emp);
    }

    // Update salary (uses @Modifying query)
    @Transactional
    public int updateSalary(String empId, int newSalary) {
        return repo.updateSalaryById(empId, newSalary);
    }

    // Delete by id
    public void deleteById(String empId) {
        repo.deleteById(empId);
    }

    // Delete by email
    public void deleteByEmail(String email) {
        repo.deleteByEmail(email);
    }
}
