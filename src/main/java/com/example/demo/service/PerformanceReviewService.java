package com.example.demo.service;

import com.example.demo.domain.PerformanceReview;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.PerformanceReviewRepository;
import com.example.demo.validators.PerformanceReviewValidator;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PerformanceReviewService {
    private final PerformanceReviewRepository reviewRepository;
    private final EmployeeRepository employeeRepository;

    public PerformanceReviewService(PerformanceReviewRepository reviewRepository, EmployeeRepository employeeRepository) {
        this.reviewRepository = reviewRepository;
        this.employeeRepository = employeeRepository;
    }

    public PerformanceReview addReview(PerformanceReview review) {
        PerformanceReviewValidator.validate(review);
        if (!employeeRepository.findById(review.getEmployeeId()).isPresent()) {
            throw new IllegalArgumentException("Employee does not exist.");
        }
        return reviewRepository.save(review);
    }

    public Optional<PerformanceReview> getReview(Long id) {
        return reviewRepository.findById(id);
    }

    public List<PerformanceReview> getReviewsByEmployee(Long employeeId) {
        return reviewRepository.findByEmployeeId(employeeId);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
