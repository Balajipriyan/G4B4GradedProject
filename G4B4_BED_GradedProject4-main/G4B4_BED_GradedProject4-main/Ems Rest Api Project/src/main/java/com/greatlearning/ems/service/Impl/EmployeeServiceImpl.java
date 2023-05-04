package com.greatlearning.ems.service.Impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.repository.EmployeeRepository;
import com.greatlearning.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> listAll() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);

	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}

	@Override
	public Employee findById(int theId) {

		return employeeRepository.findById(theId).get();
	}

	@Override
	public List<Employee> searchBy(String firstName) {

		List<Employee> searchResults = employeeRepository.findByFirstNameContainsIgnoreCase(firstName);
		return searchResults;
	}

}
