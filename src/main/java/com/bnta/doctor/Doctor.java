package com.bnta.doctor;

import java.util.Objects;

public class Doctor {
     int doctorId;
     String doctorName;
    String roomName;

    public Doctor(int doctorId, String doctorName, String roomName) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.roomName = roomName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", roomName='" + roomName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return doctorId == doctor.doctorId && Objects.equals(doctorName, doctor.doctorName) && Objects.equals(roomName, doctor.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, doctorName, roomName);
    }
}
