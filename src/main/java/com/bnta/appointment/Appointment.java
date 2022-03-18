package com.bnta.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
// change localdatetime into 2 different properties so date + time separate
public class Appointment {
    private int appointmentId;
    private int patientNhsId;
    private int doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    public Appointment(int appointmentId, int patientNhsId, int doctorId, LocalDate appointmentDate, LocalTime appointmentTime) {
        this.appointmentId = appointmentId;
        this.patientNhsId = patientNhsId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientNhsId() {
        return patientNhsId;
    }

    public void setPatientNhsId(int patientNhsId) {
        this.patientNhsId = patientNhsId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientNhsId=" + patientNhsId +
                ", doctorId=" + doctorId +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId == that.appointmentId && patientNhsId == that.patientNhsId && doctorId == that.doctorId && appointmentDate.equals(that.appointmentDate) && appointmentTime.equals(that.appointmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, patientNhsId, doctorId, appointmentDate, appointmentTime);
    }
}
