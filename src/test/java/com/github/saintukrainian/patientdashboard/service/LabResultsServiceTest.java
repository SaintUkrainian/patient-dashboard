package com.github.saintukrainian.patientdashboard.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.saintukrainian.patientdashboard.IntegrationTest;
import com.github.saintukrainian.patientdashboard.entity.LabResults;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
class LabResultsServiceTest {

  @Autowired
  private LabResultsService labResultsService;

  @Test
  void shouldFetchLabResultsByPatientId() {
    // given
    long patientId = 1L;

    // when
    List<LabResults> labResultsByPatientId = labResultsService.findLabResultsByPatientId(patientId);

    // then
    assertThat(labResultsByPatientId).isNotEmpty();
  }

  @Test
  void shouldFetchLabResultsByLabResultsId() {
    // given
    long labResultsId = 1L;

    // when
    LabResults labResultsByLabResultsId = labResultsService.findLabResultsByLabResultsId(
        labResultsId);

    // then
    assertThat(labResultsByLabResultsId)
        .isNotNull()
        .hasNoNullFieldsOrProperties();
  }

  @Test
  void shouldCreateNewLabResults() {
    // given
    LabResults labResults = LabResults.builder()
        .resultsId(1L)
        .albumin(0)
        .calcium(0)
        .bloodFlowRate(0)
        .hemoglobin(0)
        .labDate(Date.from(Instant.now()))
        .build();

    // when
    labResultsService.createLabResults(labResults, 1L);

    // then
    LabResults patientResult = labResultsService.findLabResultsByLabResultsId(1L);
    assertThat(patientResult)
        .isNotNull()
        .hasNoNullFieldsOrProperties();
  }
}
