package com.example.demo.controllers;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;


import javax.validation.Valid;
import java.util.List;



@Controller
@RequestMapping("/employees")  // Note: no /api prefix here
public class EmployeeController {


    @Autowired
    private final EmployeeService employeeService;
    private ApplicationContext context;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Show list of employees page
    // @GetMapping
    // public String viewEmployees(Model model) {
    //    model.addAttribute("employees", employeeService.getAllEmployees());
    //    return "viewemployees";  // Thymeleaf template: viewemployees.html
    // }

    // Show form to create new employee
    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "newemployee";  // Thymeleaf template: newemployee.html
    }

    //the search
    @GetMapping
    public String viewEmployees(@RequestParam(required = false) String keyword, Model model) {
        List<Employee> employees;
        if (keyword != null && !keyword.isEmpty()) {
            employees = employeeService.search(keyword);
        } else {
            employees = employeeService.getAllEmployees();
        }
        model.addAttribute("employees", employees);  // Must be non-null list
        model.addAttribute("keyword", keyword);
        return "viewemployees";
    }



    //delete
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

    // Show edit form pre-filled with employee data
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.findById(id);  // Load employee by ID
        if (employee == null) {
            // Employee not found, redirect to a safe page or show error
            return "redirect:/mainscreen";
        }
        // Add the employee object to the model for Thymeleaf
        model.addAttribute("employee", employee);
        // Return the view name of your edit form template
        return "editemployee";
    }

    // Handle form submission to update employee
    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") int id,
                                 @Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editemployee";  // return to edit form if validation fails
        }
        employee.setId((long) id);  // ensure the employee ID is set
        employeeService.updateEmployee(employee);  // you need this method in service
        return "redirect:/employees";
    }

    //error page for new employees
    @PostMapping
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "newemployee";  // form page with errors
        }
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

}
