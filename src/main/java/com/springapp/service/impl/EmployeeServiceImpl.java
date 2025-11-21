package com.springapp.service.impl;

import com.springapp.dao.EmployeeDAO;
import com.springapp.model.Employee;
import com.springapp.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDAO employeeDAO;

	public EmployeeServiceImpl(EmployeeDAO EmployeeDAO) {
		this.employeeDAO = EmployeeDAO;
	}

	@Override
	public Employee getByID(int eid) {

		return employeeDAO.getByID(eid);

	}

}
