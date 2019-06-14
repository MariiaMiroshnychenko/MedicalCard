package com.med.card.controller;

import com.med.card.entity.*;
import com.med.card.other.MedicalCardRepo;
import com.med.card.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    public RegistrationController(PersonalRegDataRepo personalRegDataRepo, RoleRepo roleRepo,
                                  MedicalCardRepo medicalCardRepo, PatientRepo patientRepo,
                                  MedicalEmployeeRepo medicalEmployeeRepo,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personalRegDataRepo = personalRegDataRepo;
        this.roleRepo = roleRepo;
        this.medicalCardRepo = medicalCardRepo;
        this.patientRepo = patientRepo;
        this.medicalEmployeeRepo = medicalEmployeeRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private PersonalRegDataRepo personalRegDataRepo;
    private RoleRepo roleRepo;
    private MedicalCardRepo medicalCardRepo;
    private PatientRepo patientRepo;
    private MedicalEmployeeRepo medicalEmployeeRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(PersonalRegData user, String code,
                          String speciality, Map<String, Object> model){
        PersonalRegData userData = personalRegDataRepo.findByLogin(user.getLogin());
        Role employee = roleRepo.findRoleByCode(code);
        Role patient = roleRepo.findRoleByCodeIsNull();

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (userData == null && !code.equals("")) {
            user.setEnabled(true);
            user.setRoleId(employee);
            personalRegDataRepo.save(user);

            MedicalEmployee medicalEmployee = MedicalEmployee.builder()
                    .person(user)
                    .speciality(speciality)
                    .build();
            medicalEmployeeRepo.save(medicalEmployee);

        } else if (userData == null && code.equals("")) {
            user.setEnabled(true);
            user.setRoleId(patient);

            personalRegDataRepo.save(user);

            MedicalCard medicalCard = new MedicalCard();
            medicalCardRepo.save(medicalCard);

            Patient newPatient = Patient.builder()
                    .person(user)
                    .medicalCard(medicalCard)
                    .build();
            patientRepo.save(newPatient);

        } else {
            model.put("message", "User exists!");
            return "registration";
        }

        personalRegDataRepo.save(user);
        return "redirect:/login";
    }
}
