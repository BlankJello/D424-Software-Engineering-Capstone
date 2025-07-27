package com.example.demo.domain;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Position is required")
    private String position;

    @NotBlank(message = "Hire date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Hire date must be in yyyy-mm-dd format")
    private String hireDate;

    // Constructors, getters, setters
    public Employee() {}

    public Employee(Long id, String firstName, String lastName, String email, String position, LocalDate hireDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.hireDate = String.valueOf(hireDate);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public @NotBlank(message = "Hire date is required") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Hire date must be in yyyy-mm-dd format") String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = String.valueOf(LocalDate.parse(hireDate)); }
}
