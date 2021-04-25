package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.R;

import static android.content.Intent.*;

public class Mms_addNewMeal extends AppCompatActivity {

    //Button btnSaveMeal,btnCancelMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_add_new_meal);

        this.setTitle("Care plus | Add new meal");//MMS_set title on app


/*
        //MMS_create an event listener on btnSave
        btnSaveMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MMS_create an intent
                Intent intentSave = new Intent(getApplicationContext(),Mms_addNewPlan.class);

                startActivity(intentSave);
            }

        });

        //MMS_create an event listener on btnCancel
        btnCancelMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MMS_create an intent
                Intent intentCancel = new Intent(getApplicationContext(),Mms_addNewPlan.class);

                startActivity(intentCancel);
            }

        });
*/


    }
}