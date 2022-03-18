package com.bnta.doctor;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> getAllDoctors();

    int addDoctor(Doctor doctor);

    int updateDoctorById(Integer id, Doctor update);

    Doctor selectDoctorById(Integer id);

    int deleteDoctorById(Integer id);

    int addPresetDoctors();

    int deleteAllDoctors();
}
