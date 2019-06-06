package com.med.card.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="role")
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    private String title;
    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private List<PersonalRegData> personalRegData;

    @Override
    public String getAuthority() {
        return getTitle();
    }
}
