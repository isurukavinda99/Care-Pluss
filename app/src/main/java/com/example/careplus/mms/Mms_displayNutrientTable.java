package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.R;

public class Mms_displayNutrientTable extends AppCompatActivity {

    //MMS_create a button object
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_display_nutrient_table);

        this.setTitle("Care plus | Nutrients table");//MMS_set title on app

        //MMS_link button object with xml
        btnOk = findViewById(R.id.btnOk);

        //MMS_create an event listener
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MMS_create an intent
                Intent intent = new Intent(getApplicationContext(),Mms_measureNutrients.class);
                startActivity(intent);
            }
        });
    }
}