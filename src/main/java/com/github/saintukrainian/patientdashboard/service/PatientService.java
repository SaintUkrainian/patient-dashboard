package com.github.saintukrainian.patientdashboard.service;

import com.github.saintukrainian.patientdashboard.entity.Patient;
import com.github.saintukrainian.patientdashboard.repository.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;

  public List<Patient> findAllPatients() {
    return patientRepository.findAllPatients();
  }
}
