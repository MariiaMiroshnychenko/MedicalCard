package com.med.card.repository;

import com.med.card.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findRoleByCode(String code);
    Role findRoleByCodeIsNull();
}
