package com.sdi.lab.service;

import com.sdi.lab.mappers.CarMapper;
import com.sdi.lab.model.Car;
import com.sdi.lab.model.CarDTO;
import com.sdi.lab.model.CarDealership;
import com.sdi.lab.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private CarRepository carRepository;
    private CarMapper carMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<CarDTO> getCars() {
        return carRepository.findAll().stream().map(carMapper::carToCarDTO).collect(Collectors.toList());
    }

    public void addCar(CarDTO car) {
        carRepository.save(carMapper.carDTOToCar(car));
    }

    public void removeCarById(int id) {
        carRepository.deleteById(id);
    }

    public Car getCarById(int id)
    {
        return carRepository.findById(id).orElse(null);
    }

    public void updateCarById(int id, CarDTO newCar) {
        Car car = carMapper.carDTOToCar(newCar);
        car.setId(id);
        carRepository.save(car);
    }

    public List<Car> getCarsNewerThan(int year) {
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(car -> car.getYear() > year).collect(Collectors.toList());
    }

    public void updateCarDealership(int id, CarDealership carDealership) {
        Car car = carRepository.findById(id).orElse(null);
        car.setCarDealership(carDealership);
        carRepository.save(car);
    }
}