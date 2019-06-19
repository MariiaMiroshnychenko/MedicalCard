package com.med.card.controller.service;

import com.med.card.model.entity.MedicalEmployee;
import com.med.card.model.entity.PersonalRegData;

import java.util.List;

public interface PersonalRegDataProcessor extends MainService<PersonalRegData> {
    List<PersonalRegData> findAllPersonalDataByPatientAttendingDoctor(MedicalEmployee medicalEmployee);
    PersonalRegData findPersonBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);
}
