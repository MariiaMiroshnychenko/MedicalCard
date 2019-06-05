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
@Table(name="medical_employee")
public class MedicalEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_id")
    private Integer medId;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonalRegData person;

    private String speciality;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attendingDoctor")
    private List<Patient> patients;
}
