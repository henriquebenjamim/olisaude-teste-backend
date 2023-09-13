package com.olisaude.backenddevchallenger.services;

import com.olisaude.backenddevchallenger.domain.Client;
import com.olisaude.backenddevchallenger.dtos.ClientDTO;
import com.olisaude.backenddevchallenger.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client findClientById(Long id) throws Exception {
        return this.repository.findClientById(id).orElseThrow(() -> new Exception("Client not found."));
    }

    public List<Client> getAll() {
        return this.repository.findAll();
    }

    public Client createClient(ClientDTO data) {
        Client newClient = new Client(data);
        newClient.setCreationDate(LocalDateTime.now());
        this.saveClient(newClient);
        return newClient;
    }

    public Client saveClient(Client client) {
       return repository.save(client);
    }
}
