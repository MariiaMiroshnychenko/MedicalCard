package com.med.card.service;

import com.med.card.repository.PersonalRegDataRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonalRegDataService implements UserDetailsService {
    private PersonalRegDataRepo personalRegDataRepo;

    public PersonalRegDataService(PersonalRegDataRepo personalRegDataRepo) {
        this.personalRegDataRepo = personalRegDataRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return personalRegDataRepo.findByLogin(s);
    }
}
