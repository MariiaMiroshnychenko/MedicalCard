package com.med.card.repository;

import com.med.card.model.entity.MedicalEmployee;
import com.med.card.model.entity.Patient;
import com.med.card.model.entity.PatientVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PatientVisitRepo extends JpaRepository<PatientVisit, Integer> {
    List<PatientVisit> findAllByPatientId(Patient patient);

    List<PatientVisit> findAllByDoctorIdAndAppTypeAndAppState(MedicalEmployee person, String appType, boolean state);
    List<PatientVisit> findAllByAppTypeAndAppState(String appType, boolean state);

    @Modifying
    @Query(value = "UPDATE patient_visit pv SET app_state=? WHERE id=?",
            nativeQuery = true)
    void updatePatientVisitSetAppStateForId(boolean state, Integer visitId);

    @Modifying
    @Query(value = "UPDATE patient_visit pv SET resp_med_employee=? WHERE id=?",
            nativeQuery = true)
    void updatePatientVisitSetExecutedByPersonForId(Integer medId, Integer visitId);
}
