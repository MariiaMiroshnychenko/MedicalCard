package com.med.card.controller;

import com.med.card.entity.Patient;
import com.med.card.entity.PatientVisit;
import com.med.card.entity.PersonalRegData;
import com.med.card.entity.Referral;
import com.med.card.repository.PatientRepo;
import com.med.card.repository.PatientVisitRepo;
import com.med.card.repository.ReferralRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class VisitCreatorController {
    private PatientRepo patientRepo;
    private PatientVisitRepo patientVisitRepo;
    private ReferralRepo referralRepo;

    public VisitCreatorController(PatientRepo patientRepo, ReferralRepo referralRepo,
                                  PatientVisitRepo patientVisitRepo) {
        this.patientRepo = patientRepo;
        this.referralRepo = referralRepo;
        this.patientVisitRepo = patientVisitRepo;
    }

    @GetMapping("/doctor-appointment")
    public String getAppointmentPage(Model model, Integer patientId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        String enterValue;
        if (personalRegData.getRoleId().getTitle().equals("Сімейний лікар")) {
            enterValue = "Patient medical card number ";
        } else {
            enterValue = "Patient referral number ";
        }
        model.addAttribute("enterValue", enterValue);
        model.addAttribute("patientId", patientId);
        return "doctor-appointments";
    }

    @PostMapping("/doctor-appointment")
    public String createAppointment(Model model, String number,
                                    String diagnosis,
                                    String appType, String appointment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("number", number);
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("appointment", appointment);

        Patient patient;
        Referral idReferralToDoctor;

        if (personalRegData.getRoleId().getTitle().equals("Сімейний лікар")) {
            idReferralToDoctor = null;
            patient = patientRepo.findByMedicalCard_McId(Integer.valueOf(number));
        } else {
            idReferralToDoctor = referralRepo.findByIdIs(Integer.valueOf(number));
            patient = patientRepo.findByReferralsIs(idReferralToDoctor);
        }

        PatientVisit patientVisit = PatientVisit.builder()
                .idReferralToDoctor(idReferralToDoctor)
                .patientId(patient)
                .visitDate(LocalDateTime.now())
                .diagnosis(diagnosis)
                .appType(appType)
                .appointment(appointment)
                .appState(true)
                .doctorId(personalRegData.getMedicalEmployee())
                .build();

        Integer patientId = patient.getId();

        patientVisitRepo.save(patientVisit);
        return getAppointmentPage(model, patientId);
    }

    @PostMapping("/doctor-appointment/referral")
    public String issueReferral(Model model, String pId, String refType, String actTitle) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("pId", pId);
        model.addAttribute("refType", refType);
        model.addAttribute("actTitle", actTitle);

        Patient patient = patientRepo.findByIdIs(Integer.valueOf(pId));

        Referral referral = Referral.builder()
                .refType(refType)
                .actTitle(actTitle)
                .refDate(LocalDateTime.now())
                .patientId(patient)
                .doctorId(personalRegData.getMedicalEmployee())
                .build();

        referralRepo.save(referral);
        return getAppointmentPage(model, null);
    }
}
