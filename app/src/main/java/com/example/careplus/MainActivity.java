package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.careplus.prms.prms_dashboard;
import android.os.ConditionVariable;

import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);

        //add_test_patient();

    }


    /*load prms dashboard*/
    public void start_prms(View v){
        Intent prms_load = new Intent(this , prms_dashboard.class);
        startActivity(prms_load);
    }

    public void add_test_patient(){

        ContentValues patient = new ContentValues();
        patient.put(DatabaseTable.Patient.PATIENT_FIRST_NAME , "isuru 2");
        patient.put(DatabaseTable.Patient.PATIENT_LAST_NAME , "kavinda 2");
        patient.put(DatabaseTable.Patient.PATIENT_DOB , "18/02/1999");
        patient.put(DatabaseTable.Patient.PATIENT_REASON , "test reoson 2 ");
        patient.put(DatabaseTable.Patient.PATIENT_DATE_ADMITTED , "18/02/2020");
        patient.put(DatabaseTable.Patient.PATIENT_ADDITIONAL_INFO , "noln add 2");

        mydb.save(DatabaseTable.Patient.TABLE_NAME , patient);

    }

}