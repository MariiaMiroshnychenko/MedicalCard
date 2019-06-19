package com.med.card.repository;

import com.med.card.model.entity.MedicalEmployee;
import com.med.card.model.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalRegDataRepo extends JpaRepository<PersonalRegData, Integer> {
    List<PersonalRegData> findAllByPatient_AttendingDoctor(MedicalEmployee medicalEmployee);
    PersonalRegData findByLogin(String login);
    PersonalRegData findAllBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);
}
