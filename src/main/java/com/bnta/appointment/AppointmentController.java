package com.bnta.appointment;

import com.bnta.patient.Patient;
import com.bnta.patient.PatientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private PatientService patientService;
    private AppointmentService appointmentService;

    public AppointmentController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    // Add appointment
    @PostMapping
    public void addAppointment(@Valid @RequestBody Appointment appointment) {
        appointmentService.bookAppointment(appointment);
    }

    // Get all ONE appointment by id
    @GetMapping("{id}")
    public Appointment getAppointments(@PathVariable("id") Integer appointmentId) {
        //wait for service methods
        return appointmentService.selectAppointmentById(appointmentId);
    }


    // Get all appointments
    @GetMapping
    public List<Appointment> getAppointment(){

        return appointmentService.viewAllAppointments();
    }

        //Update Appointments
    @PutMapping("{id}")
    public void updateAppointmentById(@PathVariable("id") Integer id,@RequestBody Appointment update) {
        appointmentService.updateAppointment(id, update);
    }


    //Delete Appointments by ID
    @DeleteMapping("{id}")
    public void deleteAppointment(@PathVariable("id") Integer id) {
        appointmentService.deleteAppointmentById(id);
    }


    @GetMapping("bloodtype/{bloodType}")
        public List<Appointment> getAppointmentByPatientBloodType(@PathVariable("bloodType") String bloodType) {
            return appointmentService.getAppointmentByPatientBloodType(bloodType);



        }

    @GetMapping("/list")
    public List<AppointmentJoint> showAllAppointmentsWithNames(){
        return appointmentService.showAllAppointmentsWithNames();
    }

    @GetMapping("/patient/{name}")
    public List<AppointmentJoint> showAllAppointmentsByPatientName(@PathVariable("name") String name){
        List<AppointmentJoint> allAppointments = appointmentService.showAllAppointmentsWithNames();
        List<AppointmentJoint> patientAppointments = allAppointments.stream()
                .filter(appointment -> appointment.getPatientName().toLowerCase().equals(name.toLowerCase()))
                .collect(Collectors.toList());
        return patientAppointments;
    }


    @GetMapping("/doctor/{name}")
    public List<AppointmentJoint> showAllAppointmentsByDoctorName(@PathVariable("name") String name){
        List<AppointmentJoint> allAppointments = appointmentService.showAllAppointmentsWithNames();
        List<AppointmentJoint> doctorAppointments = allAppointments.stream()
                .filter(appointment -> appointment.getDoctorName().toLowerCase().equals("dr " + name.toLowerCase()))
                .collect(Collectors.toList());
        return doctorAppointments;
    }

}

