package com.med.card.repository;

import com.med.card.model.entity.MedicalEmployee;
import com.med.card.model.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalEmployeeRepo extends JpaRepository<MedicalEmployee, Integer>{
    MedicalEmployee findMedicalEmployeeByPerson(PersonalRegData personalRegData);
    List<MedicalEmployee> findAllByPerson_RoleId_Title(String roleTitle);
}
