//package com.med.card.controller;
//
//import com.med.card.entity.MedicalEmployee;
//import com.med.card.entity.Patient;
//import com.med.card.entity.PersonalRegData;
//import com.med.card.repository.MedicalEmployeeRepo;
//import com.med.card.repository.PatientRepo;
//import com.med.card.repository.PersonalRegDataRepo;
//import com.med.card.service.dto.DoctorPatientInfo;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class SpecDoctorController {
//    private MedicalEmployeeRepo medicalEmployeeRepo;
//    private PersonalRegDataRepo personalRegDataRepo;
//    private PatientRepo patientRepo;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public SpecDoctorController(MedicalEmployeeRepo medicalEmployeeRepo,
//                                PersonalRegDataRepo personalRegDataRepo,
//                                PatientRepo patientRepo,
//                                BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.medicalEmployeeRepo = medicalEmployeeRepo;
//        this.personalRegDataRepo = personalRegDataRepo;
//        this.patientRepo = patientRepo;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @GetMapping("/specDoctorPage")
//    public String getSpecDoctorPage(@RequestParam(value = "passwordError", required = false) String passwordError,
//                                Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();
//
//        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);
//
//        String fullName = personalRegData.getName() + " " + personalRegData.getPatronymic() + "\n" +
//                personalRegData.getSurname();
//
//        model.addAttribute("passwordError", passwordError != null);
//        model.addAttribute("doctorSpeciality", medicalEmployee.getSpeciality());
//        model.addAttribute("doctorFullName", fullName);
//        model.addAttribute("doctorBirthDate", personalRegData.getBirthDate());
//        model.addAttribute("doctorPhoneNumber", personalRegData.getPhone());
//        model.addAttribute("doctorEmail", personalRegData.getEmail());
//        model.addAttribute("doctorPhoto", personalRegData.getPhoto());
//
//        List<PersonalRegData> attendingDoctorPatients = personalRegDataRepo.findAllByPatient_AttendingDoctor(medicalEmployee);
//        List<Patient> patientMedCards = new ArrayList<>();
//        List<DoctorPatientInfo> doctorPatientInfoList = new ArrayList<>();
//
//        attendingDoctorPatients
//                .forEach(person -> patientMedCards.add(patientRepo.findByMedicalCard_Patient_Person(person)));
//
//        for (int i = 0; i < attendingDoctorPatients.size(); i++) {
//            doctorPatientInfoList.add(new DoctorPatientInfo(i + 1, attendingDoctorPatients.get(i), patientMedCards.get(i)));
//        }
//
//        model.addAttribute("patientCards", doctorPatientInfoList);
//
//        return "specDoctorPage";
//    }
//
////    @PostMapping("/specDoctorPage")
////    public String addSpeciality(String speciality) {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();
////
////        MedicalEmployee medicalEmployee = medicalEmployeeRepo.findMedicalEmployeeByPerson(personalRegData);
////
////        medicalEmployeeRepo.updateMedicalEmployeeSetSpecialityForMedId(speciality, medicalEmployee.getMedId());
////        medicalEmployeeRepo.save(medicalEmployee);
////
////        return "specDoctorPage";
////    }
//
//    //todo check login changing!!!
//    @PostMapping("/specDoctorPage/changeLogin")
//    public String changeSpecDoctorLogin(String newLogin, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();
//
//        model.addAttribute("newLogin", newLogin);
//        System.out.println(personalRegData.getId());
//
//        personalRegDataRepo.updatePersonalRegDataSetLoginForPersonId(newLogin, personalRegData.getId());
//
//        return "specDoctorPage";
//    }
//
//    @PostMapping("/specDoctorPage/changePassword")
//    public String changeSpecDoctorPassword(String newPassword, String repeatedPassword, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();
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
////    @PostMapping("/doctorPage/changePhoto")
////    public String addPhoto(String photo, Model model) {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();
////
////        personalRegDataRepo.updatePersonalRegDataSetPhotoForPersonId(photo, personalRegData.getId());
////        personalRegDataRepo.save(personalRegData);
////
////        model.addAttribute("photo", photo);
////        return "doctorPage";
////    }
//
//    @PostMapping("/medicalCard")
//    public String showPatientMedicalCard() {
//        return "medicalCard";
//    }
//}
