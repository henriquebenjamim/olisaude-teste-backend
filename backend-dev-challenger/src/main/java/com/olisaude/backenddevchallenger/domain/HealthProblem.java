package com.olisaude.backenddevchallenger.domain;


import com.olisaude.backenddevchallenger.dtos.HealthProblemDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "healthProblem")
@Table(name = "healthProblem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class HealthProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private String diseaseName;
    private int diseaseLevel;

    public HealthProblem(HealthProblemDTO data) {
        this.diseaseName = data.diseaseName();
        this.diseaseLevel = data.diseaseLevel();
    }
}
