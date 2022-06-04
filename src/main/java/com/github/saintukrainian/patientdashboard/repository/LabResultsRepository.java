package com.github.saintukrainian.patientdashboard.repository;

import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.LAB_DATE;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.PATIENT_ID;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.RESULTS_ID;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.ALBUMIN;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.BLOOD_FLOW_RATE;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.CALCIUM;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.FLUID;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.HEMOGLOBIN;

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
    nativeQuery.setParameter(PATIENT_ID.getDisplayName(), patientId);
    log.info("Retrieving lab results where patient id = {}", patientId);
    return (List<LabResults>) nativeQuery.getResultList();
  }

  public LabResults findLabResultsByLabResultsId(long resultsId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "SELECT * FROM lab_results WHERE RESULTS_ID = :resultsId",
        LabResults.class);
    nativeQuery.setParameter(RESULTS_ID.getDisplayName(), resultsId);
    log.info("Retrieving lab results where results id = {}", resultsId);
    return (LabResults) nativeQuery.getSingleResult();
  }

  @Transactional
  public void removeLabResultsById(long resultsId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "DELETE FROM lab_results WHERE RESULTS_ID = :resultsId");
    nativeQuery.setParameter(RESULTS_ID.getDisplayName(), resultsId);
    log.warn("Removing lab results with id = {}", resultsId);
    nativeQuery.executeUpdate();
  }

  @Transactional
  public void createLabResults(LabResults newLabResults, Long patientId) {
    log.info("Creating new lab results for patient with id = {}", patientId);
    Query nativeQuery = entityManager.createNativeQuery(
        "INSERT INTO lab_results" +
            " (PATIENT_ID, ALBUMIN, FLUID, CALCIUM, BLOOD_FLOW_RATE, HEMOGLOBIN, LAB_DATE)" +
            " VALUES (:patientId, :albumin, :fluid, :calcium, :bloodFlowRate, :hemoglobin, :labDate)");
    nativeQuery.setParameter(PATIENT_ID.getDisplayName(), patientId);
    nativeQuery.setParameter(LAB_DATE.getDisplayName(), newLabResults.getLabDate());
    bindCommonParameters(newLabResults, nativeQuery);

    log.warn("Executing creation of lab results");
    nativeQuery.executeUpdate();
  }

  @Transactional
  public void updateLabResults(LabResults editedResults) {
    log.info("Updating existing lab results with id = {}", editedResults.getResultsId());
    Query nativeQuery = entityManager.createNativeQuery(
        "UPDATE lab_results SET "
            + "ALBUMIN = :albumin, "
            + "FLUID = :fluid, "
            + "CALCIUM = :calcium, "
            + "BLOOD_FLOW_RATE = :bloodFlowRate, "
            + "HEMOGLOBIN = :hemoglobin "
            + "WHERE RESULTS_ID = :resultsId");
    Long resultsId = editedResults.getResultsId();
    nativeQuery.setParameter(RESULTS_ID.getDisplayName(), resultsId);
    bindCommonParameters(editedResults, nativeQuery);

    log.warn("Executing update on lab results where lab results id = {}", resultsId);
    nativeQuery.executeUpdate();
  }

  private void bindCommonParameters(LabResults newLabResults, Query nativeQuery) {
    nativeQuery.setParameter(ALBUMIN.getResultName(), newLabResults.getAlbumin());
    nativeQuery.setParameter(FLUID.getResultName(), newLabResults.getFluid());
    nativeQuery.setParameter(CALCIUM.getResultName(), newLabResults.getCalcium());
    nativeQuery.setParameter(BLOOD_FLOW_RATE.getResultName(), newLabResults.getBloodFlowRate());
    nativeQuery.setParameter(HEMOGLOBIN.getResultName(), newLabResults.getHemoglobin());
  }
}
