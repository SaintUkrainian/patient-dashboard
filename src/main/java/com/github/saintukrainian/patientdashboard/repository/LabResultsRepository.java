package com.github.saintukrainian.patientdashboard.repository;

import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.LAB_DATE;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.MAX_VALUE;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.MIN_VALUE;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.PATIENT_ID;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.RESULTS_ID;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.ALBUMIN;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.BLOOD_FLOW_RATE;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.CALCIUM;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.FLUID;
import static com.github.saintukrainian.patientdashboard.constants.LabAnalysis.HEMOGLOBIN;

import com.github.saintukrainian.patientdashboard.entity.LabResults;
import com.github.saintukrainian.patientdashboard.model.KeyMinMaxValueDto;
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
    nativeQuery.setParameter(PATIENT_ID, patientId);
    log.info("Retrieving lab results where patient id = {}", patientId);
    return (List<LabResults>) nativeQuery.getResultList();
  }

  public LabResults findLabResultsByLabResultsId(long resultsId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "SELECT * FROM lab_results WHERE RESULTS_ID = :resultsId",
        LabResults.class);
    nativeQuery.setParameter(RESULTS_ID, resultsId);
    log.info("Retrieving lab results where results id = {}", resultsId);
    return (LabResults) nativeQuery.getSingleResult();
  }

  @Transactional
  public void removeLabResultsById(long resultsId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "DELETE FROM lab_results WHERE RESULTS_ID = :resultsId");
    nativeQuery.setParameter(RESULTS_ID, resultsId);
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
    nativeQuery.setParameter(PATIENT_ID, patientId);
    nativeQuery.setParameter(LAB_DATE, newLabResults.getLabDate());
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
    nativeQuery.setParameter(RESULTS_ID, resultsId);
    bindCommonParameters(editedResults, nativeQuery);

    log.warn("Executing update on lab results where lab results id = {}", resultsId);
    nativeQuery.executeUpdate();
  }

  public List<LabResults> findLabResultsByParameters(KeyMinMaxValueDto keyValueDto) {
    String tableColumnName = keyValueDto.getKey();
    Double minValue = keyValueDto.getMinValue();
    Double maxValue = keyValueDto.getMaxValue();

    log.info("Finding lab results by parameter = {} and values = [{}, {}]", tableColumnName,
        minValue, maxValue);
    Query nativeQuery = entityManager.createNativeQuery(
        "SELECT lr.* FROM lab_results lr WHERE " + tableColumnName
            + " BETWEEN :minValue AND :maxValue", LabResults.class);
    nativeQuery.setParameter(MIN_VALUE, minValue);
    nativeQuery.setParameter(MAX_VALUE, maxValue);

    return (List<LabResults>) nativeQuery.getResultList();
  }

  private void bindCommonParameters(LabResults newLabResults, Query nativeQuery) {
    nativeQuery.setParameter(ALBUMIN.getParameterName(), newLabResults.getAlbumin());
    nativeQuery.setParameter(FLUID.getParameterName(), newLabResults.getFluid());
    nativeQuery.setParameter(CALCIUM.getParameterName(), newLabResults.getCalcium());
    nativeQuery.setParameter(BLOOD_FLOW_RATE.getParameterName(), newLabResults.getBloodFlowRate());
    nativeQuery.setParameter(HEMOGLOBIN.getParameterName(), newLabResults.getHemoglobin());
  }
}
