package com.olisaude.backenddevchallenger.domain;


import com.olisaude.backenddevchallenger.dtos.ClientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "clients")
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String birthdayDate;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String healthProblem;

    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;

    public Client(ClientDTO data) {
        this.name = data.name();
        this.birthdayDate = data.birthdayDate();
        this.sex = data.sex();
        this.healthProblem = data.healthProblem();
    }


}
