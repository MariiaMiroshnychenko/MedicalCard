package com.med.card.repository;

import com.med.card.entity.Patient;
import com.med.card.entity.PersonalRegData;
import com.med.card.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByPerson(PersonalRegData personalRegData);
    Patient findByReferralsIs(Referral referral);
    Patient findByMedicalCard(Integer medCardId);
    Patient findByIdIs(Integer patientId);

    @Query(value = "SELECT medical_card_id from patient ORDER BY patient_id DESC LIMIT 1",
            nativeQuery = true)
    Integer selectPatientDescLimit();
}
