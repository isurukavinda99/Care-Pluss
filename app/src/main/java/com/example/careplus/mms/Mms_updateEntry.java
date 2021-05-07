package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;

public class Mms_updateEntry extends AppCompatActivity {

    //MMS_initialize objects
    EditText updatePlanName, updatePatientType, updateDay,updateBreakfast,updateLunch,updateDinner;
    Button btnUpdatePlan, btnCancelUpdates;

    private DatabaseHelper mms_dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_update_entry);

        this.setTitle("Care plus | Update entry");


        DatabaseHelper mms_dbHelper = new DatabaseHelper(getApplicationContext());

        //MMS_get references
        updatePlanName = findViewById(R.id.updatePlanName);
        updatePatientType = findViewById(R.id.updatePatientType);
        updateDay = findViewById(R.id.updateDay);
        updateBreakfast = findViewById(R.id.updateBreakfast);
        updateLunch = findViewById(R.id.updateLunch);
        updateDinner = findViewById(R.id.updateDinner);
        btnUpdatePlan = findViewById(R.id.btnUpdatePlan);
        btnCancelUpdates = findViewById(R.id.btnCancelUpdates);




        final String id = getIntent().getStringExtra("id");

        Mms_mealPlanModel mealPlanModel = mms_dbHelper.getMealPlan(Integer.parseInt(id));

        //MMS_set values
        updatePlanName.setText(mealPlanModel.getPlanName());
        updatePatientType.setText(mealPlanModel.getPatientType());
        updateDay.setText(mealPlanModel.getDay());
        updateBreakfast.setText(mealPlanModel.getBreakfast());
        updateLunch.setText(mealPlanModel.getLunch());
        updateDinner.setText(mealPlanModel.getDinner());

        //MMS_ set event listener on btnUpdatePlan
        btnUpdatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String planName = updatePlanName.getText().toString();
                String patientType = updatePatientType.getText().toString();
                String day = updateDay.getText().toString();
                String breakfast = updateBreakfast.getText().toString();
                String lunch = updateLunch.getText().toString();
                String dinner = updateDinner.getText().toString();

                Mms_mealPlanModel mealPlan = new Mms_mealPlanModel(Integer.parseInt(id),planName,patientType,day,breakfast,lunch,dinner);

                int status = mms_dbHelper.updateMealPlan(mealPlan);

                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Mms_viewMealPlans.class));


            }
        });

        btnCancelUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Mms_dashboard.class);
                startActivity(intent);
            }
        });

    }//end OnCreate
}