package com.med.card.repository;

import com.med.card.entity.Patient;
import com.med.card.entity.PersonalRegData;
import com.med.card.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReferralRepo extends JpaRepository<Referral, Integer> {
//    List<Referral> findAllByPatientIdAndRefTypeIs(Patient patient, String refType);
//    List<Referral> findAllByDoctorId_PersonAndPatientId_PersonAndRefDateIs(PersonalRegData doctor, PersonalRegData patient, LocalDateTime visitDate);
    Referral findByIdIs(Integer id);
}
