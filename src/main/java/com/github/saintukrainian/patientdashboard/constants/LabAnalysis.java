package com.github.saintukrainian.patientdashboard.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LabAnalysis {

  ALBUMIN("albumin"),
  FLUID("fluid"),
  CALCIUM ("calcium"),
  BLOOD_FLOW_RATE ("bloodFlowRate"),
  HEMOGLOBIN ("hemoglobin");

  private final String resultName;
}
