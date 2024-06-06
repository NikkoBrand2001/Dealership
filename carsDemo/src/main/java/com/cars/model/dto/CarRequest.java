package com.cars.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarRequest {
    private String brand;
    private BigDecimal price;
    private int cantUnits;
    private boolean status;
}
