package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.careplus.prms.prms_dashboard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /*load prms dashboard*/
    public void start_prms(View v){
        Intent prms_load = new Intent(this , prms_dashboard.class);
        startActivity(prms_load);
    }

}