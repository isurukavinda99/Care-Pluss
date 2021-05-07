package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.careplus.R;

public class Mms_dashboard extends AppCompatActivity {

    //MMS_create button objects
    ImageButton btnAddNewPlan, btnViewPlan, btnUpdatePlan, btnMeasureNutrients;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_dashboard);

        this.setTitle("Care plus | Dashboard");//MMS_set title on app

        //MMS_get references
        btnAddNewPlan=findViewById(R.id.btnAddNewPlan);
        btnViewPlan=findViewById(R.id.btnViewPlan);
        btnMeasureNutrients=findViewById(R.id.btnMeasureNutrients);


        //MMS_create an event listener on Add new meal plan button
        btnAddNewPlan.setOnClickListener(new View.OnClickListener() {
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
                Intent i2 = new Intent(getApplicationContext(), Mms_viewMealPlans.class);
                startActivity(i2);
            }
        });



    //MMS_create an event listener on Measure nutrients button
        btnMeasureNutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(getApplicationContext(), Mms_supplementManager.class);
                startActivity(i4);
            }
        });

    }//end onCreate

}