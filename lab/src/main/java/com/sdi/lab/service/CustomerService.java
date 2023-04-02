package com.sdi.lab.service;

import com.sdi.lab.mappers.CarMapper;
import com.sdi.lab.mappers.CustomerMapper;
import com.sdi.lab.model.Car;
import com.sdi.lab.model.CarDTO;
import com.sdi.lab.model.CarDealership;
import com.sdi.lab.model.CustomerDTO;
import com.sdi.lab.repository.CarRepository;
import com.sdi.lab.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO).collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Integer id) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO).orElse(null);
    }

    public void addCustomer(CustomerDTO customerDTO) {
        customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
    }

    public void removeCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomerById(int id, CustomerDTO customerDTO) {
        customerDTO.setId(id);
        customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
    }
}
