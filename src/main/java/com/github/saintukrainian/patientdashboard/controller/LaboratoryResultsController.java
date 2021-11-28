package com.github.saintukrainian.patientdashboard.controller;

import com.github.saintukrainian.patientdashboard.service.LabResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LaboratoryResultsController {

  final LabResultsService labResultsService;

  @GetMapping("/lab-results")
  public String patientLabResults(@RequestParam Long patientId, Model model) {
    model.addAttribute("labResults",
        labResultsService.findLabResultsByPatientId(patientId));
    return "patient_lab_results.html";
  }
}
