package com.example.demo.bootstrap;

import com.example.demo.domain.Employee;
import com.example.demo.domain.PerformanceReview;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.PerformanceReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewRepository performanceReviewRepository;

    public BootStrapData(EmployeeRepository employeeRepository, PerformanceReviewRepository performanceReviewRepository) {
        this.employeeRepository = employeeRepository;
        this.performanceReviewRepository = performanceReviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (employeeRepository.count() == 0) {
            // Create sample employees
            Employee emp1 = new Employee(null, "John", "Doe", "john.doe@example.com", "Software Engineer", LocalDate.of(2020, 1, 15));
            Employee emp2 = new Employee(null, "Jane", "Smith", "jane.smith@example.com", "Project Manager", LocalDate.of(2018, 6, 1));

            employeeRepository.save(emp1);
            employeeRepository.save(emp2);

            // Create sample performance reviews
            PerformanceReview review1 = new PerformanceReview(null, emp1.getId(), LocalDate.of(2023, 12, 1), "Manager A", "Excellent work on the recent project.", 5);
            PerformanceReview review2 = new PerformanceReview(null, emp2.getId(), LocalDate.of(2023, 11, 15), "Manager B", "Strong leadership skills.", 4);

            performanceReviewRepository.save(review1);
            performanceReviewRepository.save(review2);

            // Print all employees
            List<Employee> employees = employeeRepository.findAll();
            System.out.println("Employees in system:");
            for (Employee e : employees) {
                System.out.println(e.getId() + ": " + e.getFirstName() + " " + e.getLastName() + " - " + e.getPosition());
            }

            // Print all performance reviews
            List<PerformanceReview> reviews = performanceReviewRepository.findAll();
            System.out.println("Performance Reviews in system:");
            for (PerformanceReview r : reviews) {
                System.out.println("Review ID: " + r.getId() + ", Employee ID: " + r.getEmployeeId() + ", Rating: " + r.getRating() + ", Comments: " + r.getComments());
            }

            System.out.println("Bootstrap data loaded successfully.");
        }
    }
}
