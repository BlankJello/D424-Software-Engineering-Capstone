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



    // Delete method
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return "redirect:/mainscreen";
        }

        model.addAttribute("employee", employee);
        return "editemployee";
    }

    // Update employee
    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") long id,
                                 @Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editemployee";
        }
        employee.setId(id);
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    //error message for new employees
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
