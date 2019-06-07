package com.med.card.repository;

import com.med.card.entity.Patient;
import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByMedicalCard_Patient_Person(PersonalRegData personalRegData);
}
