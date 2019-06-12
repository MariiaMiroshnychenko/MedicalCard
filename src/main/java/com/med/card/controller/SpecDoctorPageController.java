package com.med.card.controller;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.Patient;
import com.med.card.entity.PersonalRegData;
import com.med.card.repository.MedicalEmployeeRepo;
import com.med.card.repository.PatientRepo;
import com.med.card.repository.PersonalRegDataRepo;
import com.med.card.service.dto.DoctorPatientInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SpecDoctorPageController {
    private MedicalEmployeeRepo medicalEmployeeRepo;
    private PersonalRegDataRepo personalRegDataRepo;
    private PatientRepo patientRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SpecDoctorPageController(MedicalEmployeeRepo medicalEmployeeRepo,
                                  PersonalRegDataRepo personalRegDataRepo,
                                  PatientRepo patientRepo,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.medicalEmployeeRepo = medicalEmployeeRepo;
        this.personalRegDataRepo = personalRegDataRepo;
        this.patientRepo = patientRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/patientPageForDoctor")
    public String getSpecDoctorPage(@RequestParam(value = "specDoctorPasswordError", required = false) String passwordError,
                                Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);

        String fullName = personalRegData.getName() + " " + personalRegData.getPatronymic() + "\n" +
                personalRegData.getSurname();

        model.addAttribute("specDoctorPasswordError", passwordError != null);
        model.addAttribute("specDoctorSpeciality", medicalEmployee.getSpeciality());
        model.addAttribute("specDoctorFullName", fullName);
        model.addAttribute("specDoctorBirthDate", personalRegData.getBirthDate());
        model.addAttribute("specDoctorPhoneNumber", personalRegData.getPhone());
        model.addAttribute("specDoctorEmail", personalRegData.getEmail());
        model.addAttribute("specDoctorPhoto", personalRegData.getPhoto());

        return "specDoctorPage";
    }

//    @PostMapping("/patientPageForDoctor")
//    public String addSpeciality(String speciality) {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData personalRegData = SessionData.getSessionData();
//
//        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);
//
//        medicalEmployeeRepo.updateMedicalEmployeeSetSpecialityForMedId(speciality, medicalEmployee.getMedId());
//        medicalEmployeeRepo.save(medicalEmployee);
//
//        return "specDoctorPage";
//    }

//    @PostMapping("/patientPageForDoctor/changePassword")
//    public String changePassword(String newPassword, String repeatedPassword, Model model) {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData personalRegData = SessionData.getSessionData();
//
//        model.addAttribute("newPassword", newPassword);
//        model.addAttribute("repeatedPassword", repeatedPassword);
//
//        if (newPassword.compareTo(repeatedPassword) == 0) {
//            String newEncodedPassword = bCryptPasswordEncoder.encode(newPassword);
//            personalRegDataRepo.updatePersonalRegDataSetPasswordForPersonId(newEncodedPassword, personalRegData.getId());
//        }
//
//        return "specDoctorPage";
//    }
//    @PostMapping("/doctorPage/changePhoto")
//    public String addPhoto(String photo, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();
//
//        personalRegDataRepo.updatePersonalRegDataSetPhotoForPersonId(photo, personalRegData.getId());
//        personalRegDataRepo.save(personalRegData);
//
//        model.addAttribute("photo", photo);
//        return "doctorPage";
//    }

//    @PostMapping("/medicalCard")
//    public String showPatientMedicalCard() {
//        return "medicalCard";
//    }

//    @GetMapping("/patientPageForDoctor/patientPageForDoctor")
//    public String showSpecDocPatientPage(Model model, @RequestParam(required = false) String patientSurname,
//                                  @RequestParam(required = false) String patientName,
//                                  @RequestParam(required = false) String patientPatronymic) {
//        model.addAttribute("patientSurname", patientSurname);
//        model.addAttribute("patientName", patientName);
//        model.addAttribute("patientPatronymic", patientPatronymic);
//
//        PersonalRegData patientData = personalRegDataRepo.findAllBySurnameAndNameAndPatronymic(patientSurname, patientName, patientPatronymic);
//        personalRegDataRepo.save(patientData);
//
//        model.addAttribute("patientBirthDate", patientData.getBirthDate());
//        model.addAttribute("patientPhoneNumber", patientData.getPhone());
//        model.addAttribute("patientEmail", patientData.getEmail());
////        model.addAttribute("patientPhoto", patientData.getPhoto());
//        return "patientPageForDoctor";
//    }
}
