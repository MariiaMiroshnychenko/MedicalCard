package com.med.card.repository;

import com.med.card.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByMedicalCard_Patient_Person(PersonalRegData personalRegData);
    Patient findByPerson(PersonalRegData personalRegData);
//    Patient findByReferralsIs(List<Referral> referrals);

    @Modifying
    @Transactional
    @Query(value = "update patient p set p.attending_doctor_id = ? where p.person_id = ?",
            nativeQuery = true)
    void updatePatientSetAttendingDoctorForPersonId(MedicalEmployee doctorId, Integer personId);
}
