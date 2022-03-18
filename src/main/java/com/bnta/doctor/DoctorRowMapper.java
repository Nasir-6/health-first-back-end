package com.bnta.doctor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

    public class DoctorRowMapper implements RowMapper<Doctor> {

    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Doctor(
                rs.getInt("id"),
                rs.getString("doctor_name"),
                rs.getString("room_name")
        );
    }

}