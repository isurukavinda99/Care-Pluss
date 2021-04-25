package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.R;

public class Mms_addNewPlan extends AppCompatActivity {

    //MMS_create button objects
    Button btnSelectPatient, btnSelectDate, btnBreakfast,btnLunch, btnDinner,btnSavePlan,btnCancelPlan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_add_new_plan);

        this.setTitle("Care plus | Add new meal plan");//MMS_set title

        //MMS_link button objects with xml
        btnSelectPatient=findViewById(R.id.btnSelectPatient);
        btnSelectDate=findViewById(R.id.btnSelectDate);
        btnBreakfast=findViewById(R.id.btnBreakfast);
        btnLunch=findViewById(R.id.btnLunch);
        btnDinner=findViewById(R.id.btnDinner);
        btnSavePlan=findViewById(R.id.btnSavePlan);
        btnCancelPlan=findViewById(R.id.btnCancelPlan);


        //MMS_create an event listener on btnBreakfast
        btnBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MMS_create an intent
                Intent i1= new Intent(getApplicationContext(),Mms_addNewMeal.class);
                startActivity(i1);
            }

        });


    }
}// end class