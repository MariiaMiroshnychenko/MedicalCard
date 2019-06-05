package com.med.card.entity;

import lombok.*;

import javax.persistence.*;

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
    PersonalRegData person;

    @OneToOne
    @JoinColumn(name = "medical_card_id")
    MedicalCard medicalCard;

    @ManyToOne
    @JoinColumn(name = "attending_doctor_id")
    MedicalEmployee attendingDoctor;
}
