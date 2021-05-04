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

    public void load_view_prescription(View v){
        Intent load_view_prec = new Intent(this,Prms_view_prescription.class);
        startActivity(load_view_prec);
    }

    public void load_delete_prescription(View v){
        Intent load_delete_presc = new Intent(this , Prms_delete_prescription.class);
        startActivity(load_delete_presc);
    }

    public void load_update_prescription(View v){
        Intent load_udpate_presc = new Intent(this , Prms_update_current_prescreption.class);
        startActivity(load_udpate_presc);
    }

    public void load_reallocate_prescription(View v){
        Intent load_reallocate_presc = new Intent(this,Prms_reallocate_prescription.class);
        startActivity(load_reallocate_presc);
    }


}