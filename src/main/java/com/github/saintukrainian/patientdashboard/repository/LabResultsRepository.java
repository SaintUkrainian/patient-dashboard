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
        "SELECT * FROM lab_results WHERE PATIENT_ID = :patientId ORDER BY LAB_DATE DESC",
        LabResults.class);
    nativeQuery.setParameter("patientId", patientId);
    log.info("Retrieving lab results where patient id = {}", patientId);
    return (List<LabResults>) nativeQuery.getResultList();
  }

  public LabResults findLabResultsByLabResultsId(long resultsId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "SELECT * FROM lab_results WHERE RESULTS_ID = :resultsId",
        LabResults.class);
    nativeQuery.setParameter("resultsId", resultsId);
    log.info("Retrieving lab results where results id = {}", resultsId);
    return (LabResults) nativeQuery.getSingleResult();
  }

  @Transactional
  public void removeLabResultsById(long resultsId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "DELETE FROM lab_results WHERE RESULTS_ID = :resultsId");
    nativeQuery.setParameter("resultsId", resultsId);
    log.warn("Removing lab results with id = {}", resultsId);
    nativeQuery.executeUpdate();
  }

  @Transactional
  public void updateLabResults(LabResults editedResults) {
    Query nativeQuery = entityManager.createNativeQuery(
        "UPDATE lab_results SET "
                + "ALBUMIN = :albumin, "
                + "FLUID = :fluid, "
                + "CALCIUM = :calcium, "
                + "BLOOD_FLOW_RATE = :bloodFlowRate, "
                + "HEMOGLOBIN = :hemoglobin "
              + "WHERE RESULTS_ID = :resultsId");
    Long resultsId = editedResults.getResultsId();
    nativeQuery.setParameter("resultsId", resultsId);
    nativeQuery.setParameter("albumin", editedResults.getAlbumin());
    nativeQuery.setParameter("fluid", editedResults.getFluid());
    nativeQuery.setParameter("calcium", editedResults.getCalcium());
    nativeQuery.setParameter("bloodFlowRate", editedResults.getBloodFlowRate());
    nativeQuery.setParameter("hemoglobin", editedResults.getHemoglobin());

    log.warn("Executing update on lab results where lab results id = {}", resultsId);
    nativeQuery.executeUpdate();
  }
}
