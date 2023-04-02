package com.sdi.lab.mappers;

import com.sdi.lab.model.Customer;
import com.sdi.lab.model.LoyalCustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoyaltyCustomerMapper {
    LoyaltyCustomerMapper INSTANCE = Mappers.getMapper(LoyaltyCustomerMapper.class);
    LoyalCustomerDTO customerToLoyalCustomerDTO(Customer customer);
    Customer loyalCustomerDTOToCustomer(LoyalCustomerDTO loyalCustomerDTO);
}
