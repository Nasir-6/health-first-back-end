package com.bnta.appointment;

import java.util.List;

public interface AppointmentDAO {
    int bookAppointment(Appointment appointment);
    int deleteAppointmentById(Integer id);
    int updateAppointment(Integer id, Appointment update);
    List<Appointment> viewAllAppointments();
    Appointment selectAppointmentById(Integer id);
    List<Appointment> selectAppointmentByPatientBloodType(String bloodType);
    List<AppointmentJoint> showAllAppointmentsWithNames();
}
