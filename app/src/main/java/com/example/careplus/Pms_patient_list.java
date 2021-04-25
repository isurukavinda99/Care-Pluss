package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pms_patient_list extends AppCompatActivity {

    Button full_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_patient_list);
        this.setTitle("Care Plus|Patient List");


        full_list = findViewById(R.id.btn_dmy_1);


        full_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent deailed_view = new Intent(getApplicationContext(),Pms_patient_list_detailed.class);
                startActivity(deailed_view);


            }
        });



    }
}