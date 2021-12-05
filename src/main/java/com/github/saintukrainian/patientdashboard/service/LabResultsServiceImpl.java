package com.github.saintukrainian.patientdashboard.service;

import static java.util.Objects.isNull;

import com.github.saintukrainian.patientdashboard.entity.LabResults;
import com.github.saintukrainian.patientdashboard.repository.LabResultsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabResultsServiceImpl implements LabResultsService {

  final LabResultsRepository labResultsRepository;

  @Override
  public List<LabResults> findLabResultsByPatientId(Long patientId) {
    return labResultsRepository.findLabResultsByPatientPatientIdOrderByLabDateDesc(patientId);
  }

  @Override
  public void deleteLabResultsById(Long labsId) {
    if (isNull(labsId)) {
      throw new IllegalArgumentException("Labs Id cannot be null!");
    }
    labResultsRepository.deleteById(labsId);
  }
}
