package com.olisaude.backenddevchallenger.repositories;

import com.olisaude.backenddevchallenger.domain.HealthProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthProblemRepository extends JpaRepository<HealthProblem, Long> {

    Optional<HealthProblem> findHealthProblemById(Long id);
}
