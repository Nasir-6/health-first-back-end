package com.bnta.appointment;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRowMapperList implements RowMapper<AppointmentJoint> {

    @Override
    public AppointmentJoint mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AppointmentJoint(
                rs.getString("patient_name"),
                rs.getString("doctor_name"),
                rs.getString("room_name"),
                LocalDate.parse(rs.getString("appointment_date")),
                LocalTime.parse(rs.getString("appointment_time"))
        );
    }
}