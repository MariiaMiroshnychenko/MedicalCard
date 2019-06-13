package com.med.card.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "patient_visit")
public class PatientVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_referral_to_doctor")
    private Referral idReferralToDoctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientId;

    @Column(name = "visit_date")
    private LocalDateTime visitDate;

    private String diagnosis;

    @Column(name = "app_type")
    private String appType;
    private String appointment;

    @Column(name = "app_state")
    private boolean appState = true;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private MedicalEmployee doctorId;
}
