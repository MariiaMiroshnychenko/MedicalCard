package com.med.card.repository;

import com.med.card.entity.ExamResultByReferral;
import com.med.card.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamResultByReferralRepo extends JpaRepository<ExamResultByReferral, Integer> {
    List<ExamResultByReferral> findAllByRefId_RefTypeAndRefId_PatientId(String refType, Patient patient);
}
