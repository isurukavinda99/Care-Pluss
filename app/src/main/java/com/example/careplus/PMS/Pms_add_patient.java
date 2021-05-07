package com.example.careplus.PMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class Pms_add_patient extends AppCompatActivity {

    //creating edit text objects
    EditText patient_first_name, patient_last_name, patient_dob, patient_bed_no, patient_guardian_name, patient_contact, patient_address, patient_reason_stay, patient_date_admitted, patient_additional;

    //creating button objects
    Button patient_save, patient_cancel;

    boolean everythingChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_add_patient);
        this.setTitle("Care Plus|Add Patient"); //setting title name

        //Edit texts
        patient_first_name = findViewById(R.id.add_patient_txt_first_name);                          //firstname
        patient_last_name = findViewById(R.id.add_patient_txt_last_name);                           //lastname
        patient_dob = findViewById(R.id.add_patient_txt_dob);                                       //date of birth
        patient_bed_no = findViewById(R.id.add_patient_txt_bed_no);                                 //bed no
        patient_guardian_name = findViewById(R.id.add_patient_txt_guardian_name);                   //guardian name
        patient_contact = findViewById(R.id.add_patient_txt_contact);                               //contact number
        patient_address = findViewById(R.id.add_patient_txt_address);                               //address
        patient_reason_stay = findViewById(R.id.add_patient_txt_reason_stay);                       //reason for stay
        patient_date_admitted = findViewById(R.id.add_patient_txt_date_admitted);                   //date admitted
        patient_additional = findViewById(R.id.add_patient_txt_additional_info);                    //additional information

        //buttons
        patient_save =findViewById(R.id.add_patient_btn_save);                                      //save button
        patient_cancel = findViewById(R.id.add_patient_btn_cancel);                                 //cancel button




        patient_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                everythingChecked = InputValidator();

                if(InputValidator()){

                    ContentValues add_patient = new ContentValues();

                    add_patient.put(DatabaseTable.Patient.PATIENT_FIRST_NAME, patient_first_name.getText().toString());
                    add_patient.put(DatabaseTable.Patient.PATIENT_LAST_NAME,patient_last_name.getText().toString());
                    add_patient.put(DatabaseTable.Patient.PATIENT_DOB, patient_dob.getText().toString());
                    add_patient.put(DatabaseTable.Patient.PATIENT_BED_NO,patient_bed_no.getText().toString());



                    add_patient.put(DatabaseTable.Patient.GUARDIAN_NAME, patient_guardian_name.getText().toString());
                    add_patient.put(DatabaseTable.Patient.GUARDIAN_CONTACT_NUMBER, patient_contact.getText().toString());
                    add_patient.put(DatabaseTable.Patient.GUARDIAN_ADDRESS, patient_address.getText().toString());
                    add_patient.put(DatabaseTable.Patient.PATIENT_REASON, patient_reason_stay.getText().toString());
                    add_patient.put(DatabaseTable.Patient.PATIENT_DATE_ADMITTED, patient_date_admitted.getText().toString());
                    add_patient.put(DatabaseTable.Patient.PATIENT_ADDITIONAL_INFO,patient_additional.getText().toString());

                    DatabaseHelper add_new_patient = new DatabaseHelper(getApplicationContext());

                    boolean result = add_new_patient.save(DatabaseTable.Patient.TABLE_NAME,add_patient);




                    if( result == false){

                        Toast.makeText(getApplicationContext(),"Data Not Added",Toast.LENGTH_SHORT).show();

                    }

                    else{

                        Toast.makeText(getApplicationContext(),"Data Added Successfully",Toast.LENGTH_SHORT).show();


                    }

                }





            }
        });



        patient_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back_to_home = new Intent(getApplicationContext(), Pms_home.class);
                startActivity(back_to_home);



            }
        });



    }

    private boolean InputValidator(){

        // patient_bed_no, patient_guardian_name, patient_contact, patient_address, patient_reason_stay, patient_date_admitted, patient_additional

        if(patient_first_name.length() == 0){
            patient_first_name.setError("First Name Cannot be Empty");
            patient_first_name.requestFocus();
            return false;
        }

        if(patient_last_name.length() == 0){
            patient_last_name.setError("Last Name Cannot be Empty");
            patient_last_name.requestFocus();
            return false;
        }
        if(patient_dob.length() == 0){
            patient_dob.setError("Date of Birth Cannot be Empty");
            patient_dob.requestFocus();
            return false;
        }
        if(patient_bed_no.length() == 0){
            patient_bed_no.setError("Bed No Cannot be Empty");
            patient_bed_no.requestFocus();
            return false;
        }
        if( patient_guardian_name.length() == 0){
            patient_guardian_name.setError("Guardian Name Cannot be Empty");
            patient_guardian_name.requestFocus();
            return false;
        }
        if(patient_contact.length() == 0){
            patient_contact.setError("Contact Number Cannot be Empty");
            patient_contact.requestFocus();
            return false;
        }


        if(patient_address.length() == 0){
            patient_address.setError("Address Cannot be Empty");
            patient_address.requestFocus();
            return false;
        }
        if(patient_reason_stay.length() == 0){
            patient_reason_stay.setError("Reason Cannot be Empty");
            patient_reason_stay.requestFocus();
            return false;
        }
        if(patient_date_admitted.length() == 0){
            patient_date_admitted.setError("Admitted Date Cannot be Empty");
            patient_date_admitted.requestFocus();
            return false;
        }
        if(patient_additional.length() == 0){
            patient_additional.setError("Additional Data Cannot be Empty");
            patient_additional.requestFocus();
            return false;
        }







        return true;

    }



}