package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Pms_patient_list_detailed extends AppCompatActivity {

    ImageButton clinical_record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_patient_list_detailed);

        this.setTitle("Care Plus|Austin Siva");


        clinical_record = findViewById(R.id.img_btn_full_clinical_records);


        clinical_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent full_clinical = new Intent(getApplicationContext(),Pms_clinic_record_detailed.class);
                startActivity(full_clinical);

            }
        });

    }
}