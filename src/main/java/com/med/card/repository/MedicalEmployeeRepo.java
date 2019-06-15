package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MedicalEmployeeRepo extends JpaRepository<MedicalEmployee, Integer>{
    MedicalEmployee findMedicalEmployeeByPerson(PersonalRegData personalRegData);
    List<MedicalEmployee> findAllByPerson_RoleId_Title(String roleTitle);
}
