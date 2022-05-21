package com.github.saintukrainian.patientdashboard.controller;

import static java.util.Objects.isNull;

import com.github.saintukrainian.patientdashboard.entity.LabResults;
import com.github.saintukrainian.patientdashboard.service.LabResultsService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    model.addAttribute("patientId", patientId);
    return "patient_lab_results.html";
  }

  @GetMapping("/remove-labs")
  public String removeLabResults(@RequestParam Long labsId, @RequestParam Long patientId) {
    labResultsService.removeLabResultsById(labsId);
    return "redirect:/lab-results?patientId=" + patientId;
  }

  @GetMapping("/edit-labs")
  public String editLabResults(@RequestParam Long labsId, @RequestParam Long patientId,
      Model model) {
    model.addAttribute("labResults",
        labResultsService.findLabResultsByLabResultsId(labsId));
    model.addAttribute("patientId", patientId);
    return "edit-labs.html";
  }

  @PostMapping("/edit-labs")
  public String editLabResults(@Valid @ModelAttribute LabResults editedResults,
      @RequestParam Long patientId,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "edit-labs.html";
    }
    labResultsService.updateLabResults(editedResults);
    return "redirect:/lab-results?patientId=" + patientId;
  }
}
