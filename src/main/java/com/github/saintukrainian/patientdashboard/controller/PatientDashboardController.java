package com.github.saintukrainian.patientdashboard.controller;

import com.github.saintukrainian.patientdashboard.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PatientDashboardController {

  final PatientService patientService;

  @GetMapping("/")
  public String dashboard(Model model) {
    model.addAttribute("patients", patientService.findAllPatients());
    return "dashboard.html";
  }
}
