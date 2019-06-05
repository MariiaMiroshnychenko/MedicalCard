package com.med.card.repository;

import com.med.card.entity.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCardRepo extends JpaRepository<MedicalCard, Integer> {
}
