package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonalRegDataRepo extends JpaRepository<PersonalRegData, Integer> {
    List<PersonalRegData> findAllByPatient_AttendingDoctor(MedicalEmployee medicalEmployee);
    PersonalRegData findByLogin(String login);
    PersonalRegData findAllBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);
}
