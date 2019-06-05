package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalEmployeeRepo extends JpaRepository<MedicalEmployee, Integer>{
}
