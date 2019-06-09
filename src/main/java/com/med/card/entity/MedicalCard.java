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
@Table(name="medical_card")
public class MedicalCard {
    @Id
    @Column(name = "mc_id")
    private Integer mcId;

    @OneToMany
    @JoinColumn(name = "visit_id")
    private List<PatientVisit> visitId;

    @OneToMany
    @JoinColumn(name = "ref_result_id")
    private List<ExamResultByReferral> refResultId;

    @Column(name = "final_diagnosis")
    private String finalDiagnosis;
    private String discharge;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "medicalCard")
    private Patient patient;
}
