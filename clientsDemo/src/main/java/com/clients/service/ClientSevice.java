package com.clients.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clients.model.dto.ClientRequest;
import com.clients.model.dto.ClientResponse;

@Service
public interface ClientSevice {
    List<ClientResponse> findAllClients();
    ClientResponse findClientById(Long id);
    List<ClientResponse> findByNameAndEmail(String name, String email);
    void deleteById(Long id);
    void saveClient(ClientRequest clientRequest);
}
