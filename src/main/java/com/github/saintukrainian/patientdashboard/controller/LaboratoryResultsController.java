package com.github.saintukrainian.patientdashboard.controller;

import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.ADD_LABS_PAGE;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.EDIT_LABS_PAGE;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.KEY_VALUE_DTO;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.LAB_RESULTS;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.PARAMETERS;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.PATIENT_ID;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.PATIENT_LAB_RESULTS_PAGE;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.REDIRECT_LAB_RESULTS_BY_PATIENT_ID;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.REDIRECT_TO_HOME;
import static com.github.saintukrainian.patientdashboard.constants.GeneralConstants.SEARCH_LABS_PAGE;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNullElse;

import com.github.saintukrainian.patientdashboard.constants.LabAnalysis;
import com.github.saintukrainian.patientdashboard.entity.LabResults;
import com.github.saintukrainian.patientdashboard.model.KeyMinMaxValueDto;
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
      return REDIRECT_TO_HOME;
    }
    model.addAttribute(LAB_RESULTS,
        labResultsService.findLabResultsByPatientId(patientId));
    model.addAttribute(PATIENT_ID, patientId);
    return PATIENT_LAB_RESULTS_PAGE;
  }

  @GetMapping("/add-labs")
  public String addLabResults(@RequestParam Long patientId, @ModelAttribute LabResults labResults,
      Model model) {
    model.addAttribute(PATIENT_ID, patientId);
    return ADD_LABS_PAGE;
  }

  @GetMapping("/remove-labs")
  public String removeLabResults(@RequestParam Long labsId, @RequestParam Long patientId) {
    labResultsService.removeLabResultsById(labsId);
    return REDIRECT_LAB_RESULTS_BY_PATIENT_ID + patientId;
  }

  @GetMapping("/edit-labs")
  public String editLabResults(
      @RequestParam Long labsId, @RequestParam Long patientId,
      Model model) {
    model.addAttribute(LAB_RESULTS,
        labResultsService.findLabResultsByLabResultsId(labsId));
    model.addAttribute(PATIENT_ID, patientId);
    return EDIT_LABS_PAGE;
  }

  @PostMapping("/add-labs")
  public String addLabResults(
      @RequestParam(required = false) Long patientId,
      @Valid @ModelAttribute LabResults labResults, BindingResult bindingResult, Model model) {
    model.addAttribute(PATIENT_ID, patientId);
    if (bindingResult.hasErrors()) {
      return ADD_LABS_PAGE;
    }
    labResultsService.createLabResults(labResults, patientId);
    return REDIRECT_LAB_RESULTS_BY_PATIENT_ID + patientId;
  }

  @PostMapping("/edit-labs")
  public String editLabResults(
      @RequestParam(required = false) Long patientId,
      @Valid @ModelAttribute LabResults editedResults, BindingResult bindingResult, Model model) {
    model.addAttribute(PATIENT_ID, patientId);
    if (bindingResult.hasErrors()) {
      return EDIT_LABS_PAGE;
    }
    labResultsService.updateLabResults(editedResults);
    return REDIRECT_LAB_RESULTS_BY_PATIENT_ID + patientId;
  }

  @GetMapping("/search-labs")
  public String searchLabResults(@ModelAttribute KeyMinMaxValueDto keyValueDto, Model model) {
    model.addAttribute(PARAMETERS, LabAnalysis.values());
    model.addAttribute(KEY_VALUE_DTO, requireNonNullElse(keyValueDto, new KeyMinMaxValueDto()));
    if (keyValueDto != null) {
      model.addAttribute(LAB_RESULTS, labResultsService.findLabResultsByParameters(keyValueDto));
    }
    return SEARCH_LABS_PAGE;
  }
}
