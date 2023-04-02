package com.sdi.lab.service;

import com.sdi.lab.mappers.CarMapper;
import com.sdi.lab.mappers.CustomerMapper;
import com.sdi.lab.model.*;
import com.sdi.lab.repository.CarDealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarDealershipService {
    private CarDealershipRepository carDealershipRepository;
    private CustomerMapper customerMapper;

    private CarMapper carMapper;

    @Autowired
    public CarDealershipService(CarDealershipRepository carDealershipRepository, CustomerMapper customerMapper,
                                CarMapper carMapper) {
        this.carDealershipRepository = carDealershipRepository;
        this.customerMapper = customerMapper;
        this.carMapper = carMapper;
    }

    public List<CarDealership> getCarDealerships() {
        return carDealershipRepository.findAll();
    }

    public void addCarDealership(CarDealership carDealership) {
        carDealershipRepository.save(carDealership);
    }

    public void removeCarDealershipById(int id) {
        carDealershipRepository.deleteById(id);
    }

    public void updateCarDealershipById(int id, CarDealership newCarDealership) {
        newCarDealership.setId(id);
        carDealershipRepository.save(newCarDealership);
    }

    public CarDealership getCarDealershipById(int id) {
        return carDealershipRepository.findById(id).orElse(null);
    }

    public List<Car> getDealershipCars(int id) {
        Optional<CarDealership> carDealership = carDealershipRepository.findById(id);
        if (carDealership.isPresent()) {
            return carDealership.get().getCars();
        }
        return null;
    }

    public void addLoyalCustomer(CarDealership carDealership, CustomerDTO customer,
                                 Integer registrationYear, Integer loyaltyPoints) {
        LoyalCustomersKey key = new LoyalCustomersKey();
        key.setDealershipId(carDealership.getId());
        key.setCustomerId(customer.getId());
        LoyalCustomers loyalCustomers = new LoyalCustomers();
        loyalCustomers.setKey(key);
        loyalCustomers.setCarDealership(carDealership);
        loyalCustomers.setCustomer(customerMapper.customerDTOToCustomer(customer));
        loyalCustomers.setRegistrationYear(registrationYear);
        loyalCustomers.setLoyaltyPoints(loyaltyPoints);
        carDealership.getLoyalCustomers().add(loyalCustomers);
        carDealershipRepository.save(carDealership);
    }

    public void addCars(Integer carDealershipId, List<CarDTO> cars) {
        CarDealership carDealership = carDealershipRepository.findById(carDealershipId).orElse(null);
        for (CarDTO carDTO : cars) {
            Car car = carMapper.carDTOToCar(carDTO);
            car.setCarDealership(carDealership);
            carDealership.getCars().add(car);
        }
        carDealershipRepository.save(carDealership);
    }
}
