package com.github.saintukrainian.patientdashboard.service;

import com.github.saintukrainian.patientdashboard.entity.LabResults;
import com.github.saintukrainian.patientdashboard.model.KeyMinMaxValueDto;
import com.github.saintukrainian.patientdashboard.repository.LabResultsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabResultsService {

  private final LabResultsRepository labResultsRepository;

  public List<LabResults> findLabResultsByPatientId(Long patientId) {
    if (patientId == null) {
      throw new IllegalArgumentException("Patient id cannot be null!");
    }
    return labResultsRepository.findLabResultsByPatientId(patientId);
  }

  public void removeLabResultsById(Long labsId) {
    if (labsId == null) {
      throw new IllegalArgumentException("Labs Id cannot be null!");
    }
    labResultsRepository.removeLabResultsById(labsId);
  }

  public LabResults findLabResultsByLabResultsId(Long resultsId) {
    if (resultsId == null) {
      throw new IllegalArgumentException("Lab Results Id cannot be null!");
    }
    return labResultsRepository.findLabResultsByLabResultsId(resultsId);
  }

  public void createLabResults(LabResults newLabResults, Long patientId) {
    if (newLabResults == null) {
      throw new IllegalArgumentException("Lab Results cannot be null!");
    }
    labResultsRepository.createLabResults(newLabResults, patientId);
  }

  public void updateLabResults(LabResults editedResults) {
    if (editedResults == null) {
      throw new IllegalArgumentException("Lab Results cannot be null!");
    }
    labResultsRepository.updateLabResults(editedResults);
  }

  public List<LabResults> findLabResultsByParameters(KeyMinMaxValueDto keyValueDto) {
    return labResultsRepository.findLabResultsByParameters(keyValueDto);
  }
}
