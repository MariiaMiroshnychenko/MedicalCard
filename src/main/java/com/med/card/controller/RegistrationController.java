package com.med.card.controller;

import com.med.card.entity.PersonalRegData;
import com.med.card.entity.Role;
import com.med.card.repository.RoleRepo;
import com.med.card.repository.PersonalRegDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private PersonalRegDataRepo personalRegDataRepo;
    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(PersonalRegData user, String code, Map<String, Object> model){
        PersonalRegData userData = personalRegDataRepo.findByLogin(user.getLogin());
        Role employee = roleRepo.findRoleByCode(code);
        Role patient = roleRepo.findRoleByCodeIsNull();

        if (userData == null && !code.equals("")) {
            user.setEnabled(true);
            user.setRoleId(employee);
        } else if (userData == null && code.equals("")) {
            user.setEnabled(true);
            user.setRoleId(patient);

            createMedicalCard();
        } else {
            model.put("message", "User exists!");
            return "registration";
        }

        personalRegDataRepo.save(user);
        return "redirect:/login";
    }

    @Modifying
    @Query(value = "insert into MedicalCard (visitId, refResultId, status) values (:name, :age, :email, :status)",
            nativeQuery = true)
    void createMedicalCard(@Param("name") String name, @Param("age") Integer age,
                    @Param("status") Integer status, @Param("email") String email);
    private void createMedicalCard() {
    }
}
