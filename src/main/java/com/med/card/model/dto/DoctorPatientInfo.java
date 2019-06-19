package com.med.card.model.dto;

import com.med.card.model.entity.Patient;
import com.med.card.model.entity.PersonalRegData;
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
