package com.med.card.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="patient")
public class Patient {
    @Id
    @Column(name = "patient_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonalRegData person;

    @OneToOne
    @JoinColumn(name = "medical_card_id")
    private MedicalCard medicalCard;

    @ManyToOne
    @JoinColumn(name = "attending_doctor_id")
    private MedicalEmployee attendingDoctor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private List<PatientVisit> patientVisits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private List<Referral> referrals;
}
