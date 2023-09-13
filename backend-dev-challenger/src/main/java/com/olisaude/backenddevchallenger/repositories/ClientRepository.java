package com.olisaude.backenddevchallenger.repositories;

import com.olisaude.backenddevchallenger.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientById(Long id);

}
