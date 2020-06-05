package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;
    @Override
    public void run (String... strings) throws Exception {

        Set<Car> cars = new HashSet<>();
        Set<Category> categories = new HashSet<>();

        Category category = new Category();

        Category category1 = new Category("Sedan");
        Category category2 = new Category("SUV");
        Category category3 = new Category("Sports Car");



        Car car1 = new Car("Toyota", "Camry", 2020, "brand new car");
        Car car2 = new Car("Toyota", "Highlander", 2009, "used car");
        Car car3 = new Car("Subaru", "Outback", 2010, "best seller listed");
        Car car4 = new Car("Jeep", "Wrangler", 2005, "brand new car");
        Car car5 = new Car("BMW", "Wallpaper", 2020, "best seller listed");
        Car car6 = new Car("Toyota", "Sequola", 2010, "brand new car");
        Car car7 = new Car("Jeep", "Concept", 2020, "best seller listed");
        Car car8 = new Car("Toyota", "Corolla", 2005, "brand new car");
        Car car9 = new Car("Toyota", "RAV4", 2015, "best seller listed");



        Set<Car> cars1 = new HashSet<>();
        // sedan
        cars1.add(car1);
        cars1.add(car6);
        cars1.add(car8);
        //suv
        Set<Car> cars2 = new HashSet<>();
        cars2.add(car2);
        cars2.add(car3);
        cars2.add(car9);
        //sports car
        Set<Car> cars3 = new HashSet<>();
        cars3.add(car4);
        cars3.add(car7);
        cars3.add(car5);

        category1.setCars(cars1);
        category2.setCars(cars2);
        category3.setCars(cars3);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        car1.setCategory(category1);
        car6.setCategory(category1);
        car8.setCategory(category1);

        car2.setCategory(category2);
        car3.setCategory(category2);
        car9.setCategory(category2);

        car4.setCategory(category3);
        car7.setCategory(category3);
        car5.setCategory(category3);

        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
        carRepository.save(car7);
        carRepository.save(car8);
        carRepository.save(car9);

    }
}
