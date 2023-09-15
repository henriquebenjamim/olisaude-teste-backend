package com.olisaude.backenddevchallenger.services;

import com.olisaude.backenddevchallenger.domain.HealthProblem;
import com.olisaude.backenddevchallenger.dtos.HealthProblemDTO;
import com.olisaude.backenddevchallenger.repositories.HealthProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthProblemService {

    @Autowired
    private HealthProblemRepository repository;

    public HealthProblem findHealthProblemById(Long id) throws Exception {
        return this.repository.findHealthProblemById(id).orElseThrow(() -> new Exception("Health problem not found."));
    }

    public HealthProblem createHealthProblem(HealthProblemDTO data) {
        HealthProblem newHealthProblem = new HealthProblem(data);
        newHealthProblem.setDiseaseName(data.diseaseName());
        newHealthProblem.setDiseaseLevel(data.diseaseLevel());
        this.saveHealthProblem(newHealthProblem);
        return newHealthProblem;
    }

    public HealthProblem saveHealthProblem(HealthProblem healthProblem) {
        return repository.save(healthProblem);
    }

}
