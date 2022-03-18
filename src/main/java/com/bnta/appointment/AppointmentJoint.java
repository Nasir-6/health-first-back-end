package com.bnta.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class AppointmentJoint {
    private String patientName;
    private String doctorName;
    private String roomName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    public AppointmentJoint(String patientName, String doctorName, String roomName, LocalDate appointmentDate, LocalTime appointmentTime) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.roomName = roomName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentJoint that = (AppointmentJoint) o;
        return Objects.equals(patientName, that.patientName) && Objects.equals(doctorName, that.doctorName) && Objects.equals(roomName, that.roomName) && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(appointmentTime, that.appointmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientName, doctorName, roomName, appointmentDate, appointmentTime);
    }

    @Override
    public String toString() {
        return "AppointmentJoint{" +
                "patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
