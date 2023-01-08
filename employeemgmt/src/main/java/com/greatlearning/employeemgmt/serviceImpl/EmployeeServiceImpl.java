package com.greatlearning.employeemgmt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemgmt.Repository.EmployeeRepository;
import com.greatlearning.employeemgmt.model.Employee;
import com.greatlearning.employeemgmt.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveOrUpdate(Employee employee) {
		employeeRepository.save(employee);
		employeeRepository.flush();
	}

	@Override
	public void deleteEmployeeById(int id) {
		Employee emp  = getEmployeeById(id);
		employeeRepository.delete(emp);
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id).get();
	}

}
