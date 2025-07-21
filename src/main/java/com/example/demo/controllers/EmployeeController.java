package com.example.demo.controllers;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/employees")  // Note: no /api prefix here
public class EmployeeController {

    private final EmployeeService employeeService;;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Show list of employees page
    @GetMapping
    public String viewEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "viewemployees";  // Thymeleaf template: viewemployees.html
    }

    // Show form to create new employee
    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "newemployee";  // Thymeleaf template: newemployee.html
    }

    // Handle form submission to save new employee
    @PostMapping
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees";  // Redirect to employees list after saving
    }
}
