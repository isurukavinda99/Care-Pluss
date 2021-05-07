package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.PMS.Pms_home;
import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    //creating button_patient object
    Button button_patient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //matching id to button_patient object
        button_patient = findViewById(R.id.btn_patient);


        button_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent patient_home = new Intent(getApplicationContext(), Pms_home.class);
                startActivity(patient_home);

            }
        });

        DatabaseHelper mydb = new DatabaseHelper(this);
    }
}