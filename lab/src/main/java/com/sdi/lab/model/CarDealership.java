package com.sdi.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity

public class CarDealership {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "`name`")
        private String name;

        @Column(name = "`address`")
        private String address;

        @Column(name = "`phone`")
        private String phone;

        @Column(name = "`founding_year`")
        private Integer foundingYear;

        @Column(name = "`email_address` ")
        private String emailAddress;

        @JsonIgnore
        @OneToMany(mappedBy = "carDealership")
        private List<Car> cars;

    @JsonIgnore
    @OneToMany(mappedBy = "carDealership", cascade = CascadeType.ALL)
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

        public List<Car> getCars() {
            return cars;
        }

        public void setCars(List<Car> cars) {
            this.cars = cars;
        }
}
