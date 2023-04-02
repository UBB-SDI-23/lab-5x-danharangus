package com.sdi.lab.model;

import jakarta.persistence.Column;

public class CarDealershipDTO {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Integer foundingYear;
    private String emailAddress;
    private Double avgCustomerAge;

    private Double avgCarPrice;

    public Double getAvgCarPrice() {
        return avgCarPrice;
    }

    public void setAvgCarPrice(Double avgCarPrice) {
        this.avgCarPrice = avgCarPrice;
    }
    public Double getAvgCustomerAge() {
        return avgCustomerAge;
    }

    public void setAvgCustomerAge(Double avgCustomerAge) {
        this.avgCustomerAge = avgCustomerAge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(Integer foundingYear) {
        this.foundingYear = foundingYear;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}