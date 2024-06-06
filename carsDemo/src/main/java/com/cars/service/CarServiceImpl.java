package com.cars.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cars.model.dto.CarRequest;
import com.cars.model.dto.CarResponse;
import com.cars.model.entity.Car;
import com.cars.repository.CarRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public List<CarResponse> getAllCars() {
        List<Car> list = carRepository.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("the list is empty");
        }
        return list.stream().map(this::mapToCarResponses).toList();
    }

    @Override
    public CarResponse finById(Long carId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        return carOptional.map(this::mapToCarResponses).orElseThrow(() -> new RuntimeException("id is invalid"));
    }

    @Override
    public List<CarResponse> findCarsInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Car> list = carRepository.findByPriceBetween(minPrice, maxPrice);
        if (list.isEmpty()) {
            throw new RuntimeException("the list is Empty");
        }
        return list.stream().map(this::mapToCarResponses).toList();
    }

    @Override
    public void deleteCar(Long idCar) {
        if (idCar == null && idCar == 0) {
            throw new RuntimeException("invalid id.");
        }
        carRepository.deleteById(idCar);
    }

    @Override
    public void saveCar(CarRequest carRequest) {
        if (carRequest == null) {
            throw new RuntimeException("the parameters cannot be null");
        }

        Car car = Car.builder()
                .brand(carRequest.getBrand())
                .price(carRequest.getPrice())
                .cantUnits(carRequest.getCantUnits())
                .status(carRequest.isStatus())
                .build();

        carRepository.save(car);
    }

    private CarResponse mapToCarResponses(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .price(car.getPrice())
                .cantUnits(car.getCantUnits())
                .status(car.isStatus())
                .build();
    }
}
