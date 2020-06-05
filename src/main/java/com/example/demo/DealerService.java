package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealerService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Car findByCarId(long id) {
        return carRepository.findByCarId(id);
    }

    public Category findByCategoryId(long id) {
        return categoryRepository.findByCategoryId(id);
    }

    public void delete(Car theCar) {
        carRepository.delete(theCar);
    }

//    public void safeDelete1(long category_id){
////        Category category = categoryRepository.findByCategoryId(category_id);
//        Category category = categoryRepository.findById(category_id).get();
//        for(Car car : carRepository.findAll()){
//            if(car.getCategory().contains(category)){
//
////                car.setCategory(null);
//                System.out.println("From line 32 (Dealer Service) Cars in the same category are: " );
//            }
//        }
//    }
//
    public void deleteCarsWithThisCategoryId(long categ_id) {

        // get all the cars in the repository
        for(Car car : carRepository.findAll()){
            // get all cars whose foreign key is equal to the current category
            if(car.getCategory().getCategoryId() == categ_id){
//                System.out.println("car model = " + car.getModel() + ", car manufacturor = " + car.getManufacturer());
                // delete the car whose foreign key mathces the current category
                carRepository.delete(car);
            }
        }
    }



}
