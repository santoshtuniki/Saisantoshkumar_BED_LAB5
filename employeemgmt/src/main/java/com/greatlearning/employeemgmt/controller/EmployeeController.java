package com.greatlearning.employeemgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.greatlearning.employeemgmt.model.Employee;
import com.greatlearning.employeemgmt.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		List<Employee> result = employeeService.getAll();
		model.addAttribute("employees", result);
		return "employees";
	}

	@GetMapping("/employees/new")
	public String addNewEmployee(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		return "create_employee";
	}

	@GetMapping("/employees/edit/{id}")
	public String UpdateEmployee(Model model, @PathVariable("id") int id) {
		Employee emp = employeeService.getEmployeeById(id);
		model.addAttribute("employee", emp);
		return "edit_employee";
	}

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}

	@PostMapping("/employees")
	public String addEmployee(@ModelAttribute(name = "employee") Employee emp) {
		employeeService.saveOrUpdate(emp);
		return "redirect:/employees";
	}

	@PostMapping("/employees/{id}")
	public String updateEmployee(@ModelAttribute(name = "employee") Employee emp, @PathVariable("id") int id) {
		Employee existingEmp = employeeService.getEmployeeById(id);
		if (existingEmp.getId() == emp.getId()) {
			existingEmp.setFirstName(emp.getFirstName());
			existingEmp.setLastName(emp.getLastName());
			existingEmp.setEmail(emp.getEmail());
		}
		employeeService.saveOrUpdate(existingEmp);
		return "redirect:/employees";
	}

}
