package com.olisaude.backenddevchallenger.dtos;

import com.olisaude.backenddevchallenger.domain.Sex;

public record ClientDTO(String name, String birthdayDate, Sex sex, String healthProblem) {

}
