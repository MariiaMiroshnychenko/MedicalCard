package com.med.card.other;

import com.med.card.entity.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCardRepo extends JpaRepository<MedicalCard, Integer> {
}
