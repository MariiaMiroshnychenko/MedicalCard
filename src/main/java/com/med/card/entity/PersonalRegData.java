package com.med.card.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="personal_reg_data")
public class PersonalRegData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer id;

    private String surname;
    private String name;
    private String patronymic;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "pass_series")
    private String passSeries;

    @Column(name = "pass_number")
    private String passNumber;

    @Column(name = "ident_code")
    private String identCode;

    private String phone;
    private String email;

    private String login;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role roleId;

    private Boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private MedicalEmployee medicalEmployee;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Patient patient;
}
