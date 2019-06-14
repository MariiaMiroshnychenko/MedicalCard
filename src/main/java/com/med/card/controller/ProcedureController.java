package com.med.card.controller;

import com.med.card.entity.PatientVisit;
import com.med.card.entity.PersonalRegData;
import com.med.card.repository.PatientVisitRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProcedureController {
    private PatientVisitRepo patientVisitRepo;

    public ProcedureController(PatientVisitRepo patientVisitRepo) {
        this.patientVisitRepo = patientVisitRepo;
    }

    @GetMapping("/procedures")
    public String getProcedures(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        if (personalRegData.getRoleId().getTitle().equals("Медичний персонал")) {
            return "redirect:/proceduresMedEmp";
        }

        List<PatientVisit> toDoList;

        toDoList = patientVisitRepo.findAllByDoctorIdAndAppTypeAndAppState(
                personalRegData.getMedicalEmployee(), "Спеціальне лікування", true);

        model.addAttribute("toDoList", toDoList);
        return "procedure";
    }

    @GetMapping("/proceduresMedEmp")
    public String getMedEmpProcedures(Model model) {
        List<PatientVisit> toDoList;

        toDoList = patientVisitRepo.findAllByAppTypeAndAppState("Процедура", true);

        model.addAttribute("toDoList", toDoList);
        return "procedure-med-emp";
    }

    @PostMapping({"/procedures", "/proceduresMedEmp"})
    public String toMakeFlag(Model model, Integer visitId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("visitId", visitId);

        patientVisitRepo.updatePatientVisitSetAppStateForId(false, visitId);
        patientVisitRepo.updatePatientVisitSetExecutedByPersonForId(personalRegData.getMedicalEmployee().getMedId(),
                visitId);

        if (personalRegData.getRoleId().getTitle().equals("Сімейний лікар") ||
                personalRegData.getRoleId().getTitle().equals("Лікар за спеціальністю")) {
            return getProcedures(model);
        } else {
            return getMedEmpProcedures(model);
        }
    }
}
