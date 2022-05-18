package com.github.saintukrainian.patientdashboard.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "lab_results")
public class LabResults {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "results_id")
  private Long resultsId;

  @Column(name = "lab_date")
  private Date labDate;

  @Column(name = "albumin")
  private float albumin;

  @Column(name = "fluid")
  private float fluid;

  @Column(name = "calcium")
  private float calcium;

  @Column(name = "blood_flow_rate")
  private float bloodFlowRate;

  @Column(name = "hemoglobin")
  private float hemoglobin;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    LabResults that = (LabResults) o;
    return resultsId != null && Objects.equals(resultsId, that.resultsId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
