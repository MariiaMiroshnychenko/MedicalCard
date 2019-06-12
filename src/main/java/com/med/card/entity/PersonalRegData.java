package com.med.card.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="personal_reg_data")
public class PersonalRegData implements UserDetails {
    @Id
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

    private boolean enabled;
    private String photo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private MedicalEmployee medicalEmployee;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Patient patient;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(getRoleId());
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
