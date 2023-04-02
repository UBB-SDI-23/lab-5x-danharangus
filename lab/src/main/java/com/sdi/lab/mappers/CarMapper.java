package com.sdi.lab.mappers;

import com.sdi.lab.model.Car;
import com.sdi.lab.model.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    CarDTO carToCarDTO(Car car);
    Car carDTOToCar(CarDTO carDTO);
}
