package com.med.card.controller.service.implementation;

import com.med.card.model.entity.MedicalEmployee;
import com.med.card.model.entity.PersonalRegData;
import com.med.card.repository.PersonalRegDataRepo;
import com.med.card.controller.service.PersonalRegDataProcessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalRegDataService implements UserDetailsService, PersonalRegDataProcessor {
    private PersonalRegDataRepo personalRegDataRepo;

    public PersonalRegDataService(PersonalRegDataRepo personalRegDataRepo) {
        this.personalRegDataRepo = personalRegDataRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return personalRegDataRepo.findByLogin(s);
    }

    @Override
    public List<PersonalRegData> findAllPersonalDataByPatientAttendingDoctor(MedicalEmployee medicalEmployee) {
        return personalRegDataRepo.findAllByPatient_AttendingDoctor(medicalEmployee);
    }

    @Override
    public PersonalRegData findPersonBySurnameAndNameAndPatronymic(String surname, String name, String patronymic) {
        return personalRegDataRepo.findAllBySurnameAndNameAndPatronymic(surname, name, patronymic);
    }

    @Override
    public void save(PersonalRegData personalRegData) {
        personalRegDataRepo.save(personalRegData);
    }
}
