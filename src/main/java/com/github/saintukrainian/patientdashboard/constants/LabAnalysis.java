package com.github.saintukrainian.patientdashboard.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LabAnalysis {

  ALBUMIN("albumin", "ALBUMIN", "Albumin"),
  FLUID("fluid", "FLUID", "Fluid"),
  CALCIUM("calcium", "CALCIUM", "Calcium"),
  BLOOD_FLOW_RATE("bloodFlowRate", "BLOOD_FLOW_RATE", "Blood Flow Rate"),
  HEMOGLOBIN("hemoglobin", "HEMOGLOBIN", "Hemoglobin");

  private final String parameterName;
  private final String tableColumnName;
  private final String displayName;
}
