package com.github.saintukrainian.patientdashboard.repository;

import com.github.saintukrainian.patientdashboard.entity.LabResults;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LabResultsRepository {

  private final EntityManager entityManager;

  public List<LabResults> findLabResultsByPatientId(long patientId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "SELECT * FROM lab_results WHERE PATIENT_ID = :patientId ORDER BY LAB_DATE DESC", LabResults.class);
    nativeQuery.setParameter("patientId", patientId);
    log.info("Retrieving lab results where patient id = {}", patientId);
    return (List<LabResults>) nativeQuery.getResultList();
  }

  @Transactional
  public void removeLabResultsById(long resultsId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "DELETE FROM lab_results WHERE results_id = :resultsId");
    nativeQuery.setParameter("resultsId", resultsId);
    log.warn("Removing lab results with id = {}", resultsId);
    nativeQuery.executeUpdate();
  }
}
