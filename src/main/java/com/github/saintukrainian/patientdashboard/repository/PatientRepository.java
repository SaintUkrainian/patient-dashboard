package com.github.saintukrainian.patientdashboard.repository;

import com.github.saintukrainian.patientdashboard.entity.Patient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PatientRepository {

  private final EntityManager entityManager;

  public List<Patient> findAllPatients() {
    Query nativeQuery =
        entityManager.createNativeQuery("SELECT * FROM patient", Patient.class);
    return (List<Patient>) nativeQuery.getResultList();
  }
}
