package com.med.card.controller;

import com.med.card.model.entity.*;
import com.med.card.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class RegistrationController {
    private PersonalRegDataRepo personalRegDataRepo;
    private RoleRepo roleRepo;
    private PatientRepo patientRepo;
    private MedicalEmployeeRepo medicalEmployeeRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationController(PersonalRegDataRepo personalRegDataRepo,
                                  RoleRepo roleRepo, PatientRepo patientRepo,
                                  MedicalEmployeeRepo medicalEmployeeRepo,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personalRegDataRepo = personalRegDataRepo;
        this.roleRepo = roleRepo;
        this.patientRepo = patientRepo;
        this.medicalEmployeeRepo = medicalEmployeeRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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
            user.setPhoto("https://www.pngkey.com/maxpic/u2q8u2w7e6y3r5y3/");
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

            List<MedicalEmployee> attendingDoctors = medicalEmployeeRepo.findAllByPerson_RoleId_Title("Сімейний лікар");
            Random random = new Random();

            Patient newPatient = Patient.builder()
                    .person(user)
                    .medicalCard(patientRepo.selectPatientDescLimit() + 1)
                    .attendingDoctor(attendingDoctors.get(random.nextInt(attendingDoctors.size())))
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
