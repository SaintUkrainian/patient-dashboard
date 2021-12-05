package com.github.saintukrainian.patientdashboard.repository;

import com.github.saintukrainian.patientdashboard.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
