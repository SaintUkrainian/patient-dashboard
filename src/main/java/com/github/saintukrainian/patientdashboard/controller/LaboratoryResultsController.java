package com.github.saintukrainian.patientdashboard.controller;

import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.LAB_RESULTS;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.PATIENT_ID;
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

    model.addAttribute(LAB_RESULTS.getDisplayName(),
        labResultsService.findLabResultsByPatientId(patientId));
    model.addAttribute(PATIENT_ID.getDisplayName(), patientId);
    return "patient_lab_results.html";
  }

  @GetMapping("/add-labs")
  public String addLabResults(@RequestParam Long patientId, @ModelAttribute LabResults labResults,
      Model model) {
    model.addAttribute(PATIENT_ID.getDisplayName(), patientId);
    return "add-labs";
  }

  @GetMapping("/remove-labs")
  public String removeLabResults(@RequestParam Long labsId, @RequestParam Long patientId) {
    labResultsService.removeLabResultsById(labsId);
    return "redirect:/lab-results?patientId=" + patientId;
  }

  @GetMapping("/edit-labs")
  public String editLabResults(
      @RequestParam Long labsId, @RequestParam Long patientId,
      Model model) {
    model.addAttribute(LAB_RESULTS.getDisplayName(),
        labResultsService.findLabResultsByLabResultsId(labsId));
    model.addAttribute(PATIENT_ID.getDisplayName(), patientId);
    return "edit-labs";
  }

  @PostMapping("/add-labs")
  public String addLabResults(
      @RequestParam(required = false) Long patientId,
      @Valid @ModelAttribute LabResults labResults, BindingResult bindingResult, Model model) {
    model.addAttribute(PATIENT_ID.getDisplayName(), patientId);
    if (bindingResult.hasErrors()) {
      return "add-labs";
    }

    labResultsService.createLabResults(labResults, patientId);
    return "redirect:/lab-results?patientId=" + patientId;
  }

  @PostMapping("/edit-labs")
  public String editLabResults(
      @RequestParam(required = false) Long patientId,
      @Valid @ModelAttribute LabResults editedResults, BindingResult bindingResult, Model model) {
    model.addAttribute(PATIENT_ID.getDisplayName(), patientId);
    if (bindingResult.hasErrors()) {
      return "edit-labs";
    }

    labResultsService.updateLabResults(editedResults);
    return "redirect:/lab-results?patientId=" + patientId;
  }
}
