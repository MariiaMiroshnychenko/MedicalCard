package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.Patient;
import com.med.card.entity.PersonalRegData;
import com.med.card.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByPerson(PersonalRegData personalRegData);
    Patient findByReferralsIs(Referral referral);
    Patient findByMedicalCard(Integer medCardId);
    Patient findByIdIs(Integer patientId);

    @Modifying
    @Query(value = "UPDATE patient p SET p.attending_doctor_id = ? WHERE p.person_id = ?",
            nativeQuery = true)
    void updatePatientSetAttendingDoctorForPersonId(MedicalEmployee doctorId, Integer personId);

    @Query(value = "SELECT medical_card_id from patient ORDER BY patient_id DESC LIMIT 1",
            nativeQuery = true)
    Integer selectPatientDescLimit();
}
