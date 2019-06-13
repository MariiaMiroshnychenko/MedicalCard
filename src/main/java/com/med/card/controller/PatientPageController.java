package com.med.card.controller;

import com.med.card.entity.ExamResultByReferral;
import com.med.card.entity.Patient;
import com.med.card.entity.PatientVisit;
import com.med.card.entity.PersonalRegData;
import com.med.card.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientPageController {
    private PersonalRegDataRepo personalRegDataRepo;
    private PatientRepo patientRepo;
    private PatientVisitRepo patientVisitRepo;
    private ExamResultByReferralRepo examResultByReferralRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public PatientPageController(PersonalRegDataRepo personalRegDataRepo,
                                 PatientRepo patientRepo,
                                 PatientVisitRepo patientVisitRepo,
                                 ExamResultByReferralRepo examResultByReferralRepo,
                                 BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.personalRegDataRepo = personalRegDataRepo;
        this.patientRepo = patientRepo;
        this.patientVisitRepo = patientVisitRepo;
        this.examResultByReferralRepo = examResultByReferralRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/patientPage")
    public String getPatientPage(Model model) {
//        PersonalRegData patientData = SessionData.getSessionData();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData patientData = (PersonalRegData) authentication.getPrincipal();

        List<PersonalRegData> doctors = personalRegDataRepo.findAllByRoleId_Title("Сімейний лікар");

        Patient patient = patientRepo.findByMedicalCard_Patient_Person(patientData);
        patientRepo.save(patient);

        Patient patientId = patientRepo.findByPerson(patientData);

        List<PatientVisit> patientVisits = patientVisitRepo.findAllByPatientId(patientId);

        List<ExamResultByReferral> referralResults = examResultByReferralRepo.findAllByRefId_RefTypeAndRefId_PatientId("Направлення на дослідження", patientId);
//        List<Referral> referrals = referralRepo.findAllByPatientIdAndRefTypeIs(patientId, "Направлення на дослідження");

//        List<ExamResultByReferral> results = new ArrayList<>();
//
//        for (Referral ref : referrals) {
//            ExamResultByReferral examResultByReferral = examResultByReferralRepo.findByRefId(ref);
//            results.add(examResultByReferral);
//        }
//
//        List<ReferralResultDto> referralResultDtos = new ArrayList<>();
//        for (int i = 0; i < referrals.size(); i++) {
//            referralResultDtos.add(new ReferralResultDto(referrals.get(i), results.get(i)));
//        }

        model.addAttribute("doctors", doctors);
        model.addAttribute("patientSurname", patientData.getSurname());
        model.addAttribute("patientName", patientData.getName());
        model.addAttribute("patientPatronymic", patientData.getPatronymic());
        model.addAttribute("patientBirthDate", patientData.getBirthDate());
        model.addAttribute("patientPhone", patientData.getPhone());
        model.addAttribute("patientEmail", patientData.getEmail());
        model.addAttribute("attendingDoctorSurname", patientData.getPatient().getAttendingDoctor().getPerson().getSurname());
        model.addAttribute("attendingDoctorName", patientData.getPatient().getAttendingDoctor().getPerson().getName());
        model.addAttribute("attendingDoctorPatronymic", patientData.getPatient().getAttendingDoctor().getPerson().getPatronymic());
        model.addAttribute("medCardId", patient.getMedicalCard().getMcId());
        model.addAttribute("patientVisits", patientVisits);
        model.addAttribute("referralResults", referralResults);
        model.addAttribute("patientPhoto", patientData.getPhoto());

        return "patientPage";
    }

    @GetMapping("/doctorPageForPatient")
    public String showDoctorPage(Model model, @RequestParam(required = false) String attendingDoctorSurname,
                                 @RequestParam(required = false) String attendingDoctorName,
                                 @RequestParam(required = false) String attendingDoctorPatronymic) {
        model.addAttribute("attendingDoctorSurname", attendingDoctorSurname);
        model.addAttribute("attendingDoctorName", attendingDoctorName);
        model.addAttribute("attendingDoctorPatronymic", attendingDoctorPatronymic);

        PersonalRegData doctorData =
                personalRegDataRepo.findAllBySurnameAndNameAndPatronymic(
                        attendingDoctorSurname, attendingDoctorName, attendingDoctorPatronymic);
        personalRegDataRepo.save(doctorData);

        model.addAttribute("attendingDoctorSpeciality", doctorData.getMedicalEmployee().getSpeciality());
        model.addAttribute("doctorBirthDate", doctorData.getBirthDate());
        model.addAttribute("doctorPhoneNumber", doctorData.getPhone());
        model.addAttribute("doctorEmail", doctorData.getEmail());
        model.addAttribute("attendingDoctorPhoto", doctorData.getPhoto());

        return "doctorPageForPatient";
    }

    @PostMapping("/patientPage/changeLogin")
    public String changePassword(String newLogin, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("newLogin", newLogin);

        personalRegDataRepo.updatePersonalRegDataSetLoginForPersonId(newLogin, personalRegData.getId());

        return "patientPage";
    }

    @PostMapping("/patientPage/changePassword")
    public String changePassword(String newPassword, String repeatedPassword, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("newPassword", newPassword);
        model.addAttribute("repeatedPassword", repeatedPassword);

        if (newPassword.compareTo(repeatedPassword) == 0) {
            String newEncodedPassword = bCryptPasswordEncoder.encode(newPassword);
            personalRegDataRepo.updatePersonalRegDataSetPasswordForPersonId(newEncodedPassword, personalRegData.getId());
        }

        return "patientPage";
    }
}
