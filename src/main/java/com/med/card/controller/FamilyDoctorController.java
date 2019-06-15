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
public class FamilyDoctorController {
    private MedicalEmployeeRepo medicalEmployeeRepo;
    private PersonalRegDataRepo personalRegDataRepo;
    private PatientRepo patientRepo;

    public FamilyDoctorController(MedicalEmployeeRepo medicalEmployeeRepo,
                                  PersonalRegDataRepo personalRegDataRepo,
                                  PatientRepo patientRepo) {
        this.medicalEmployeeRepo = medicalEmployeeRepo;
        this.personalRegDataRepo = personalRegDataRepo;
        this.patientRepo = patientRepo;
    }

    @GetMapping("/doctorPage")
    public String getDoctorPage(@RequestParam(value = "passwordError", required = false) String passwordError,
                                Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);

        List<PersonalRegData> attendingDoctorPatients = personalRegDataRepo.findAllByPatient_AttendingDoctor(medicalEmployee);
//        List<Patient> patientMedCards = new ArrayList<>();
//        List<DoctorPatientInfo> doctorPatientInfoList = new ArrayList<>();

//        attendingDoctorPatients
//                .forEach(person -> patientMedCards.add(patientRepo.findByMedicalCard_Patient_Person(person)));

//        for (int i = 0; i < attendingDoctorPatients.size(); i++) {
//            doctorPatientInfoList.add(new DoctorPatientInfo(i + 1, attendingDoctorPatients.get(i), patientMedCards.get(i)));
//        }

        model.addAttribute("passwordError", passwordError != null);
        model.addAttribute("docData", personalRegData);
        model.addAttribute("patients", attendingDoctorPatients);

        return "doctorPage";
    }

//    @PostMapping("/medicalCard")
//    public String showPatientMedicalCard() {
//        return "medicalCard";
//    }

    @GetMapping("/doctorPage/patientPageForDoctor")
    public String showPatientPage(Model model, @RequestParam(required = false) String patientSurname,
                                  @RequestParam(required = false) String patientName,
                                  @RequestParam(required = false) String patientPatronymic) {
        model.addAttribute("patientSurname", patientSurname);
        model.addAttribute("patientName", patientName);
        model.addAttribute("patientPatronymic", patientPatronymic);

        PersonalRegData patientData = personalRegDataRepo.findAllBySurnameAndNameAndPatronymic(patientSurname, patientName, patientPatronymic);
        personalRegDataRepo.save(patientData);

        model.addAttribute("patientData", patientData);
        return "patientPageForDoctor";
    }
}
