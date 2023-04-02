package com.sdi.lab.mappers;

import com.sdi.lab.model.Car;
import com.sdi.lab.model.CarDTO;
import com.sdi.lab.model.Customer;
import com.sdi.lab.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}