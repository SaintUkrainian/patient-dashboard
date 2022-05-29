package com.github.saintukrainian.patientdashboard.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GeneralConstants {

  LAB_RESULTS("labResults"),
  RESULTS_ID("resultsId"),
  PATIENT_ID("patientId"),
  LAB_DATE ("labDate");

  private final String displayName;
}
