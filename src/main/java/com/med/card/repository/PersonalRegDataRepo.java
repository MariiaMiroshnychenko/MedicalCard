package com.med.card.repository;

import com.med.card.entity.PersonalRegData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonalRegDataRepo extends JpaRepository<PersonalRegData, Integer> {
    PersonalRegData findByLogin(String login);
    //Admin config
    List<PersonalRegData> findAllBySurname(String surname);
    List<PersonalRegData> findAllByRoleId_Title(String roleTitle);

    //Patient config
    List<PersonalRegData> findAllByMedicalEmployee_Speciality(String speciality);

    //Doctor config
    List<PersonalRegData> findAllByPatient_AttendingDoctor_MedId(Integer doctorId);

    @Modifying
    @Transactional
    @Query(value = "update personal_reg_data prd set prd.phone = ? where prd.person_id = ?",
            nativeQuery = true)
    void updatePersonalRegDataSetPhoneForPersonId(String phone, Integer medId);

    @Modifying
    @Transactional
    @Query(value = "update personal_reg_data prd set prd.email = ? where prd.person_id = ?",
            nativeQuery = true)
    void updatePersonalRegDataSetEmailForPersonId(String email, Integer medId);

    @Modifying
    @Transactional
    @Query(value = "update personal_reg_data prd set prd.login = ? where prd.person_id = ?",
            nativeQuery = true)
    void updatePersonalRegDataSetLoginForPersonId(String login, Integer medId);

    @Modifying
    @Transactional
    @Query(value = "update personal_reg_data prd set prd.password = ? where prd.person_id = ?",
            nativeQuery = true)
    void updatePersonalRegDataSetPasswordForPersonId(String password, Integer medId);
}
