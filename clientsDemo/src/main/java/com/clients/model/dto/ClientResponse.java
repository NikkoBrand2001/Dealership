package com.clients.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientResponse {
    private Long id;
    private String name; 
    private String dir; 
    private String email;
    private Long idCar;
}
