package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.wms.Wms_create_schedule;
import com.example.careplus.wms.Wms_dashboard;
import com.example.careplus.wms.Wms_view_reports;
import android.os.ConditionVariable;
import android.widget.SearchView;

import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class MainActivity extends AppCompatActivity {

    SearchView mySearchView;

    DatabaseHelper db;
    //Button btnWorkout;//Wms_dashboard a button object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create search bar here
       /* mySearchView = (SearchView)findViewById(R.id.searchView1);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        }); */


        DatabaseHelper db = new DatabaseHelper(this);

        //input patient table data
        ContentValues patient = new ContentValues();
        patient.put(DatabaseTable.Patient.PATIENT_FIRST_NAME, "Yasmini");
        patient.put(DatabaseTable.Patient.PATIENT_LAST_NAME, "Kaveetha");
        patient.put(DatabaseTable.Patient.PATIENT_DOB, "01/05/1999");
        patient.put(DatabaseTable.Patient.PATIENT_REASON, "Skin problem");
        patient.put(DatabaseTable.Patient.PATIENT_DATE_ADMITTED, "12/03/2021");
        patient.put(DatabaseTable.Patient.PATIENT_ADDITIONAL_INFO, "not null");

        //db.save(DatabaseTable.Patient.TABLE_NAME, patient);


    }

    public void goToWmsDashboard(View view){
        Intent wms = new Intent(this,Wms_dashboard.class);
        startActivity(wms);
    }

}