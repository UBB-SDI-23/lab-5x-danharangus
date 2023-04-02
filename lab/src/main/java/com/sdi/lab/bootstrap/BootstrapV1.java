package com.sdi.lab.bootstrap;

import com.sdi.lab.model.Car;
import com.sdi.lab.model.CarDealership;
import com.sdi.lab.repository.CarDealershipRepository;
import com.sdi.lab.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapV1 implements CommandLineRunner {
    private final CarRepository carRepository;
    private final CarDealershipRepository carDealershipRepository;

    @Autowired
    public BootstrapV1(CarRepository carRepository, CarDealershipRepository carDealershipRepository) {
        this.carRepository = carRepository;
        this.carDealershipRepository = carDealershipRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*CarDealership carDealership = new CarDealership();
        carDealership.setName("BMW Dealership");
        carDealership.setAddress("Sofia, Bulgaria");
        carDealership.setPhone("0888888888");
        carDealership.setEmailAddress("bmwdealership@gmail.com");
        carDealership.setFoundingYear(2019);

        carDealershipRepository.save(carDealership);

        Car car = new Car();
        car.setMake("BMW");
        car.setModel("X5");
        car.setYear(2019);
        car.setPrice(100000.00);
        car.setIsElectric(false);
        car.setCarDealership(carDealership);
        carRepository.save(car);*/
    }
}