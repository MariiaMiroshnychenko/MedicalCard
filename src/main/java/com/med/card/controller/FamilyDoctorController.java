package com.med.card.controller;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.Patient;
import com.med.card.entity.PersonalRegData;
import com.med.card.repository.MedicalEmployeeRepo;
import com.med.card.repository.PatientRepo;
import com.med.card.repository.PersonalRegDataRepo;
import com.med.card.service.dto.DoctorPatientInfo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class FamilyDoctorController {
    private MedicalEmployeeRepo medicalEmployeeRepo;
    private PersonalRegDataRepo personalRegDataRepo;
    private PatientRepo patientRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public FamilyDoctorController(MedicalEmployeeRepo medicalEmployeeRepo,
                                  PersonalRegDataRepo personalRegDataRepo,
                                  PatientRepo patientRepo,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.medicalEmployeeRepo = medicalEmployeeRepo;
        this.personalRegDataRepo = personalRegDataRepo;
        this.patientRepo = patientRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/doctorPage")
    public String getDoctorPage(@RequestParam(value = "passwordError", required = false) String passwordError,
                                Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);

        String fullName = personalRegData.getName() + " " + personalRegData.getPatronymic() + "\n" +
                personalRegData.getSurname();

        model.addAttribute("passwordError", passwordError != null);
        model.addAttribute("doctorSpeciality", medicalEmployee.getSpeciality());
        model.addAttribute("doctorFullName", fullName);
        model.addAttribute("doctorBirthDate", personalRegData.getBirthDate());
        model.addAttribute("doctorPhoneNumber", personalRegData.getPhone());
        model.addAttribute("doctorEmail", personalRegData.getEmail());
        model.addAttribute("doctorPhoto", personalRegData.getPhoto());

        List<PersonalRegData> attendingDoctorPatients = personalRegDataRepo.findAllByPatient_AttendingDoctor(medicalEmployee);
        List<Patient> patientMedCards = new ArrayList<>();
        List<DoctorPatientInfo> doctorPatientInfoList = new ArrayList<>();

        attendingDoctorPatients
                .forEach(person -> patientMedCards.add(patientRepo.findByMedicalCard_Patient_Person(person)));

        for (int i = 0; i < attendingDoctorPatients.size(); i++) {
            doctorPatientInfoList.add(new DoctorPatientInfo(i + 1, attendingDoctorPatients.get(i), patientMedCards.get(i)));
        }

        model.addAttribute("patientCards", doctorPatientInfoList);

        return "doctorPage";
    }

    @PostMapping("/doctorPage")
    public String addSpeciality(String speciality) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();
        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);

        medicalEmployeeRepo.updateMedicalEmployeeSetSpecialityForMedId(speciality, medicalEmployee.getMedId());
        medicalEmployeeRepo.save(medicalEmployee);

        return "doctorPage";
    }

    @PostMapping("/doctorPage/changeLogin")
    public String changeLogin(String newLogin, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("newLogin", newLogin);

        personalRegDataRepo.updatePersonalRegDataSetLoginForPersonId(newLogin, personalRegData.getId());

        return "doctorPage";
    }

    @PostMapping("/doctorPage/changePassword")
    public String changePassword(String newPassword, String repeatedPassword, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("newPassword", newPassword);
        model.addAttribute("repeatedPassword", repeatedPassword);

        if (newPassword.compareTo(repeatedPassword) == 0) {
            String newEncodedPassword = bCryptPasswordEncoder.encode(newPassword);
            personalRegDataRepo.updatePersonalRegDataSetPasswordForPersonId(newEncodedPassword, personalRegData.getId());
        }

        return "doctorPage";
    }

    @PostMapping("/medicalCard")
    public String showPatientMedicalCard() {
        return "medicalCard";
    }

    @GetMapping("/doctorPage/patientPageForDoctor")
    public String showPatientPage(Model model, @RequestParam(required = false) String patientSurname,
                                  @RequestParam(required = false) String patientName,
                                  @RequestParam(required = false) String patientPatronymic) {
        model.addAttribute("patientSurname", patientSurname);
        model.addAttribute("patientName", patientName);
        model.addAttribute("patientPatronymic", patientPatronymic);

        PersonalRegData patientData = personalRegDataRepo.findAllBySurnameAndNameAndPatronymic(patientSurname, patientName, patientPatronymic);
        personalRegDataRepo.save(patientData);

        model.addAttribute("patientBirthDate", patientData.getBirthDate());
        model.addAttribute("patientPhoneNumber", patientData.getPhone());
        model.addAttribute("patientEmail", patientData.getEmail());
        model.addAttribute("patientPhoto", patientData.getPhoto());
        return "patientPageForDoctor";
    }
}
