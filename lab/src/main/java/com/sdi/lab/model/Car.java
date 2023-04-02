package com.sdi.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "`make`")
    private String make;

    @Column(name = "`model`")
    private String model;

    @Column(name = "`year`")
    private Integer year;

    @Column(name = "`price`")
    private Double price;

    @Column(name = "`is_electric`")
    private Boolean isElectric;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "car_dealership_id")
    private CarDealership carDealership;

    public Car(Integer id, String make, String model, Integer year, Double price, Boolean isElectric) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isElectric = isElectric;
    }

    public Car()
    {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsElectric() {
        return isElectric;
    }

    public void setIsElectric(Boolean isElectric) {
        this.isElectric = isElectric;
    }

    public CarDealership getCarDealership() {
        return carDealership;
    }

    public void setCarDealership(CarDealership carDealership) {
        this.carDealership = carDealership;
    }
}
