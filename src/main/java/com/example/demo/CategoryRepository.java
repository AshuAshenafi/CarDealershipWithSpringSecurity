package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Override
    public Set<Category> findAll();

//    Car findByCarId(long id);

    Category findByCategoryId(long id);
}
