package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.careplus.R;

public class Mms_updateMealPlan extends AppCompatActivity {

    ImageButton imgBtnEdit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_update_meal_plan);

        this.setTitle("Care plus | Update meal plan");

        imgBtnEdit1 = findViewById(R.id.imgBtnEdit1);

        imgBtnEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit1 = new Intent(getApplicationContext(),Mms_updateEntry.class);
                startActivity(intentEdit1);
            }
        });
    }
}