package com.med.card.controller;

import com.med.card.entity.PersonalRegData;
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
    @GetMapping("/specDoctorPage")
    public String getSpecDoctorPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonalRegData personalRegData = (PersonalRegData) authentication.getPrincipal();

        model.addAttribute("specDocData", personalRegData);

        return "specDoctorPage";
    }
}
