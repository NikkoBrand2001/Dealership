package com.cars.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cars.model.dto.CarRequest;
import com.cars.model.dto.CarResponse;
import com.cars.service.CarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/all-cars")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/find-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getCarById(@PathVariable Long id){
        return carService.finById(id);
    }

    @GetMapping("/find-in-range/{minPrice}/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getCarInRange(@PathVariable BigDecimal minPrice, @PathVariable BigDecimal maxPrice){
        return carService.findCarsInRange(minPrice, maxPrice);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        carService.deleteCar(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCar(@RequestBody CarRequest carRequest){
        carService.saveCar(carRequest);
    }
}
