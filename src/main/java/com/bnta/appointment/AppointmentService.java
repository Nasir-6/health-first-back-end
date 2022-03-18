package com.bnta.appointment;
import com.bnta.doctor.Doctor;
import com.bnta.doctor.DoctorDAO;
import com.bnta.doctor.DoctorDAS;
import com.bnta.exception.AppointmentNotFoundException;
import com.bnta.exception.IllegalStateException;
import com.bnta.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.bnta.doctor.DoctorService;
import com.bnta.patient.PatientService;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    /* What we want this service to do;
    1- to add a appointment
    check if patient exists- selectpPatientById(Integer id)


    1 - Add Patient appointments.
    Get Patient information, and Create a booking for them, initiate a for loop by patientId and if empty create an 'instance' to add Doctor, patient and, appointment room together, return the information.
    We need to

    2- Cancel Patient appointments.
    initiate a for each loop for the appointments list, byPatientId =

    3 - Update Patient appointments.
    4 - View Patient appointments.
    5 - Remove Patient appointments.
     */
    private AppointmentDAO appointmentDAO;

    public AppointmentService(@Qualifier("appointmentrepo") AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }


    private void checkBookAppointmentProperties(Appointment appointment) {
        if(appointment.getAppointmentTime() == null) {
            throw new InvalidRequestException("Appointment time cannot be null");
        }
        if(appointment.getAppointmentDate() == null) {
            throw new InvalidRequestException("Appointment date cannot be null");
        }
        if(appointment.getDoctorId() <= 0) {
            throw new InvalidRequestException("Doctor ID cannot be less than or equal to 0");
        }
        if(appointment.getPatientNhsId() <= 0) {
            throw new InvalidRequestException("Patient ID cannot be less than or equal to 0");
        }
    }

    public int bookAppointment(Appointment appointment) {
        //check if all value inputs are correct
        checkBookAppointmentProperties(appointment);
//        if (doctorDAO.selectDoctorById(appointment.getDoctorId()) == null) {
//            throw new IllegalStateException("Invalid doctor ID");
//        }
//        if (patientService.findPatientById(appointment.getPatientNhsId()) == null) {
//            throw new IllegalStateException("Invalid patient ID");
//        }
        return appointmentDAO.bookAppointment(appointment);
    }


    public Appointment selectAppointmentById(Integer id) {
        if (id == null || id <= 0) {
            throw new AppointmentNotFoundException("Invalid appointment ID");
        }
        Appointment output = appointmentDAO.selectAppointmentById(id);
        if (output == null) {
            throw new AppointmentNotFoundException("Appointment with ID " + id + " not found");
        } else {
            return output;
        }
    }

    public List<Appointment> viewAllAppointments() {
        return appointmentDAO.viewAllAppointments();
    }

    public int deleteAppointmentById(Integer id) {
        //check if appointment ID exists, so check if null
        if (appointmentDAO.selectAppointmentById(id) == null){
            throw new AppointmentNotFoundException("Appointment with ID " + id + " not found");
        }
        // otherwise delete appointment
        return appointmentDAO.deleteAppointmentById(id);
    }

    public int updateAppointment (Integer id, Appointment update){
        //check if id exists
        selectAppointmentById(id);
        int output = appointmentDAO.updateAppointment(id, update);
        if (output != 1) {
            throw new IllegalStateException("Could not update appointment.");
        }
        return output;
    }





    public List<Appointment> getAppointmentByPatientBloodType(String bloodType) {

        List<Appointment> output = appointmentDAO.selectAppointmentByPatientBloodType(bloodType);
        if (output == null) {
            throw new AppointmentNotFoundException("Appointment with this bloodtype not found");
        }
        return output;

    }




    public List<AppointmentJoint> showAllAppointmentsWithNames() {
        List<AppointmentJoint> output = appointmentDAO.showAllAppointmentsWithNames();
        if (output == null) {
            throw new AppointmentNotFoundException("No appointments found");
            }
        return output;
    }


}







