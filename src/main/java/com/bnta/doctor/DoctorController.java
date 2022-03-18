package com.bnta.doctor;

import com.bnta.appointment.Appointment;
import com.bnta.appointment.AppointmentService;
import com.bnta.patient.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("doctors")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Add doctor
    @PostMapping
    public void addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
    }

//    @PostMapping("/fill")
//    public void addPresetDoctors(){
//        doctorService.addPresetDoctors();
//    }

    // Get all doctors by id
    @GetMapping("{id}")
    public Doctor getDoctorsById (@PathVariable("id") Integer doctorId) {
        //wait for service methods
        return doctorService.selectDoctorById(doctorId);
    }

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    //Update Doctor
    @PutMapping("{id}")
    public void updateDoctorById(@RequestBody Doctor update, @PathVariable("id") Integer id) {
        doctorService.updateDoctorById(id, update);
    }

    //Delete Doctors by ID
    @DeleteMapping("{id}")
    public void deleteDoctor(@PathVariable("id")Integer id) {doctorService.deleteDoctorById(id);
    }

    //Delete All Doctors [admin]
    @DeleteMapping()
    public void deleteAllDoctors() {doctorService.deleteAllDoctors();
    }

}