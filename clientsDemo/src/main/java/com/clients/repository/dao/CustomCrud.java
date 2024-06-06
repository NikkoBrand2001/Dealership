package com.clients.repository.dao;

import java.util.List;

import com.clients.model.entity.Client;

public interface CustomCrud {
    List<Client> findByNameAndEmail(String nombre, String email);
}
