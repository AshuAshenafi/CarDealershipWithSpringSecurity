package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CarRepository extends CrudRepository<Car, Long> {
    @Override
    public Set<Car> findAll();

    public Car findByCarId(long id);
}
