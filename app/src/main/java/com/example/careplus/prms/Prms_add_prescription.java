package com.example.careplus.prms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.careplus.R;

import java.sql.SQLOutput;

public class Prms_add_prescription extends AppCompatActivity {

    RadioButton prms_morning_radio , prms_day_radio , prms_night_radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prms_add_prescription);

        prms_morning_radio = findViewById(R.id.prms_morning_radio);
        prms_day_radio = findViewById(R.id.prms_day_radio);
        prms_night_radio = findViewById(R.id.prms_night_radio);
    }

    public void reselect_radio(View v){

        if(v.getId() == R.id.prms_morning_radio){
            prms_morning_radio.setChecked(true);
            prms_day_radio.setChecked(false);
            prms_night_radio.setChecked(false);
        }else if(v.getId() == R.id.prms_day_radio){
            prms_morning_radio.setChecked(false);
            prms_day_radio.setChecked(true);
            prms_night_radio.setChecked(false);
        }else if (v.getId() == R.id.prms_night_radio){
            prms_morning_radio.setChecked(false);
            prms_day_radio.setChecked(false);
            prms_night_radio.setChecked(true);
        }


    }
}