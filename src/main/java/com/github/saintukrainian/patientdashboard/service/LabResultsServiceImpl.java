package com.github.saintukrainian.patientdashboard.service;

import com.github.saintukrainian.patientdashboard.model.LabResults;
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
}
