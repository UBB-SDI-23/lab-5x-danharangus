package com.sdi.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "`first_name`")
    private String firstName;

    @Column(name = "`last_name`")
    private String lastName;

    @Column(name = "`age`")
    private Integer age;

    @Column(name = "`email`")
    private String email;

    @Column(name = "`phone_number`")
    private String phoneNumber;


    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<LoyalCustomers> loyalCustomers;

    public List<LoyalCustomers> getLoyalCustomers() {
        return loyalCustomers;
    }

    public void setLoyalCustomers(List<LoyalCustomers> loyalCustomers) {
        this.loyalCustomers = loyalCustomers;
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
