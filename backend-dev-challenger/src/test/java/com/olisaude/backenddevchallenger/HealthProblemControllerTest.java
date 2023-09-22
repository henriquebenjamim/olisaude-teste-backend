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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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

    @Test
    void shouldGetHealthProblemById() throws Exception {
        Long healthProblemId = 1L;

        HealthProblem mockHealthProblem = new HealthProblem();

        mockHealthProblem.setId(healthProblemId);
        mockHealthProblem.setDiseaseName("Diabetes");
        mockHealthProblem.setDiseaseLevel(1);

        when(healthProblemService.findHealthProblemById(healthProblemId)).thenReturn(mockHealthProblem);


        HealthProblem returnedHealthProblem = assertDoesNotThrow(() -> healthProblemController.GetHealthProblemById(healthProblemId));

        assertThat(mockHealthProblem.getId(), equalTo(returnedHealthProblem.getId()));
        assertThat(mockHealthProblem.getDiseaseName(), equalTo(returnedHealthProblem.getDiseaseName()));
        assertThat(mockHealthProblem.getDiseaseLevel(), equalTo(returnedHealthProblem.getDiseaseLevel()));
    }

    // ChatGpt Creates
    @Test
    void shouldHandleExceptionWhenIdNotFound() throws Exception {
        Long healthProblemId = 1L; // Replace with a valid healthProblemId

        // Mock the behavior of the service method to throw an exception
        String exMessage = "Health problem not found";
        when(healthProblemService.findHealthProblemById(healthProblemId)).thenThrow(new Exception(exMessage));

        // Use assertThrows to verify that the exception is thrown
        Exception exception = assertThrows(Exception.class, () -> healthProblemController.GetHealthProblemById(healthProblemId));

        // Perform assertions on the exception message or type as needed
        assertThat(exMessage, equalTo(exception.getMessage()));
    }

}
