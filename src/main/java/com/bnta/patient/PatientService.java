package com.bnta.patient;

import com.bnta.appointment.Appointment;
import com.bnta.exception.AppointmentNotFoundException;
import com.bnta.exception.IllegalStateException;
import com.bnta.exception.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class PatientService {

    private PatientDAO patientDAO;

    public PatientService(@Qualifier("patientrepo") PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public boolean doesPatientWithIdExist(Integer patientNhsId) {
        return patientDAO
                .selectAllPatients()
                .stream()
                .anyMatch(p -> Objects.equals(p.getPatientNhsId(), patientNhsId));
    }

    public int addNewPatient(Patient patient) {
        // check if patient object is submitted
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        // check that the patient object is complete
        else if (patient.getPatientName() == null ||
                patient.getPatientEmailAddress() == null ||
                patient.getPatientPhoneNumber() == null ||
                patient.getBloodType() == null) {
            throw new IllegalStateException("Patient cannot have empty fields");
        }
        // check patient does not already exist
        boolean exists = doesPatientWithIdExist(patient.getPatientNhsId());
        if (exists) {
            throw new IllegalStateException("Patient with id " + patient.getPatientNhsId() + " already exists");
        } else {
            // ADD PATIENT
            int result = patientDAO.insertPatient(patient);
            if (result != 1) {
                throw new IllegalStateException("Could not register new patient.");
            } else {
                return 1;
            }
        }
    }

    public Patient findPatientById(Integer id) {
        if (id == null || id <= 0) {
            throw new PatientNotFoundException("Invalid patient ID");
        }
        Patient output = patientDAO.selectPatientById(id);
        if (output == null) {
            throw new PatientNotFoundException("Patient with ID " + id + " not found");
        } else {
            return output;
        }
    }

    public List<Patient> findAllPatients() {
            return patientDAO.selectAllPatients();
    }

    public int updatePatient(Integer id, Patient update) {
        if (update == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        } else if (update.getPatientName() == null ||
                update.getPatientEmailAddress() == null ||
                update.getPatientPhoneNumber() == null ||
                update.getBloodType() == null) {
            throw new IllegalStateException("Patient cannot have empty fields");
        } else {
            findPatientById(id);
            int output = patientDAO.updatePatient(id, update);
            if (output != 1) {
                throw new IllegalStateException("Could not update patient.");
            }
            return output;
        }
    }

    public int deletePatientById(Integer id) {
        boolean exists = doesPatientWithIdExist(id);
        if (exists) {
            Patient delPatient = patientDAO.selectPatientById(id);
            return patientDAO.deletePatient(delPatient);
        } else {
            throw new PatientNotFoundException("Patient with ID '" + id + "' not found.");
        }
    }

    public void deleteAllPatients() {
        patientDAO.deleteAllPatients();
    }
}

