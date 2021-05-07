package com.example.careplus.PMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.careplus.R;

public class Pms_home extends AppCompatActivity {

    //creating bmi object

    ImageButton button_bmi_calculator,button_add_patient,button_add_clinical_record,button_patient_List, clinical_patient_img_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_home);

        this.setTitle("Care Plus|Patient Management Home");

        //matching id to bmi_calculator object

        button_bmi_calculator = findViewById(R.id.img_btn_bmi_calc);

        button_add_patient = findViewById(R.id.img_btn_add_patient);

        button_add_clinical_record = findViewById(R.id.img_btn_add_clinical_record);

        button_patient_List = findViewById(R.id.img_btn_view_patient_list);

        clinical_patient_img_btn = findViewById(R.id.pms_home_to_clinical_patient);

    //creating onclick method

        button_bmi_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creating explicit intent to navigate to bmi calculator

                Intent bmi = new Intent(getApplicationContext(), Pms_bmi_calculator.class);
                startActivity(bmi);


            }
        });

        //setting onclick for add patient button
        button_add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting explixit intent

                Intent add_patient = new Intent(getApplicationContext(), Pms_add_patient.class);
                startActivity(add_patient);


            }
        });


        button_add_clinical_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent add_clinical_record = new Intent(getApplicationContext(), Pms_add_clinical_record.class);
                startActivity(add_clinical_record);




            }
        });

        button_patient_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent patient_list = new Intent(getApplicationContext(), Pms_patient_list.class);
                startActivity(patient_list);


            }



        });

        clinical_patient_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_cp_list = new Intent(getApplicationContext(), Pms_clinical_patient_list.class);
                startActivity(to_cp_list);
            }
        });





    }
}