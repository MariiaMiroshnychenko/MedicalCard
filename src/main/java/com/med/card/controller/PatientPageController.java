package com.med.card.controller;

import com.med.card.model.entity.ExamResultByReferral;
import com.med.card.model.entity.Patient;
import com.med.card.model.entity.PatientVisit;
import com.med.card.model.entity.PersonalRegData;
import com.med.card.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PatientPageController {
    private PersonalRegDataRepo personalRegDataRepo;
    private PatientRepo patientRepo;
    private PatientVisitRepo patientVisitRepo;
    private ExamResultByReferralRepo examResultByReferralRepo;


    public PatientPageController(PersonalRegDataRepo personalRegDataRepo,
                                 PatientRepo patientRepo,
                                 PatientVisitRepo patientVisitRepo,
                                 ExamResultByReferralRepo examResultByReferralRepo) {
        this.personalRegDataRepo = personalRegDataRepo;
        this.patientRepo = patientRepo;
        this.patientVisitRepo = patientVisitRepo;
        this.examResultByReferralRepo = examResultByReferralRepo;
    }

    @GetMapping("/patientPage")
    public String getPatientPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData patientData = (PersonalRegData) authentication.getPrincipal();

        Patient patientId = patientRepo.findByPerson(patientData);

        List<PatientVisit> patientVisits = patientVisitRepo.findAllByPatientId(patientId);

        List<ExamResultByReferral> referralResults = examResultByReferralRepo.findAllByRefId_RefTypeAndRefId_PatientId(
                "Направлення на дослідження", patientId);

//        model.addAttribute("doctors", doctors);
        model.addAttribute("patientData", patientData);
        model.addAttribute("patientVisits", patientVisits);
        model.addAttribute("referralResults", referralResults);

        return "patientPage";
    }
}
