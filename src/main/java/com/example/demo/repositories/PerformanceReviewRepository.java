package com.example.demo.repositories;

import com.example.demo.domain.PerformanceReview;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {
    PerformanceReview save(PerformanceReview review);
    Optional<PerformanceReview> findById(Long id);
    List<PerformanceReview> findByEmployeeId(Long employeeId);
    List<PerformanceReview> findAll();
    void deleteById(Long id);
}
