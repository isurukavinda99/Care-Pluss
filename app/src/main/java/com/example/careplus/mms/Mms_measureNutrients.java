package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.R;

public class Mms_measureNutrients extends AppCompatActivity {

    //MMS_create button objects
    Button btnSelectFood, btnSelectServingSize, btnSelectQuantity, btnCalculate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_measure_nutrients);

        this.setTitle("Care plus | Measure nutrients");//MMS_set title on app

        //MMS_link button objects with xml
        btnSelectFood = findViewById(R.id.btnSelectFood);
        btnSelectServingSize = findViewById(R.id.btnSelectServingSize);
        btnSelectQuantity = findViewById(R.id.btnSelectQuantity);
        btnCalculate = findViewById(R.id.btnCalculate);

        //MMS_create an event listener on btnCalculate
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an intent
                Intent intent = new Intent(getApplicationContext(),Mms_displayNutrientTable.class);

                startActivity(intent);
            }
        });

    }// end of onCreate
}// end class