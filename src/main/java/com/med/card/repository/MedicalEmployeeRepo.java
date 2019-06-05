package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalEmployeeRepo extends JpaRepository<MedicalEmployee, Integer>{
}
