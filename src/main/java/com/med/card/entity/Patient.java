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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonalRegData person;

    @Column(name = "medical_card_id")
    private Integer medicalCard;

    @ManyToOne
    @JoinColumn(name = "attending_doctor_id")
    private MedicalEmployee attendingDoctor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private List<PatientVisit> patientVisits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private List<Referral> referrals;
}
