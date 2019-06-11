package com.med.card.repository;

import com.med.card.entity.DoctorSchedule;
import com.med.card.entity.MedicalEmployee;
import com.med.card.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface DoctorScheduleRepo extends JpaRepository<DoctorSchedule, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into doctor_schedule(v_date, v_time, patient_id, doctor_id, enabled)" +
            " VALUES (?, ?, ?, ?, ?)",
            nativeQuery = true)
    void insertDoctorScheduleValuesDateTimePatientDoctorEnabled(Date date, Time time,
                                                                Patient patient, MedicalEmployee doctor,
                                                                boolean enabled);

    List<DoctorSchedule> findAllByDate(Date date);
}
