package com.example.demo.repositories;

import com.example.demo.domain.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.query.FluentQuery;
import com.example.demo.repositories.EmployeeRepository;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private Map<Long, Employee> store = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(idGenerator.getAndIncrement());
        }
        store.put(employee.getId(), employee);
        return employee;
    }


    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Employee> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void flush() {
    }

    @Override
    public <S extends Employee> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Employee> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Employee getOne(Long aLong) {
        return null;
    }

    @Override
    public Employee getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Employee> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Employee> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Employee> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Employee, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPositionContainingIgnoreCase(String firstName, String lastName, String email, String position) {
        return List.of();
    }

    @Override
    public List<Employee> search(String keyword) {
        return List.of();
    }

    @Override
    public Optional<Object> findById(SingularAttribute<AbstractPersistable, Serializable> id) {
        return Optional.empty();
    }

    @Override
    public void delete(Employee entity) {
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
    }

    @Override
    public void deleteAll(Iterable<? extends Employee> entities) {
    }

    @Override
    public void deleteAll() {
    }
}
