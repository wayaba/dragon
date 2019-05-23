package com.blitox.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blitox.model.Employee;
import com.blitox.repository.EmployeeRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/")
    public String list(Model model) {
	    model.addAttribute("employees", employeeRepository.findAll());
        return "/employee/list";
    }
	
	@GetMapping("/add")
    public String add(Employee employee) {
        return "/employee/add";
    }
	
	
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Employee employee = employeeRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	     
	    model.addAttribute("employee", employee);
	    return "/employee/update";
	}
	
	@PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/employee/add";
        }
        employeeRepository.save(employee);
        
        return "redirect:/employee/";
    }
	
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Employee employee, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	employee.setId(id);
	        return "/employee/update";
	    }
	    employeeRepository.save(employee);
	    
	    return "redirect:/employee/";
	}
	     
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Employee employee = employeeRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		employeeRepository.delete(employee);
		
		return "redirect:/employee/";
	}
	
}
