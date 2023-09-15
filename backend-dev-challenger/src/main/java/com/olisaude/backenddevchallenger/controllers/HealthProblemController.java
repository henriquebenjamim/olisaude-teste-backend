package com.olisaude.backenddevchallenger.controllers;

import com.olisaude.backenddevchallenger.domain.HealthProblem;
import com.olisaude.backenddevchallenger.dtos.HealthProblemDTO;
import com.olisaude.backenddevchallenger.services.HealthProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/healthProblems")
public class HealthProblemController {

    @Autowired
    private HealthProblemService healthProblemService;

    @PostMapping
    public ResponseEntity<HealthProblem> Post(@RequestBody HealthProblemDTO healthProblem) {
        HealthProblem newHealthProblem = healthProblemService.createHealthProblem(healthProblem);
        return new ResponseEntity<>(newHealthProblem, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public HealthProblem GetHealthProblemById(@PathVariable Long id) throws Exception {
        return healthProblemService.findHealthProblemById(id);
    }
}
