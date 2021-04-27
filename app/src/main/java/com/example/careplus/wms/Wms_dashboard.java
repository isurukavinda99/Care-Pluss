package com.example.careplus.wms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.R;

public class Wms_dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wms_dashboard);
/*
        //wms_link btnWorkout button with xml
        btnCreateWorkoutSchedule = findViewById(R.id.btnCreateWorkoutSchedule);
        btnViewWorkoutPlans = findViewById(R.id.btnViewWorkoutPlan);
        btnViewPatientMonthlyReport = findViewById(R.id.ViewPatientMonthlyReport);

        //Wms_create_schedule listener
        btnCreateWorkoutSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wms activity intents
                Intent intent = new Intent(getApplicationContext(),Wms_create_schedule.class);
                startActivity(intent);
            }
        });
        //Wms_view_plan listener
        btnViewWorkoutPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wms activity intents
                Intent intent = new Intent(getApplicationContext(),Wms_view_plans.class);
                startActivity(intent);
            }
        });
        //Wms_view_plan listener
        btnViewPatientMonthlyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wms activity intents
                Intent intent = new Intent(getApplicationContext(),Wms_view_reports.class);
                startActivity(intent);
            }
        });
        */
    }

    public void goToWmsCreateWorkout(View view){
        Intent creteWork = new Intent(this , Wms_create_schedule.class);
        startActivity(creteWork);
    }
    public void goToWmsViewWorkoutPlan(View view){
        Intent createWork = new Intent(this , Wms_view_plans.class);
        startActivity(createWork);
    }
    public void goToWmsViewWorkoutReport(View view){
        Intent createWork = new Intent(this , Wms_view_reports.class);
        startActivity(createWork);
    }
}