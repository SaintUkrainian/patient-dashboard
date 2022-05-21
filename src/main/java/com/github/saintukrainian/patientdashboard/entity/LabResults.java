package com.github.saintukrainian.patientdashboard.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "lab_results")
public class LabResults {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "results_id")
  private Long resultsId;

  @Column(name = "lab_date")
  private Date labDate;

  @PositiveOrZero
  @Column(name = "albumin")
  private float albumin;

  @PositiveOrZero
  @Column(name = "fluid")
  private float fluid;

  @PositiveOrZero
  @Column(name = "calcium")
  private float calcium;

  @PositiveOrZero
  @Column(name = "blood_flow_rate")
  private float bloodFlowRate;

  @PositiveOrZero
  @Column(name = "hemoglobin")
  private float hemoglobin;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;
}
