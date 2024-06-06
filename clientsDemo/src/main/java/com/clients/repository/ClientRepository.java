package com.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clients.model.entity.Client;
import com.clients.repository.dao.CustomCrud;

public interface ClientRepository extends JpaRepository<Client, Long>, CustomCrud {
    
}
