package com.med.card.controller;

import com.med.card.entity.PersonalRegData;
import com.med.card.repository.PersonalRegDataRepo;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class UserDataController {
    private PersonalRegDataRepo personalRegDataRepo;
    private MessageSource messageSource;

    public UserDataController(PersonalRegDataRepo personalRegDataRepo,
                              MessageSource messageSource) {
        this.personalRegDataRepo = personalRegDataRepo;
        this.messageSource = messageSource;
    }

//    @GetMapping("/")
//    public String greeting() {
//        return "greeting";
//    }

    @GetMapping("/")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

//      todo localization!!!
//    @GetMapping("/greeting")
//    public String showCommonTemplate(Model model) {
//        model.addAttribute("main", messageSource.getMessage("page.title", null,  LocaleContextHolder.getLocale()));
//        return "greeting";
//    }

//    @GetMapping("/main")
//    public String getAllUsers(@AuthenticationPrincipal PersonalRegData personalRegData,
//            @RequestParam(required = false) String surname,
//                              String role, String speciality,
//                              Integer doctorId, String name, String doctorSurname,
//                              String patronymic,
//                              Model model) {
//        String userName = personalRegData.getName() + " " + personalRegData.getSurname();
//        model.addAttribute("userName", userName);
//        Iterable<PersonalRegData> users;
//        if (surname != null && !surname.isEmpty()) {
//            users = personalRegDataRepo.findAllBySurname(surname);
//            model.addAttribute("surname", surname);
//        } else if (role != null && !role.isEmpty()) {
//            users = personalRegDataRepo.findAllByRoleId_Title(role);
//            model.addAttribute("role", role);
//        } else if (speciality != null && !speciality.isEmpty()) {
//            model.addAttribute("speciality", speciality);
//            users = personalRegDataRepo.findAllByMedicalEmployee_Speciality(speciality);
//        /*} else if (doctorId != null) {
//            model.addAttribute("doctorId", doctorId);
//            users = personalRegDataRepo.findAllByPatient_AttendingDoctor_MedId(doctorId);*/
//        } else {
//            users = personalRegDataRepo.findAll();
//        }
//        model.addAttribute("users", users);
//        return "main";
//    }

//    @PostMapping("/filter")
//    public String filter(@RequestParam String surname, Map<String, Object> model) {
//        List<PersonalRegData> users = personalRegDataRepo.findAllBySurname(surname);
//        model.put("users", users);
//        return "main";
//    }
}
