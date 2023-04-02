package com.sdi.lab.model;

import jakarta.persistence.*;

@Entity
public class LoyalCustomers {
    @EmbeddedId
    private LoyalCustomersKey id;

    private Integer loyaltyPoints;
    private Integer registrationYear;

    @ManyToOne
    @MapsId("dealershipId")
    CarDealership carDealership;

    @ManyToOne
    @MapsId("customerId")
    Customer customer;

    public LoyalCustomersKey getId() {
        return id;
    }

    public void setId(LoyalCustomersKey id) {
        this.id = id;
    }

    public Integer getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(Integer registrationYear) {
        this.registrationYear = registrationYear;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public LoyalCustomersKey getKey() {
        return id;
    }

    public void setKey(LoyalCustomersKey id) {
        this.id = id;
    }

    public CarDealership getCarDealership() {
        return carDealership;
    }

    public void setCarDealership(CarDealership carDealership) {
        this.carDealership = carDealership;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
