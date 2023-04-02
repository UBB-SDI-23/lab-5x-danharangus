package com.sdi.lab.model;

import jakarta.persistence.Column;
import net.minidev.json.annotate.JsonIgnore;

public class LoyalCustomerDTO {
    private Integer id;
    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private String phoneNumber;

    private Integer registrationYear;

    private Integer loyaltyPoints;

    @JsonIgnore
    private Integer carDealershipId;

    public Integer getCarDealershipId() {
        return carDealershipId;
    }

    public void setCarDealershipId(Integer carDealershipId) {
        this.carDealershipId = carDealershipId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
