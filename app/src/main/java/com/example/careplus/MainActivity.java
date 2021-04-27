package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.wms.Wms_create_schedule;
import com.example.careplus.wms.Wms_dashboard;
import com.example.careplus.wms.Wms_view_reports;

public class MainActivity extends AppCompatActivity {
    //Button btnWorkout;//Wms_dashboard a button object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wms_link btnWorkout button with xml
        //btnWorkout = findViewById(R.id.btnWorkout);

        //Wms_dashboard listener
        /*btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wms activity intents
                Intent intent = new Intent(getApplicationContext(),Wms_dashboard.class);
                startActivity(intent);
            }
        });*/
    }

    public void goToWmsDashboard(View view){
        Intent wms = new Intent(this,Wms_dashboard.class);
        startActivity(wms);
    }

}