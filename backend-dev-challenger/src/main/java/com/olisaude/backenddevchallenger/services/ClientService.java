package com.olisaude.backenddevchallenger.services;

import com.olisaude.backenddevchallenger.domain.Client;
import com.olisaude.backenddevchallenger.dtos.ClientDTO;
import com.olisaude.backenddevchallenger.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    private Client findClientById(String id) throws Exception {
        return this.repository.findClientById(id).orElseThrow(() -> new Exception("Client not found."));
    }

    public List<Client> getAll() {
        return this.repository.findAll();
    }

    public Client createClient(ClientDTO data) {
        Client newClient = new Client(data);
        this.saveClient(newClient);
        return newClient;
    }

    public void saveClient(Client client) {
        this.repository.save(client);
    }
}
