package com.example.demo.service;

import com.example.demo.domain.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.validators.EmployeeValidator;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class EmployeeService implements EmployeeServicelmpl {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        EmployeeValidator.validate(employee);
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee ID is required for update.");
        }
        EmployeeValidator.validate(employee);
        return employeeRepository.save(employee);
    }

    public Employee findById(long theId) {
        return (Employee) employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> search(String keyword) {
        // Implement search logic, e.g. search by first name, last name, email, or position
        return employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPositionContainingIgnoreCase(
                keyword, keyword, keyword, keyword);
    }


    public List<Employee> findAll() {
        return List.of();
    }

    public interface employeeService {
        public List<Employee> findAll();

        public Employee findById(long theId);

        public void save(Employee theEmployee);

        public void deleteById(long theId);

        public List<Employee> listAll(String keyword);
    }


    @Override
    public void deleteById(long theId) {
            Long theIdl = (long) theId;
            employeeRepository.deleteById(theIdl);
        }
        public List<Employee> listAll (String keyword){
            if (keyword != null) {
                return employeeRepository.search(keyword);
            }
            return (List<Employee>) employeeRepository.findAll();
        }
    }