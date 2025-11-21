package com.springapp.dao;

import com.springapp.model.Employee;

public interface EmployeeDAO {
	Employee getByID(int eid);
}
