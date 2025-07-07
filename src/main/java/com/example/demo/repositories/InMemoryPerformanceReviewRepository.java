package com.example.demo.repositories;

import com.example.demo.repositories.PerformanceReviewRepository;
import com.example.demo.domain.PerformanceReview;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryPerformanceReviewRepository implements PerformanceReviewRepository {
    private Map<Long, PerformanceReview> store = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public PerformanceReview save(PerformanceReview review) {
        if (review.getId() == null) {
            review.setId(idGenerator.getAndIncrement());
        }
        store.put(review.getId(), review);
        return review;
    }

    @Override
    public Optional<PerformanceReview> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<PerformanceReview> findByEmployeeId(Long employeeId) {
        return store.values().stream()
                .filter(r -> r.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PerformanceReview> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<PerformanceReview> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<PerformanceReview> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<PerformanceReview> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends PerformanceReview> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends PerformanceReview> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends PerformanceReview> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<PerformanceReview> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public PerformanceReview getOne(Long aLong) {
        return null;
    }

    @Override
    public PerformanceReview getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends PerformanceReview> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends PerformanceReview> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends PerformanceReview> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends PerformanceReview> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends PerformanceReview> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends PerformanceReview> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends PerformanceReview, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public void delete(PerformanceReview entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PerformanceReview> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
