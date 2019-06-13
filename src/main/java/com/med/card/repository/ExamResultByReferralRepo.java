package com.med.card.repository;

import com.med.card.entity.ExamResultByReferral;
import com.med.card.entity.Patient;
import com.med.card.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamResultByReferralRepo extends JpaRepository<ExamResultByReferral, Integer> {
//    ExamResultByReferral findByRefId(Referral referral);
//    List<ExamResultByReferral> findAllByRefId_RefType(String refType);
    List<ExamResultByReferral> findAllByRefId_RefTypeAndRefId_PatientId(String refType, Patient patient);
}
