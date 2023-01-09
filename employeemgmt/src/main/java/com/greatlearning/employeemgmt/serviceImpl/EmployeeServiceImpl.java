package com.greatlearning.employeemgmt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemgmt.Repository.EmployeeRepository;
import com.greatlearning.employeemgmt.model.Employee;
import com.greatlearning.employeemgmt.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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
		Employee emp = getEmployeeById(id);
		employeeRepository.delete(emp);
	}

	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RuntimeException("Employee does not exists for the Id: " + id);
		}
	}

}
