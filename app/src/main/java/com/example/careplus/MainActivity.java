package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.careplus.mms.Mms_dashboard;
import android.os.ConditionVariable;

import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class MainActivity extends AppCompatActivity {

    Button btnMeal;//MMS_create a button object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper mydb = new DatabaseHelper(this);

        //MMS_link btnMeal button with xml
        btnMeal = findViewById(R.id.btnMeal);

        //MMS_create an event listner
        btnMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MMS_create an intent
                Intent mms_intent = new Intent(getApplicationContext(),Mms_dashboard.class);
                startActivity(mms_intent);
            }
        });
    }
}