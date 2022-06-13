package com.github.saintukrainian.patientdashboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyMinMaxValueDto {

  private String key;
  private Double minValue;
  private Double maxValue;
}
