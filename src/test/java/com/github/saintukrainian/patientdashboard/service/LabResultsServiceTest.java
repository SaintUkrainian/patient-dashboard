package com.github.saintukrainian.patientdashboard.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.saintukrainian.patientdashboard.IntegrationTest;
import com.github.saintukrainian.patientdashboard.entity.LabResults;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
class LabResultsServiceTest {

  @Autowired
  private LabResultsService labResultsService;

  @Test
  void shouldFetchLabResultsById() {
    // given
    long patientId = 1L;

    // when
    List<LabResults> labResultsByPatientId = labResultsService.findLabResultsByPatientId(patientId);

    // then
    assertThat(labResultsByPatientId).isNotEmpty();
  }
}