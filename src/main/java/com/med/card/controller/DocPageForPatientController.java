package com.med.card.controller;

import com.med.card.entity.PersonalRegData;
import com.med.card.repository.PersonalRegDataRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocPageForPatientController {
    private PersonalRegDataRepo personalRegDataRepo;

    public DocPageForPatientController(PersonalRegDataRepo personalRegDataRepo) {
        this.personalRegDataRepo = personalRegDataRepo;
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

        model.addAttribute("attendingDoctor", doctorData);

        return "doctorPageForPatient";
    }
}
