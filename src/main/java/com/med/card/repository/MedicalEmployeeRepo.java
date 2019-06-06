package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MedicalEmployeeRepo extends JpaRepository<MedicalEmployee, Integer>{
    MedicalEmployee findMedicalEmployeeByPerson(PersonalRegData personalRegData);

    @Modifying
    @Transactional
    @Query(value = "update medical_employee me set me.speciality = ? where me.med_id = ?",
            nativeQuery = true)
    void updateMedicalEmployeeSetSpecialityForMedId(String speciality, Integer medId);
}
