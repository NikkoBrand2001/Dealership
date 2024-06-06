package com.cars.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cars.model.dto.CarRequest;
import com.cars.model.dto.CarResponse;

@Service
public interface CarService {
    
 List<CarResponse> getAllCars();
 CarResponse finById(Long carId);
 List<CarResponse> findCarsInRange(BigDecimal minPrice, BigDecimal maxPrice);
 void deleteCar(Long idCar);
 void saveCar(CarRequest carRequest);

    
}
