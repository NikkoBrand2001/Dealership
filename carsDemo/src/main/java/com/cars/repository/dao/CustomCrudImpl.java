package com.cars.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import com.cars.model.entity.Car;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomCrudImpl implements CustomCrud {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Car> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        String jpql = "SELECT c FROM Car c WHERE c.price BETWEEN :minPrice AND :maxPrice ORDER BY c.price ASC";
        
        TypedQuery<Car> query = entityManager.createQuery(jpql, Car.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }
    
}
