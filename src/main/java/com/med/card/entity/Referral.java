package com.med.card.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "referral")
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ref_type")
    private String refType;

    @Column(name = "act_title")
    private String actTitle;

    @Column(name = "ref_date")
    private LocalDateTime refDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private MedicalEmployee doctorId;

    @OneToOne
    @JoinColumn(name = "exam_result_id")
    private ExamResultByReferral examResultId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idReferralToDoctor")
    private PatientVisit patientVisit;
}
