package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.Patient;
import com.med.card.entity.PatientVisit;
import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientVisitRepo extends JpaRepository<PatientVisit, Integer> {
    List<PatientVisit> findAllByPatientId(Patient patient);
//    List<PatientVisit> findAllByDoctorId_Person(PersonalRegData personalRegData);
//    List<PatientVisit> findAllByVisitDate(LocalDateTime localDateTime);
}
