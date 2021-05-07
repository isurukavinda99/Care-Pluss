package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.mms.Mms_dashboard;
import android.view.View;
import android.widget.Button;

import com.example.careplus.wms.Wms_create_schedule;
import com.example.careplus.wms.Wms_dashboard;
import com.example.careplus.wms.Wms_view_reports;
import android.os.ConditionVariable;
import android.widget.SearchView;

import com.example.careplus.PMS.Pms_home;
import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class MainActivity extends AppCompatActivity {

    Button btnMeal;//MMS_create a button object

    //creating button_patient object
    Button button_patient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //matching id to button_patient object
        button_patient = findViewById(R.id.btn_patient);

        //MMS_get reference
        btnMeal = findViewById(R.id.btnMeal);


        button_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent patient_home = new Intent(getApplicationContext(), Pms_home.class);
                startActivity(patient_home);

            }
        });

        //MMS_create an event listner
        btnMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MMS_create an intent
                Intent mms_intent = new Intent(getApplicationContext(),Mms_dashboard.class);
                startActivity(mms_intent);
            }
        });

        DatabaseHelper mydb = new DatabaseHelper(this);

        /*Makesure remove this line when merging */
        Intent login = new Intent(this , Login_main.class);
        //startActivity(login);
    }

    public void goToWmsDashboard(View view){
        Intent wms = new Intent(this,Wms_dashboard.class);
        startActivity(wms);
    }

}