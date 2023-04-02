package com.sdi.lab.controller;

import com.sdi.lab.model.CarDealership;
import com.sdi.lab.model.Customer;
import com.sdi.lab.model.CustomerDTO;
import com.sdi.lab.service.CarDealershipService;
import com.sdi.lab.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService, CarDealershipService carDealershipService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customer) {
        customerService.addCustomer(customer);
        return customer;
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customer) {
        customerService.updateCustomerById(id, customer);
        return customer;
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int id) {
        customerService.removeCustomerById(id);
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }
}