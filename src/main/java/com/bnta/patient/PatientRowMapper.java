package com.bnta.patient;

import com.bnta.patient.Patient;
import com.bnta.patient.BloodType;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Patient(
                rs.getInt("id"),
                rs.getString("patient_name"),
                rs.getString("phone_number"),
                rs.getString("email_address"),
                BloodType.valueOf(rs.getString("blood_type"))
        );
    }
}
