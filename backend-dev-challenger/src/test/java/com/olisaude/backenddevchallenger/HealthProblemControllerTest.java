package com.olisaude.backenddevchallenger;

import com.olisaude.backenddevchallenger.controllers.HealthProblemController;
import com.olisaude.backenddevchallenger.domain.HealthProblem;
import com.olisaude.backenddevchallenger.dtos.HealthProblemDTO;
import com.olisaude.backenddevchallenger.services.HealthProblemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HealthProblemControllerTest {

    @InjectMocks
    private HealthProblemController healthProblemController;

    @Mock
    private HealthProblemService healthProblemService;

    private HealthProblemDTO healthProblemDTO;

    @BeforeEach
    void setup() {
        healthProblemDTO = new HealthProblemDTO("diabetes", 1);
    }
    @Test
    void shouldSaveHealthProblem() {
        ResponseEntity<HealthProblem> response = assertDoesNotThrow(() -> healthProblemController.Post(healthProblemDTO));
        assertThat(response.getStatusCodeValue(), equalTo(HttpStatus.CREATED.value()));
    }
}
