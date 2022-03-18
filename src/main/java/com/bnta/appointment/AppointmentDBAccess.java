package com.bnta.appointment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/* What we want this service to do;
    1 - Add  appointments.
    Get Patient information, and Create a booking for them, initiate a for loop by patientId and if empty create an 'instance' to add Doctor, patient and, appointment room together, return the information.
    We need to

    2- Cancel Patient appointments.
    initiate a for each loop for the appointments list, byPatientId =

    3 - Update Patient appointments.
    4 - View Patient appointments.
    5 - Remove Patient appointments.
     */

@Repository("appointmentrepo")

public class AppointmentDBAccess implements AppointmentDAO{

    private JdbcTemplate jdbcTemplate;

    public AppointmentDBAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int bookAppointment(Appointment appointment) {
        String insertSql =
                """
                INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time)
                VALUES(?,?,?,?)
                """;

        int result = jdbcTemplate.update(
                insertSql,
                appointment.getPatientNhsId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate().toString(),
                appointment.getAppointmentTime().toString()
                );
        return result;
    }

    @Override
    public int deleteAppointmentById(Integer id) {
        String sql = """ 
                DELETE FROM appointments 
                WHERE appointment_id = ?
                """;
        int result = jdbcTemplate.update(
                sql,
                id

        );
        return result;
    }

    @Override
    public List<Appointment> viewAllAppointments() {
        String sql = """
                SELECT appointment_id, patient_id, doctor_id, appointment_date, appointment_time FROM appointments
                """;
        return jdbcTemplate.query(sql, new AppointmentRowMapper());
    }


    @Override
    public Appointment selectAppointmentById(Integer id) {
        String sql = """
                SELECT appointment_id, patient_id, doctor_id, appointment_date, appointment_time FROM appointments WHERE appointment_id = ?
                """;
        List<Appointment> appointments = jdbcTemplate.query(sql, new AppointmentRowMapper(), id);
        return appointments.stream().findFirst().orElse(null);

    }


    @Override
    public int updateAppointment(Integer id, Appointment update) {
        return jdbcTemplate.update(
                """
                        UPDATE appointments SET (patient_id, doctor_id, appointment_date, appointment_time) = (?, ?, ?, ?) WHERE appointment_id = ?
                        """,
                update.getPatientNhsId(),
                update.getDoctorId(),
                update.getAppointmentDate(),
                update.getAppointmentTime(),
                id

        );
    }

    @Override
    public List<Appointment> selectAppointmentByPatientBloodType(String bloodType) {
        String sql = """
        SELECT
            appointments.*
        FROM
            patients
        INNER JOIN appointments
            ON patients.id = appointments.patient_id
        WHERE patients.blood_type = ?
        """;
        List<Appointment> appointmentswithbloodtype = jdbcTemplate.query(sql, new AppointmentRowMapper(), bloodType);
        if (appointmentswithbloodtype.isEmpty()) {
            return null;
        } else {
            return appointmentswithbloodtype;
        }


    }

    @Override
    public List<AppointmentJoint> showAllAppointmentsWithNames(){
        String sql = """
                SELECT patients.patient_name,
                    doctors.doctor_name,
                    doctors.room_name,
                    appointments.appointment_date,
                    appointment_time
                FROM appointments
                        INNER JOIN patients
                            ON appointments.patient_id = patients.id
                        INNER JOIN doctors
                            ON appointments.doctor_id = doctors.id;
                """;
        List<AppointmentJoint> appointmentsOutput = jdbcTemplate.query(
                sql,
                new AppointmentRowMapperList());
        return appointmentsOutput;
    }

    //Extend appointment controller and patient controller
    //Join tables- e.g select all patients for one doctor
    //select all appointments for one patient
    //Add doctor controller
}





