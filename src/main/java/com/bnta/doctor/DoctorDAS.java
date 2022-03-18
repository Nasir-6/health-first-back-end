package com.bnta.doctor;

import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("doctorrepo")
public class DoctorDAS implements DoctorDAO {

    private JdbcTemplate jdbcTemplate;

    public DoctorDAS(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Doctor> getAllDoctors() {
    String sql = """
                SELECT id, doctor_name, room_name FROM doctors
                """;
        return jdbcTemplate.query(sql, new DoctorRowMapper());
    }

    @Override
    public int addDoctor(Doctor doctor) throws NullPointerException {
        try {
            String insertSql = """
                INSERT INTO doctors(doctor_name, room_name)
                VALUES(?, ?)
                """;
            int result = jdbcTemplate.update(
                    insertSql,
                    doctor.getDoctorName(),
                    doctor.getRoomName()
            );
            System.out.println(result);
            return result;

        } catch(NullPointerException e)  {
            throw new NullPointerException("Invalid doctor information entered");
        }

    }

    @Override
    public int updateDoctorById(Integer id, Doctor update) {
        return jdbcTemplate.update(
                """
                        UPDATE doctors
                        SET (doctor_name, room_name) = (?, ?)
                        WHERE id = ?
                        """,

                update.getDoctorName(),
                update.getRoomName(),
                id
        );
    }

    @Override
    public Doctor selectDoctorById(Integer id) {
        String sql = """
                SELECT id, doctor_name, room_name FROM doctors WHERE id = ?
                """;
        List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorRowMapper(), id);
        return doctors.stream().findFirst().orElse(null);
    }

    @Override
    public int deleteDoctorById(Integer id) {
        String sql =
                        """
                        DELETE FROM doctors
                         WHERE id = ?
                        """;

                int result = jdbcTemplate.update(
                        sql,
                        id
                );

        return result;

    }

    @Override
    public int addPresetDoctors() {
        String insertSql = """
                INSERT INTO doctors (doctor_name, room_name)
                VALUES('Dr Franks','Room 3'),('Dr Maryland','Room 2'),('Dr Darwin','Room 1')
                """;
        int result = jdbcTemplate.update(
                insertSql);

        System.out.println(result);
        return result;

    }

    @Override
    public int deleteAllDoctors() {
        return jdbcTemplate.update(
                """
                        DELETE FROM doctors
                        """);


    }
}
