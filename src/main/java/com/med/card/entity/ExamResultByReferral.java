package com.med.card.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "exam_result_by_referral")
public class ExamResultByReferral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "examResultId")
    private Referral refId;

    @Column(name = "res_date")
    private LocalDateTime resDate;

    @Column(name = "result_text")
    private String resultText;

    @OneToOne
    @JoinColumn(name = "resp_doctor_id")
    private MedicalEmployee respDoctorId;
}
