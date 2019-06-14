package com.med.card.controller;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.PersonalRegData;
import com.med.card.repository.MedicalEmployeeRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedEmployeePageController {
    private MedicalEmployeeRepo medicalEmployeeRepo;

    public MedEmployeePageController(MedicalEmployeeRepo medicalEmployeeRepo) {
        this.medicalEmployeeRepo = medicalEmployeeRepo;
    }

    @GetMapping("/medEmployeePage")
    public String getEmployeePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);

        model.addAttribute("medicalEmployee", medicalEmployee);
        return "medEmployeePage";
    }
}
