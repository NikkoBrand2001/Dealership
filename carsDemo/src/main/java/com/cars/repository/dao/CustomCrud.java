package com.cars.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import com.cars.model.entity.Car;

public interface CustomCrud {
     List<Car> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
