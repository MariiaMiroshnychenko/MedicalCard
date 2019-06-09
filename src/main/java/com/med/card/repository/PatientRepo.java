package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.Patient;
import com.med.card.entity.PatientVisit;
import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByMedicalCard_Patient_Person(PersonalRegData personalRegData);
    Patient findByPerson(PersonalRegData personalRegData);
    @Modifying
    @Transactional
    @Query(value = "update patient p set p.attending_doctor_id = ? where p.person_id = ?",
            nativeQuery = true)
    void updatePatientSetAttendingDoctorForPersonId(MedicalEmployee doctorId, Integer personId);
}
