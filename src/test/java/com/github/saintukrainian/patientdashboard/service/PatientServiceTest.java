package com.github.saintukrainian.patientdashboard.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.saintukrainian.patientdashboard.IntegrationTest;
import com.github.saintukrainian.patientdashboard.entity.Patient;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
class PatientServiceTest {

  @Autowired
  private PatientService patientService;

  @Test
  void shouldFetchAllPatients() {
    // when
    List<Patient> patients = patientService.findAllPatients();

    // then
    assertThat(patients).isNotEmpty();
  }
}
