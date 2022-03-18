package com.bnta.patient;

import com.bnta.appointment.Appointment;
import com.bnta.appointment.AppointmentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {

    private PatientService patientService;
    private AppointmentService appointmentService;

    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

// Patient options:

    // Add patient
    @PostMapping
    public void addPatient(@Valid @RequestBody Patient patient){
        patientService.addNewPatient(patient);
    }

    // Get all patients
    @GetMapping
    public List<Patient> getPatients(){
        return patientService.findAllPatients();
    }

    // Get patient by ID
    @GetMapping(path = "{id}")
    public Patient getPatients(@PathVariable("id") Integer patientId){
            return patientService.findPatientById(patientId);
    }

    // Update patient details
    @PutMapping("{id}")
    public void updatePatientById(@RequestBody Patient patient, @PathVariable("id") Integer id) {
            patientService.updatePatient(id, patient);
    }

    // Delete patient by ID
    @DeleteMapping("{id}")
    public void deletePatient(@PathVariable("id") Integer id) {
            patientService.deletePatientById(id);
    }

    // Delete all patients
    @DeleteMapping
    public void deleteAllPatients() {patientService.deleteAllPatients();
    }

    }

