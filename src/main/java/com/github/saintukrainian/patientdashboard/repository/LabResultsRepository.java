package com.github.saintukrainian.patientdashboard.repository;

import com.github.saintukrainian.patientdashboard.entity.LabResults;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabResultsRepository extends JpaRepository<LabResults, Long> {

  List<LabResults> findLabResultsByPatientPatientIdOrderByLabDateDesc(Long patientId);
}
