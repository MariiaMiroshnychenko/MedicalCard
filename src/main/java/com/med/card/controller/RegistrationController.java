package com.med.card.controller;

import com.med.card.entity.*;
import com.med.card.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private PersonalRegDataRepo personalRegDataRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private MedicalCardRepo medicalCardRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private MedicalEmployeeRepo medicalEmployeeRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(PersonalRegData user, String code, Map<String, Object> model){
        PersonalRegData userData = personalRegDataRepo.findByLogin(user.getLogin());
        Role employee = roleRepo.findRoleByCode(code);
        Role patient = roleRepo.findRoleByCodeIsNull();

        if (userData == null && !code.equals("")) {
            user.setEnabled(true);
            user.setRoleId(employee);
            personalRegDataRepo.save(user);

            MedicalEmployee medicalEmployee = MedicalEmployee.builder()
                    .person(user)
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
