package com.sdi.lab.controller;

import com.sdi.lab.model.Car;
import com.sdi.lab.model.CarDTO;
import com.sdi.lab.model.CarDealership;
import com.sdi.lab.service.CarDealershipService;
import com.sdi.lab.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CarController {
    private CarService carService;
    private CarDealershipService carDealershipService;

    public CarController(CarService carService, CarDealershipService carDealershipService) {
        this.carService = carService;
        this.carDealershipService = carDealershipService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getCars() {
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }

    @GetMapping("/cars/year/{yearGreater}")
    public ResponseEntity<List<Car>> getCarsNewerThan(@PathVariable int yearGreater) {
        return new ResponseEntity<>(carService.getCarsNewerThan(yearGreater), HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        try {
            return new ResponseEntity<Car>(carService.getCarById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO car) {
        carService.addCar(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody CarDTO car) {
        try {
            carService.updateCarById(id, car);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable int id) {
        try {
            carService.removeCarById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cars/dealership/{carId}/{carDealershipId}")
    public void addCarToDealership(@PathVariable int carId, @PathVariable int carDealershipId) {
        CarDealership carDealership = carDealershipService.getCarDealershipById(carDealershipId);
        carService.updateCarDealership(carId, carDealership);
    }
}
