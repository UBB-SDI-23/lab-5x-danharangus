package com.sdi.lab.model;

public class CarDTO {
    private Integer id;
    private String make;
    private String model;
    private Integer year;
    private Double price;
    private Boolean isElectric;

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

    public void setIsElectric(Boolean electric) {
        isElectric = electric;
    }
}
