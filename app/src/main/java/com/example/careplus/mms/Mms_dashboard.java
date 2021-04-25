package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.careplus.R;

public class Mms_dashboard extends AppCompatActivity {

    //MMS_create button objects
    ImageButton btnAddNew, btnViewPlan, btnUpdatePlan, btnMeasureNutrients;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_dashboard);

        this.setTitle("Care plus | Dashboard");

        //MMS_link button objects with xml
        btnAddNew=findViewById(R.id.btnAddNewPlan);
        btnViewPlan=findViewById(R.id.btnViewPlan);
        btnUpdatePlan=findViewById(R.id.btnUpdatePlan);
        btnMeasureNutrients=findViewById(R.id.btnMeasureNutrients);

        //MMS_create an event listener on Add new meal plan button
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),Mms_addNewPlan.class);
                startActivity(i1);
            }
        });

        //MMS_create an event listener on View meal plan button
        btnViewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), Mms_viewMealPlan1.class);
                startActivity(i2);
            }
        });

        //MMS_create an event listener on Update meal plan button
        btnUpdatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(getApplicationContext(),Mms_updateMealPlan.class);
                startActivity(i3);
            }
        });

    //MMS_create an event listener on Measure nutrients button
        btnMeasureNutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(getApplicationContext(),Mms_measureNutrients.class);
                startActivity(i4);
            }
        });

    }
}