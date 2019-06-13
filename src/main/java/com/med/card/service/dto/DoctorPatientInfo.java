package com.med.card.service.dto;

import com.med.card.entity.Patient;
import com.med.card.entity.PersonalRegData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorPatientInfo {
    int counter;
    PersonalRegData personalRegData;
    Patient patient;
}
