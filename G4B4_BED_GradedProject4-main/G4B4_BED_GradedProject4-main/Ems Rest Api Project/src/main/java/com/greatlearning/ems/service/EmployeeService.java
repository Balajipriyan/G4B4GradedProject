
package com.greatlearning.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.greatlearning.ems.entity.Employee;

public interface EmployeeService {

	List<Employee> listAll();

	Employee saveEmployee(Employee employee);

	public Employee findById(int theId);

	public void deleteById(int theId);

	public List<Employee> searchBy(String firstName);

}
