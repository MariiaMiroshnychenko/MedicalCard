package com.med.card.controller;

import com.med.card.controller.service.PersonalRegDataProcessor;
import com.med.card.model.entity.PersonalRegData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocPageForPatientController {
    private PersonalRegDataProcessor personalRegDataProcessor;

    public DocPageForPatientController(PersonalRegDataProcessor personalRegDataProcessor) {
        this.personalRegDataProcessor = personalRegDataProcessor;
    }

    @GetMapping("/doctorPageForPatient")
    public String showDoctorPage(Model model, @RequestParam(required = false) String attendingDoctorSurname,
                                 @RequestParam(required = false) String attendingDoctorName,
                                 @RequestParam(required = false) String attendingDoctorPatronymic) {
        model.addAttribute("attendingDoctorSurname", attendingDoctorSurname);
        model.addAttribute("attendingDoctorName", attendingDoctorName);
        model.addAttribute("attendingDoctorPatronymic", attendingDoctorPatronymic);

        PersonalRegData doctorData =
                personalRegDataProcessor.findPersonBySurnameAndNameAndPatronymic(
                        attendingDoctorSurname, attendingDoctorName, attendingDoctorPatronymic);

        personalRegDataProcessor.save(doctorData);

        model.addAttribute("attendingDoctor", doctorData);

        return "doctorPageForPatient";
    }
}
