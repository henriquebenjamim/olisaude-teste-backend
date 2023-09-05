package com.olisaude.backenddevchallenger.controllers;

import com.olisaude.backenddevchallenger.domain.Client;
import com.olisaude.backenddevchallenger.dtos.ClientDTO;
import com.olisaude.backenddevchallenger.services.ClientService;
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

    @PostMapping
    public ResponseEntity<Client> Post(@RequestBody ClientDTO client) {
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Client>> Get() {
        List<Client> clients = this.clientService.getAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Client GetClientById(@PathVariable String id) throws Exception {
        return clientService.findClientById(id);
    }

//    @PutMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@PathVariable String id,
//                       @RequestBody @Valid ClientDTO client) throws Exception{
//        clientService
//                .findClientById(id)
//                .map
//                .findById(id)
//                .map(clienteExistente -> {
//                    cliente.setId(clienteExistente.getId());
//                    clientes.save(cliente);
//                    return clienteExistente;
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateClient(
//            @PathVariable String id,
//            @RequestBody ClientDTO updatedClientDTO
//    ) {
//        try {
//            // Validate input if needed
//            if (updatedClientDTO == null) {
//                return ResponseEntity.badRequest().body("Request body is required.");
//            }
//
//            // Check if the client with the given ID exists
//            Client existingClient = clientService.findClientById(id);
//
//            if (existingClient == null) {
//                return ResponseEntity.notFound().build();
//            }
//
//            // Save the updated client (as a ClientDTO)
//
//            ClientDTO savedClientDTO = clientService.saveClient(updatedClientDTO);
//
//            // Return a successful response with the updated client (as a ClientDTO)
//            return ResponseEntity.ok(savedClientDTO);
//
//        } catch (Exception e) {
//            // Handle any exceptions that may occur during the update process
//            e.printStackTrace(); // Log the error for debugging
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(
            @PathVariable String id,
            @RequestBody ClientDTO updatedClientDTO
    ) {
        try {
            // Validate input if needed
            if (updatedClientDTO == null) {
                return ResponseEntity.badRequest().body("Request body is required.");
            }

            // Check if the client with the given ID exists
            Client existingClient = clientService.findClientById(id);

            if (existingClient == null) {
                return ResponseEntity.notFound().build();
            }

            Client updatedClient = convertClientDTOToClient(updatedClientDTO);

            // Save the updated client (as a ClientDTO)
            Client savedClientDTO = clientService.saveClient(updatedClient);

            // Return a successful response with the updated client (as a ClientDTO)
            return ResponseEntity.ok(savedClientDTO);

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
        client.setHealthProblem(clientDTO.healthProblem());
        client.setUpdatedDate(LocalDateTime.now());
        return client;
    }

}
