package com.github.saintukrainian.patientdashboard.service;

import com.github.saintukrainian.patientdashboard.model.Patient;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {

  List<Patient> findAllPatients();
}
