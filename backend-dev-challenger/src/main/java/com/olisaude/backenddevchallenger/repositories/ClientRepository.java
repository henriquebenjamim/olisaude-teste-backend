package com.olisaude.backenddevchallenger.repositories;

import com.olisaude.backenddevchallenger.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findClientById(String id);

}
