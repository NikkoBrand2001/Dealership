package com.clients.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clients.model.dto.ClientRequest;
import com.clients.model.dto.ClientResponse;
import com.clients.service.ClientSevice;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientSevice clientSevice;

    @GetMapping("/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getAllClients(){
        return clientSevice.findAllClients();
    }

    @GetMapping("/find-by.id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse getById(@PathVariable Long id){
        return clientSevice.findClientById(id); 
    }

    @GetMapping("/find-by-name-email/{name}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getByNameAndEmail(String name, String email){
        return clientSevice.findByNameAndEmail(name, email);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        clientSevice.deleteById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody ClientRequest clientRequest){
        clientSevice.saveClient(clientRequest);
    }
}
