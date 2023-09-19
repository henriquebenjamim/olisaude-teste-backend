package com.olisaude.backenddevchallenger.controllers;

import com.olisaude.backenddevchallenger.domain.Client;
import com.olisaude.backenddevchallenger.domain.HealthProblem;
import com.olisaude.backenddevchallenger.dtos.ClientDTO;
import com.olisaude.backenddevchallenger.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import com.olisaude.backenddevchallenger.repositories.HealthProblemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private HealthProblemRepository healthProblemRepository;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO) {
        // You can retrieve the healthProblemId from the clientDTO
        Long healthProblemId = clientDTO.healthProblemId();

        // Use the ID to fetch the corresponding HealthProblem entity from your repository
        HealthProblem healthProblem = healthProblemRepository.findById(healthProblemId)
                .orElseThrow(() -> new EntityNotFoundException("HealthProblem not found with ID: " + healthProblemId));

        // Create a new Client entity and set its associated HealthProblem
        Client client = new Client(clientDTO);
        client.setHealthProblem(healthProblem);

        // Save the Client entity
        Client savedClient = clientService.createClient(client);

        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<Client> Post(@RequestBody ClientDTO client) {
//        Client newClient = clientService.createClient(client);
//        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<List<Client>> Get() {
        List<Client> clients = this.clientService.getAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Client GetClientById(@PathVariable Long id) throws Exception {
        return clientService.findClientById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(
            @PathVariable Long id,
            @RequestBody ClientDTO updatedClientDTO
    ) {
        try {
            if (updatedClientDTO == null) {
                return ResponseEntity.badRequest().body("Request body is required.");
            }

            // Check if the client with the given ID exists
            Client existingClient = clientService.findClientById(id);

            if (existingClient == null) {
                return ResponseEntity.notFound().build();
            }

            Client updatedClient = convertClientDTOToClient(updatedClientDTO);
            existingClient.setName(updatedClient.getName());
            existingClient.setBirthdayDate(updatedClient.getBirthdayDate());
            existingClient.setSex(updatedClient.getSex());
            existingClient.setHealthProblem(updatedClient.getHealthProblem());
            existingClient.setUpdatedDate(LocalDateTime.now());

            // Save the updated client (as a ClientDTO)
            Client savedClient = clientService.saveClient(existingClient);

            // Return a successful response with the updated client (as a ClientDTO)
            return ResponseEntity.ok(savedClient);

        } catch (Exception e) {
            // Handle any exceptions that may occur during the update process
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    private Client convertClientDTOToClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.name());
        client.setBirthdayDate(clientDTO.birthdayDate());
        client.setSex(clientDTO.sex());
//        client.setHealthProblem(clientDTO.healthProblemId);
        client.setUpdatedDate(LocalDateTime.now());
        return client;
    }

}
