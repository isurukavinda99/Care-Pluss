package com.example.careplus.PMS;

public class clinicalModel {

    private int clinical_patient_id, record_id;
    private long record_date;
    private String diagnosis;
    private double blood_pressure, blood_glucose;

    public clinicalModel() {
    }

    public clinicalModel(int record_id, long record_date, String diagnosis, double blood_pressure, double blood_glucose) {
        this.record_id = record_id;
        this.record_date = record_date;
        this.diagnosis = diagnosis;
        this.blood_pressure = blood_pressure;
        this.blood_glucose = blood_glucose;
    }

    public clinicalModel(int clinical_patient_id, int record_id, long record_date, String diagnosis, double blood_pressure, double blood_glucose) {
        this.clinical_patient_id = clinical_patient_id;
        this.record_id = record_id;
        this.record_date = record_date;
        this.diagnosis = diagnosis;
        this.blood_pressure = blood_pressure;
        this.blood_glucose = blood_glucose;
    }

    public clinicalModel(int record_id, String diagnosis, double blood_pressure, double blood_glucose) {
        this.record_id = record_id;
        this.diagnosis = diagnosis;
        this.blood_pressure = blood_pressure;
        this.blood_glucose = blood_glucose;
    }

    public int getClinical_patient_id() {
        return clinical_patient_id;
    }

    public void setClinical_patient_id(int clinical_patient_id) {
        this.clinical_patient_id = clinical_patient_id;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public long getRecord_date() {
        return record_date;
    }

    public void setRecord_date(long record_date) {
        this.record_date = record_date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public double getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(double blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public double getBlood_glucose() {
        return blood_glucose;
    }

    public void setBlood_glucose(double blood_glucose) {
        this.blood_glucose = blood_glucose;
    }
}
