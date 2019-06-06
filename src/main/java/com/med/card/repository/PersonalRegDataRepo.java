package com.med.card.repository;

import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalRegDataRepo extends JpaRepository<PersonalRegData, Integer> {
    PersonalRegData findByLogin(String login);
    //Admin config
    List<PersonalRegData> findAllBySurname(String surname);
    List<PersonalRegData> findAllByRoleId_Title(String roleTitle);

    //Patient config
    List<PersonalRegData> findAllByMedicalEmployee_Speciality(String speciality);

    //Doctor config
    List<PersonalRegData> findAllByPatient_AttendingDoctor_MedId(Integer doctorId);
}
