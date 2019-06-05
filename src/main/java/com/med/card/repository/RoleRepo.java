package com.med.card.repository;

import com.med.card.entity.PersonalRegData;
import com.med.card.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findRoleByCode(String code);
    Role findRoleByCodeIsNull();
}
