package com.github.saintukrainian.patientdashboard.controller;

import static java.util.Objects.isNull;

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
  public String patientLabResults(@RequestParam(required = false) Long patientId, Model model) {
    if (isNull(patientId)) {
      return "redirect:/";
    }
    model.addAttribute("labResults",
        labResultsService.findLabResultsByPatientId(patientId));
    return "patient_lab_results.html";
  }

  @GetMapping("/remove-labs")
  public String removeLabResults(@RequestParam Long labsId, @RequestParam Long patientId) {
    labResultsService.deleteLabResultsById(labsId);
    return "redirect:/lab-results?patientId=" + patientId;
  }
}
