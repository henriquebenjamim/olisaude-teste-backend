package com.olisaude.backenddevchallenger.domain;


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
}
