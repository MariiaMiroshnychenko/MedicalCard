//package com.med.card.controller;
//
//import com.med.card.entity.Patient;
//import com.med.card.entity.PatientVisit;
//import com.med.card.entity.PersonalRegData;
//import com.med.card.entity.Referral;
//import com.med.card.repository.*;
//import com.med.card.service.dto.VisitsDto;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;

//@Controller
//public class DoctorAppointmentsController {
//    private PatientVisitRepo patientVisitRepo;
//    private PersonalRegDataRepo personalRegDataRepo;
//    private ReferralRepo referralRepo;
//    private PatientRepo patientRepo;
//
//    public DoctorAppointmentsController(PatientVisitRepo patientVisitRepo,
//                                        PersonalRegDataRepo personalRegDataRepo,
//                                        ReferralRepo referralRepo,
//                                        PatientRepo patientRepo) {
//        this.patientVisitRepo = patientVisitRepo;
//        this.personalRegDataRepo = personalRegDataRepo;
//        this.referralRepo = referralRepo;
//        this.patientRepo = patientRepo;
//    }
//
//    @GetMapping("/doctorPage/doctor-appointment")
//    public String getDoctorAppointment(Model model, LocalDateTime localDateTime) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData doctorRegData = (PersonalRegData) authentication.getPrincipal();
//
//        model.addAttribute("date", localDateTime);
//
//        List<PatientVisit> visits;
//        if (localDateTime != null) {
//            visits = patientVisitRepo.findAllByVisitDate(localDateTime);
//        } else {
//            visits = patientVisitRepo.findAllByDoctorId_Person(doctorRegData);
//        }
//
//        List<VisitsDto> visitsDtos = new ArrayList<>();
//
//        int counter = 0;
//        for (PatientVisit pv : visits) {
//            PersonalRegData patient = personalRegDataRepo.findPersonalRegDataByPatient(pv.getPatientId());
//            personalRegDataRepo.save(patient);
//
//            List<Referral> referrals = referralRepo.findAllByDoctorId_PersonAndPatientId_PersonAndRefDateIs(
//                    doctorRegData, patient, pv.getVisitDate());
//
//            visitsDtos.add(new VisitsDto(counter + 1, pv, patient, referrals));
//            counter++;
//        }
//
//        model.addAttribute("doctorAppointments", visitsDtos);
//
//        return "doctor-appointments";
//    }
//
////    @Secured(value = {"Лікар за спеціальністю"})
////    @PostMapping("/doctorPage/doctor-appointment")
////    public String getVisitsByDate(Date localDateTime) {
//////        model.addAttribute("date", localDateTime);
////        getDoctorAppointment(model, localDateTime);
////        return "doctor-appointments";
////    }
//
////    @Secured(value = "Лікар зі спеціальності")
//    @PostMapping("/doctorPage/doctor-appointment")
//    public String makeAppointment (Model model,
//                                   @RequestParam(required = false) Integer referralNumber,
//                                   @RequestParam(required = false) String diagnosis,
//                                   @RequestParam(required = false) String appointment,
//                                   @RequestParam(required = false) String refType,
//                                   @RequestParam(required = false) String actTitle) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData doctorRegData = (PersonalRegData) authentication.getPrincipal();
//
//        model.addAttribute("refNumber", referralNumber);
//        model.addAttribute("diagnosis", diagnosis);
//        model.addAttribute("appointment", appointment);
//        model.addAttribute("refType", refType);
//        model.addAttribute("actTitle", actTitle);
//
//        Referral referral = referralRepo.findByIdIs(referralNumber);
////        referralRepo.save(referral);
//
//        Patient patient = patientRepo.findByReferralsIs(Collections.singletonList(referral));
////        patientRepo.save(patient);
//
//        PatientVisit patientVisit = PatientVisit.builder()
//                .idReferralToDoctor(referral)
//                .patientId(patient)
//                .visitDate(LocalDateTime.now())
//                .diagnosis(diagnosis)
//                .appointment(appointment)
//                .doctorId(doctorRegData.getMedicalEmployee())
//                .build();
//
//        if(!refType.isEmpty() && !actTitle.isEmpty()) {
//            Referral newReferral = Referral.builder()
//                .refType(refType)
//                .actTitle(actTitle)
//                .refDate(LocalDateTime.now())
//                .patientId(patient)
//                .doctorId(doctorRegData.getMedicalEmployee())
//                .build();
//
//            referralRepo.save(newReferral);
//        }
//        patientVisitRepo.save(patientVisit);
//        return "doctor-appointments";
//    }
//}
