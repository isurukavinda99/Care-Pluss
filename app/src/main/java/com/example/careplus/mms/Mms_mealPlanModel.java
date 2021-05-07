package com.example.careplus.mms;

public class Mms_mealPlanModel {

    private int id;
    private String planName, day, patientType, breakfast, lunch, dinner;

    public Mms_mealPlanModel(){

    }

    //MMS_constructor with all fields
    public Mms_mealPlanModel(int id, String planName, String day, String patientType, String breakfast, String lunch, String dinner) {
        this.id = id;
        this.planName = planName;
        this.day = day;
        this.patientType = patientType;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    //MMS_constructor without id
    public Mms_mealPlanModel(String planName, String day, String patientType, String breakfast, String lunch, String dinner) {
        this.planName = planName;
        this.day = day;
        this.patientType = patientType;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }

    public String getDay() {
        return day;
    }

    public String getPatientType() {
        return patientType;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public String getDinner() {
        return dinner;
    }

    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}

