package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.careplus.R;

public class Mms_viewMealPlan1 extends AppCompatActivity {

    //MMS_create button objects
    ImageButton btnCalender;
    Button btnMonday, btnTuesday, btnWednesday, btnThursday,btnFriday,btnSaturday,btnSunday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_view_meal_plan1);

        this.setTitle("Care plus | View meal plan");

        btnCalender = findViewById(R.id.btnCalender);
        btnMonday = findViewById(R.id.btnMonday);
        btnTuesday = findViewById(R.id.btnTuesday);
        btnWednesday = findViewById(R.id.btnWednesday);
        btnThursday = findViewById(R.id.btnThursday);
        btnFriday = findViewById(R.id.btnFriday);
        btnSaturday = findViewById(R.id.btnSaturday);
        btnSunday = findViewById(R.id.btnSunday);

        btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MMS_create an intent
                Intent i1 = new Intent(getApplicationContext(),Mms_viewMealPlan2.class);
                startActivity(i1);
            }
        });



    }
}