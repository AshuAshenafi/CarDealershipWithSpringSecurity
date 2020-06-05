package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carId;

    @NotBlank(message = "Must give Manufacturer!")
    @Size(min=2, max=50)
    private String manufacturer;

    @NotBlank(message="Must give model!")
    @Size(min=2, max=50)
    private String model;

    @NotNull
    @Min(1800)
    @Max(2040)
    private int year;

    @NotBlank(message="Must give description!")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    public Car() {
    }

//    public Car(@NotBlank(message = "Must give Manufacturer!") @Size(min = 2, max = 50) String manufacturer,
//               @NotBlank(message = "Must give model!") @Size(min = 2, max = 50) String model,
//               @NotNull @Min(1800) @Max(2040) int year, @NotBlank(message = "Must give description!")
//                       String description, Category category) {
//        super();
//        this.manufacturer = manufacturer;
//        this.model = model;
//        this.year = year;
//        this.description = description;
//        this.category = category;
//    }


        public Car(String manufacturer, String model, int year, String description) {
        super();
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.description = description;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
