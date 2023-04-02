package com.sdi.lab.controller;

import com.sdi.lab.mappers.CarMapper;
import com.sdi.lab.model.*;
import com.sdi.lab.service.CarDealershipService;
import com.sdi.lab.service.CarService;
import com.sdi.lab.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/car-dealerships")
public class CarDealershipController {
    private CarDealershipService carDealershipService;
    CustomerService customerService;
    CarService carService;

    @Autowired
    public CarDealershipController(CarDealershipService carDealershipService, CustomerService customerService,
                                   CarService carService) {
        this.carDealershipService = carDealershipService;
        this.customerService = customerService;
        this.carService = carService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDealership> getCarDealerships() {
        return carDealershipService.getCarDealerships();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDealership addCarDealership(@RequestBody CarDealership carDealership) {
        carDealershipService.addCarDealership(carDealership);
        return carDealership;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDealership getCarDealershipById(@PathVariable int id) {
        return carDealershipService.getCarDealershipById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDealership updateCarDealership(@PathVariable int id, @RequestBody CarDealership carDealership) {
        carDealershipService.updateCarDealershipById(id, carDealership);
        return carDealership;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCarDealership(@PathVariable int id) {
        carDealershipService.removeCarDealershipById(id);
    }

    @GetMapping("/{id}/cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getDealershipCars(@PathVariable int id) {
        return carDealershipService.getDealershipCars(id);
    }

    @GetMapping("/{id}/loyal-customers/")
    @ResponseStatus(HttpStatus.CREATED)
    public List<LoyalCustomerDTO> getLoyalCustomers(@PathVariable Integer id) {
        CarDealership carDealership = carDealershipService.getCarDealershipById(id);
        List<LoyalCustomers> loyalCustomers = carDealership.getLoyalCustomers();
        List<LoyalCustomerDTO> customers = new ArrayList<>();
        for (LoyalCustomers loyalCustomer : loyalCustomers) {
            LoyalCustomerDTO customer = new LoyalCustomerDTO();
            customer.setId(loyalCustomer.getCustomer().getId());
            customer.setFirstName(loyalCustomer.getCustomer().getFirstName());
            customer.setLastName(loyalCustomer.getCustomer().getLastName());
            customer.setAge(loyalCustomer.getCustomer().getAge());
            customer.setEmail(loyalCustomer.getCustomer().getEmail());
            customer.setPhoneNumber(loyalCustomer.getCustomer().getPhoneNumber());
            customer.setRegistrationYear(loyalCustomer.getRegistrationYear());
            customer.setLoyaltyPoints(loyalCustomer.getLoyaltyPoints());
            customers.add(customer);
        }
        return customers;
    }


    @PostMapping("/loyal-customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addLoyalCustomer(@RequestBody LoyalCustomerRelationDTO loyalCustomer) {
        CarDealership carDealership = carDealershipService.getCarDealershipById(loyalCustomer.getCarDealershipId());
        CustomerDTO customer = customerService.getCustomerById(loyalCustomer.getCustomerId());
        carDealershipService.addLoyalCustomer(carDealership, customer,
                loyalCustomer.getRegistrationYear(), loyalCustomer.getLoyaltyPoints());
    }

    @GetMapping("/avg-customer-age")
    public List<CarDealershipDTO> getCarDealershipsByAvgCustomerAge() {
        List<CarDealership> carDealerships = carDealershipService.getCarDealerships();
        List<CarDealershipDTO> carDealershipDTOs = new ArrayList<>();

        for (CarDealership carDealership : carDealerships) {
            List<LoyalCustomers> loyalCustomers = carDealership.getLoyalCustomers();
            double avgCustomerAge = loyalCustomers.stream()
                    .mapToInt(lc -> lc.getCustomer().getAge()).average().orElse(0);
            CarDealershipDTO carDealershipDTO = new CarDealershipDTO();
            carDealershipDTO.setId(carDealership.getId());
            carDealershipDTO.setName(carDealership.getName());
            carDealershipDTO.setAddress(carDealership.getAddress());
            carDealershipDTO.setPhone(carDealership.getPhone());
            carDealershipDTO.setEmailAddress(carDealership.getEmailAddress());
            carDealershipDTO.setFoundingYear(carDealership.getFoundingYear());
            carDealershipDTO.setAvgCustomerAge(avgCustomerAge);
            carDealershipDTOs.add(carDealershipDTO);
        }

        carDealershipDTOs.sort(Comparator.comparingDouble(CarDealershipDTO::getAvgCustomerAge));

        return carDealershipDTOs;
    }

    @GetMapping("/avg-car-price")
    public List<CarDealershipDTO> getCarDealershipsByAvgCarPrice() {
        List<CarDealership> carDealerships = carDealershipService.getCarDealerships();
        List<CarDealershipDTO> carDealershipDTOs = new ArrayList<>();

        for (CarDealership carDealership : carDealerships) {
            List<Car> cars = carDealership.getCars();
            double avgCarPrice = cars.stream().mapToDouble(Car::getPrice).average().orElse(0);
            CarDealershipDTO carDealershipDTO = new CarDealershipDTO();
            carDealershipDTO.setId(carDealership.getId());
            carDealershipDTO.setName(carDealership.getName());
            carDealershipDTO.setAddress(carDealership.getAddress());
            carDealershipDTO.setPhone(carDealership.getPhone());
            carDealershipDTO.setEmailAddress(carDealership.getEmailAddress());
            carDealershipDTO.setFoundingYear(carDealership.getFoundingYear());
            carDealershipDTO.setAvgCarPrice(avgCarPrice);
            carDealershipDTOs.add(carDealershipDTO);
        }

        carDealershipDTOs.sort(Comparator.comparingDouble(CarDealershipDTO::getAvgCarPrice));

        return carDealershipDTOs;
    }

    @PostMapping("/{id}/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@PathVariable int id, @RequestBody List<Car> cars) {
        CarDealership carDealership = carDealershipService.getCarDealershipById(id);
        for (Car car : cars) {
            carService.updateCarDealership(car.getId(), carDealership);
        }
    }
}
