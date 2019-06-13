package com.med.card.other;

import com.med.card.entity.PersonalRegData;
import com.med.card.repository.PersonalRegDataRepo;
import org.springframework.ui.Model;

public class AuthDataController {
PersonalRegDataRepo personalRegDataRepo;

    AuthDataController(PersonalRegDataRepo personalRegDataRepo) {
        this.personalRegDataRepo = personalRegDataRepo;
    }

//    @PostMapping("/changeLogin")
    public String changeLogin(String newLogin, Model model,
                              PersonalRegData sessionPersonalRegData, String pageName) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonalRegData personalRegData = SessionData.getSessionData();

        model.addAttribute("newLogin", newLogin);
//        System.out.println(personalRegData.getId());

        personalRegDataRepo.updatePersonalRegDataSetLoginForPersonId(newLogin, sessionPersonalRegData.getId());

        return pageName;
    }
}
