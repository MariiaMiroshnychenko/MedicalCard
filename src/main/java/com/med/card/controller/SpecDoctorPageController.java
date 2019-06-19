package com.med.card.controller;

import com.med.card.model.entity.PersonalRegData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpecDoctorPageController {
    @GetMapping("/specDoctorPage")
    public String getSpecDoctorPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("specDocData", personalRegData);

        return "specDoctorPage";
    }
}
