package com.med.card.controller;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.PersonalRegData;
import com.med.card.repository.MedicalEmployeeRepo;
import com.med.card.repository.PatientRepo;
import com.med.card.repository.PersonalRegDataRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/specDoctorPage")
    public String getSpecDoctorPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);

        String fullName = personalRegData.getName() + " " + personalRegData.getPatronymic() + "\n" +
                personalRegData.getSurname();

        model.addAttribute("specDoctorSpeciality", medicalEmployee.getSpeciality());
        model.addAttribute("specDoctorFullName", fullName);
        model.addAttribute("specDoctorBirthDate", personalRegData.getBirthDate());
        model.addAttribute("specDoctorPhoneNumber", personalRegData.getPhone());
        model.addAttribute("specDoctorEmail", personalRegData.getEmail());
        model.addAttribute("specDoctorPhoto", personalRegData.getPhoto());

        return "specDoctorPage";
    }

    @PostMapping("/specDoctorPage/changeLogin")
    public String changeLogin(String newLogin, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("newLogin", newLogin);

        personalRegDataRepo.updatePersonalRegDataSetLoginForPersonId(newLogin, personalRegData.getId());

        return "specDoctorPage";
    }

    @PostMapping("/specDoctorPage/changePassword")
    public String changePassword(String newPassword, String repeatedPassword, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("newPassword", newPassword);
        model.addAttribute("repeatedPassword", repeatedPassword);

        if (newPassword.compareTo(repeatedPassword) == 0) {
            String newEncodedPassword = bCryptPasswordEncoder.encode(newPassword);
            personalRegDataRepo.updatePersonalRegDataSetPasswordForPersonId(newEncodedPassword, personalRegData.getId());
        }

        return "specDoctorPage";
    }
}
