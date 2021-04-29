package com.example.careplus.prms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.careplus.R;

public class prms_dashboard extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prms_dashboard);

    }

    public void load_add_precription(View v){
        Intent load_add_presc = new Intent(this , Prms_add_prescription.class);
        startActivity(load_add_presc);
    }


}