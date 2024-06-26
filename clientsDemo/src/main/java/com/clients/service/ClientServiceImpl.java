package com.clients.service;

import java.util.List;
import java.util.Optional;

import com.clients.model.dto.ClientRequest;
import com.clients.model.dto.ClientResponse;
import com.clients.model.entity.Client;
import com.clients.repository.ClientRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public List<ClientResponse> findAllClients() {
      List<Client> list = clientRepository.findAll();
      if(list.isEmpty()){
        throw new RuntimeException("the List is empty");
      }

    return list.stream().map(this::mapToClientResponse).toList();
    }

    @Override
    public ClientResponse findClientById(Long id) {
       Optional<Client> clientOptional = clientRepository.findById(id);

       return clientOptional.map(this::mapToClientResponse).orElseThrow(()-> new RuntimeException("the id is invalid"));
    }

    @Override
    public List<ClientResponse> findByNameAndEmail(String name, String email) {
      List<Client> list = clientRepository.findByNameAndEmail(name, email);
      if(list.isEmpty()){
        throw new RuntimeException("the list is empty");
      }   
      return list.stream().map(this::mapToClientResponse).toList(); 
    }

    @Override
    public void deleteById(@NonNull Long id) {

       clientRepository.deleteById(id);
    }

    @Override
    public void saveClient(ClientRequest clientRequest) {
        if(clientRequest == null){
            throw new RuntimeException("request cannot be null");
        }
        Client client = Client.builder()
        .name(clientRequest.getName())
        .dir(clientRequest.getDir())
        .email(clientRequest.getEmail())
        .idCar(clientRequest.getIdCar())
        .build();
        clientRepository.save(client);
    }

    private ClientResponse mapToClientResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .dir(client.getDir())
                .email(client.getEmail())
                .idCar(client.getIdCar())
                .build();
    }
}
