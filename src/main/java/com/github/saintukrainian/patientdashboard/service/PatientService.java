package com.github.saintukrainian.patientdashboard.service;

import com.github.saintukrainian.patientdashboard.entity.Patient;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {

  List<Patient> findAllPatients();
}
