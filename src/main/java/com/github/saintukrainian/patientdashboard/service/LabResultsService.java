package com.github.saintukrainian.patientdashboard.service;

import com.github.saintukrainian.patientdashboard.model.LabResults;
import java.util.List;

public interface LabResultsService {

  List<LabResults> findLabResultsByPatientId(Long patientId);
}
