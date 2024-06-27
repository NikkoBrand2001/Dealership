package com.clients.repository.dao;

import java.util.List;

import com.clients.model.entity.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomCrudImpl implements CustomCrud{

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Client> findByNameAndEmail(String nombre, String email) {
        String jpql = "SELECT c FROM Client c WHERE c.name = :name AND c.email = :email";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("nombre", nombre);
        query.setParameter("email", email);
        return query.getResultList();
    }
}
