package com.bnta.patient;

import java.util.Objects;

public class Patient {
    int patientNhsId;
    String patientName;
    String patientPhoneNumber;
    String patientEmailAddress;
    BloodType bloodType;

    public Patient(int patientNhsId, String patientName, String patientPhoneNumber, String patientEmailAddress, BloodType bloodType) {
        this.patientNhsId = patientNhsId;
        this.patientName = patientName;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientEmailAddress = patientEmailAddress;
        this.bloodType = bloodType;
    }

    //should we also have a more empty patient constructor, in case we have a patient without all the fields?
    // Ie missing blood type - which we don't need for basic functionality

    public int getPatientNhsId() {
        return patientNhsId;
    }

    public void setPatientNhsId(int patientNhsId) {
        this.patientNhsId = patientNhsId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public String getPatientEmailAddress() {
        return patientEmailAddress;
    }

    public void setPatientEmailAddress(String patientEmailAddress) {
        this.patientEmailAddress = patientEmailAddress;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientNhsId=" + patientNhsId +
                ", patientName='" + patientName + '\'' +
                ", patientPhoneNumber='" + patientPhoneNumber + '\'' +
                ", patientEmailAddress='" + patientEmailAddress + '\'' +
                ", bloodType=" + bloodType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patientNhsId == patient.patientNhsId && Objects.equals(patientName, patient.patientName) && Objects.equals(patientPhoneNumber, patient.patientPhoneNumber) && Objects.equals(patientEmailAddress, patient.patientEmailAddress) && bloodType == patient.bloodType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientNhsId, patientName, patientPhoneNumber, patientEmailAddress, bloodType);
    }
}
