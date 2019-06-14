package com.med.card.repository;

import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PersonalRegDataRepo extends JpaRepository<PersonalRegData, Integer> {
    List<PersonalRegData> findAllByRoleId_Title(String roleTitle);
    List<PersonalRegData> findAllByPatient_AttendingDoctor(MedicalEmployee medicalEmployee);

    PersonalRegData findByLogin(String login);
    PersonalRegData findAllBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);

    @Modifying
    @Query(value = "update personal_reg_data prd set prd.login = ? where prd.person_id = ?",
            nativeQuery = true)
    void updatePersonalRegDataSetLoginForPersonId(String login, Integer personId);

    @Modifying
    @Query(value = "update personal_reg_data prd set prd.password = ? where prd.person_id = ?",
            nativeQuery = true)
    void updatePersonalRegDataSetPasswordForPersonId(String password, Integer personId);
}
