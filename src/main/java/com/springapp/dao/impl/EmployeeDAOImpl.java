package com.springapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.springapp.dao.EmployeeDAO;
import com.springapp.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Employee getByID(int eid) {
		return entityManager.find(Employee.class, eid);
	}

}
