package com.example.demo.validators;

import com.example.demo.domain.PerformanceReview;

public class PerformanceReviewValidator {
    public static void validate(PerformanceReview review) {
        if (review.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID is required.");
        }
        if (review.getReviewDate() == null) {
            throw new IllegalArgumentException("Review date is required.");
        }
        if (review.getReviewer() == null || review.getReviewer().isEmpty()) {
            throw new IllegalArgumentException("Reviewer name is required.");
        }
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
    }
}
