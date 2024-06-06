package com.cars.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cars.model.entity.Car;
import com.cars.repository.dao.CustomCrud;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> , CustomCrud{

} 
