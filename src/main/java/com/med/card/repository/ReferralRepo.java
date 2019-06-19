package com.med.card.repository;

import com.med.card.model.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferralRepo extends JpaRepository<Referral, Integer> {
    Referral findByIdIs(Integer id);
}
