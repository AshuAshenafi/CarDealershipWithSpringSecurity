package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    DealerService dealerService;

    @RequestMapping("/")
    public String displayHome(Model model) {
        Set<Category> categories = categoryRepository.findAll();
        Set<Car> cars = carRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("cars", cars);
        return "home";
    }

    @GetMapping("/new-car")
    public String displayCarForm(Model model) {
        Car aCar = new Car();

        Set<Category> categories = categoryRepository.findAll();
        model.addAttribute("allCategories", categories);

        model.addAttribute("car", aCar);
        return "new-car";
    }

    @PostMapping("/save-car")
    public String createCar(Model model, @Valid Car car, Errors errors) {
        if(errors.hasErrors()){
            return "/new-car";
        }
        carRepository.save(car);
        return "redirect:/new-car";
    }

    @GetMapping("/list-cars")
    public String displayCars(Model model) {
        Set<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "list-cars";
    }

    @GetMapping("/detail-car")
    public String displayCarDetailForm(@RequestParam("id") long theId, Model model) {
        Car theCar = dealerService.findByCarId(theId);
        model.addAttribute("car", theCar);
        return "car-detail";
    }

    @GetMapping("/update-car")
    public String displayCarUpdateForm(@RequestParam("id") long theId, Model model) {
        Car theCar = dealerService.findByCarId(theId);
        model.addAttribute("car", theCar);
        return "new-car";
    }

    @GetMapping("/delete-car")
    public String deleteCar(@RequestParam("id") long theId, Model model) {
        Car theCar = dealerService.findByCarId(theId);
        dealerService.delete(theCar);
        return "redirect:/list-cars";
    }


    @GetMapping("/new-category")
    public String displayCategoryForm(Model model) {
        Category aCategory = new Category();
        model.addAttribute("category", aCategory);

        Set<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);

        return "new-category";
    }

    @PostMapping("/save-category")
    public String createCategory(Model model, @Valid Category category, Errors errors) {
        if(errors.hasErrors()){
            return "/new-category";
        }
        categoryRepository.save(category);
        return "redirect:/new-category";
    }

    @GetMapping("/list-category")
    public String displayCategories(Model model) {
        Set<Category> categories = categoryRepository.findAll();
        model.addAttribute("allCategory", categories);
        return "list-categories";
    }

    @GetMapping("/update-category")
    public String displayCategoryUpdateForm(@RequestParam("id") long theId, Model model) {
        Category theCategory = dealerService.findByCategoryId(theId);
        model.addAttribute("category", theCategory);
        return "new-category";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") long theId) {
        // delete the child cars under this category
        dealerService.deleteCarsWithThisCategoryId(theId);
        // delete category
        categoryRepository.deleteById(theId);
        return "redirect:/list-category";
    }

}
