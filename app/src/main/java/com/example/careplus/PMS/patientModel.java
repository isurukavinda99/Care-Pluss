package com.example.careplus.PMS;

public class patientModel {

    private int p_id;
    private String p_fname, p_lname, p_bed_no, p_additional, p_guardian, p_contact,p_address,p_reason;

    private String p_dob, p_date_admitted;



    //default constructor
    public patientModel(){

    }

    //parameterized constructor with id
    public patientModel(int p_id, String p_fname, String p_lname, String p_bed_no, String p_additional, String p_guardian, String p_contact, String p_address, String p_reason, String p_dob, String p_date_admitted) {
        this.p_id = p_id;
        this.p_fname = p_fname;
        this.p_lname = p_lname;
        this.p_bed_no = p_bed_no;
        this.p_additional = p_additional;
        this.p_guardian = p_guardian;
        this.p_contact = p_contact;
        this.p_address = p_address;
        this.p_reason = p_reason;
        this.p_dob = p_dob;
        this.p_date_admitted = p_date_admitted;
    }

    //parameterized constructor without id


    public patientModel(String p_fname, String p_lname, String p_bed_no, String p_additional, String p_guardian, String p_contact, String p_address, String p_reason, String p_dob, String p_date_admitted) {
        this.p_fname = p_fname;
        this.p_lname = p_lname;
        this.p_bed_no = p_bed_no;
        this.p_additional = p_additional;
        this.p_guardian = p_guardian;
        this.p_contact = p_contact;
        this.p_address = p_address;
        this.p_reason = p_reason;
        this.p_dob = p_dob;
        this.p_date_admitted = p_date_admitted;
    }


    //getters and setters

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_fname() {
        return p_fname;
    }

    public void setP_fname(String p_fname) {
        this.p_fname = p_fname;
    }

    public String getP_lname() {
        return p_lname;
    }

    public void setP_lname(String p_lname) {
        this.p_lname = p_lname;
    }

    public String getP_bed_no() {
        return p_bed_no;
    }

    public void setP_bed_no(String p_bed_no) {
        this.p_bed_no = p_bed_no;
    }

    public String getP_additional() {
        return p_additional;
    }

    public void setP_additional(String p_additional) {
        this.p_additional = p_additional;
    }

    public String getP_guardian() {
        return p_guardian;
    }

    public void setP_guardian(String p_guardian) {
        this.p_guardian = p_guardian;
    }

    public String getP_contact() {
        return p_contact;
    }

    public void setP_contact(String p_contact) {
        this.p_contact = p_contact;
    }

    public String getP_address() {
        return p_address;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public String getP_reason() {
        return p_reason;
    }

    public void setP_reason(String p_reason) {
        this.p_reason = p_reason;
    }

    public String getP_dob() {
        return p_dob;
    }

    public void setP_dob(String p_dob) {
        this.p_dob = p_dob;
    }

    public String getP_date_admitted() {
        return p_date_admitted;
    }

    public void setP_date_admitted(String p_date_admitted) {
        this.p_date_admitted = p_date_admitted;
    }
}


