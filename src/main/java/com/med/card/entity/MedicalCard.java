package com.med.card.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="medical_card")
public class MedicalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mc_id")
    private Integer mcId;

    @Column(name = "visit_id")
    private Integer visitId;

    @Column(name = "ref_result_id")
    private Integer refResultId;

    @Column(name = "final_diagnosis")
    private String finalDiagnosis;
    private String discharge;


}
