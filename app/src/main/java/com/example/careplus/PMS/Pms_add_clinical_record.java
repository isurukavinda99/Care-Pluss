package com.example.careplus.PMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class Pms_add_clinical_record extends AppCompatActivity {

    EditText c_p_id, c_p_name,c_p_date,c_p_pressure,c_p_glucose,c_p_diagnosis,c_p_others;
    Button c_p_save, c_p_cancel;
    CheckBox clinical_check;

    //DatabaseHelper clinical_databaseHelper;

    //to check all the edit texts are filled
    boolean everythingChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_add_clinical_record);
        this.setTitle("Care Plus|Add Clinical Record");

        c_p_id = findViewById(R.id.pms_add_clinical_txt_p_id);
        //c_p_name = findViewById(R.id.pms_add_clinical_txt_p_name);
        c_p_date = findViewById(R.id.pms_add_clinical_txt_p_date);
        c_p_pressure = findViewById(R.id.pms_add_clinical_txt_p_bp);
        c_p_glucose = findViewById(R.id.pms_add_clinical_txt_p_bgluc);
        c_p_diagnosis = findViewById(R.id.pms_add_clinical_txt_p_diag);
        //c_p_others = findViewById(R.id.pms_add_clinical_txt_p_other_details);

        c_p_save = findViewById(R.id.pms_add_clinic_btn_save);
        c_p_cancel = findViewById(R.id.pms_add_clinic_btn_cancel);

        clinical_check = findViewById(R.id.pms_add_clinical_checkbox);

        //save button
        c_p_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                everythingChecked = checkAllEditText();

                if(checkAllEditText()){

                    ContentValues clinical_data = new ContentValues();

                    clinical_data.put(DatabaseTable.BeaHeadCard.PATIENT_ID_FK,c_p_id.getText().toString());
                    clinical_data.put(DatabaseTable.BeaHeadCard.CARD_DATE, c_p_date.getText().toString());
                    clinical_data.put(DatabaseTable.BeaHeadCard.CARD_BLOOD_PRESSURE,c_p_pressure.getText().toString());
                    clinical_data.put(DatabaseTable.BeaHeadCard.CARD_SUGAR_LEVEL, c_p_glucose.getText().toString());
                    clinical_data.put(DatabaseTable.BeaHeadCard.CARD_DIAGNOSIS, c_p_diagnosis.getText().toString());

                    DatabaseHelper clinical = new DatabaseHelper(getApplicationContext());

                    boolean result = clinical.save(DatabaseTable.BeaHeadCard.TABLE_NAME,clinical_data);

                    if( result == false){

                        Toast.makeText(getApplicationContext(),"Data Not Added",Toast.LENGTH_SHORT).show();

                    }

                    else{

                        Toast.makeText(getApplicationContext(),"Data Added Successfully",Toast.LENGTH_SHORT).show();


                    }







                    Intent i = new Intent(getApplicationContext(), Pms_clinical_patient_list.class);
                    startActivity(i);


                }









            }
        });





    }






    //test cases
    private boolean checkAllEditText(){

        if(c_p_id.length() == 0){

            c_p_id.setError("Id cannot be Empty");
            c_p_id.requestFocus();
            return false;
        }

        /*
        if(c_p_name.length() == 0){

            c_p_name.setError("Name cannot be Empty");
            return false;
        }

         */

        if(c_p_date.length() == 0){

            c_p_date.setError("Date cannot be Empty");
            c_p_id.requestFocus();
            return false;
        }

        if(c_p_glucose.length() == 0){

            c_p_glucose.setError("Glucose Level cannot be Empty");
            c_p_id.requestFocus();
            return false;
        }

        if(c_p_pressure.length() == 0){

            c_p_pressure.setError("BP cannot be Empty");
            c_p_id.requestFocus();
            return false;
        }

        if(c_p_diagnosis.length() == 0){

            c_p_diagnosis.setError("Diagnosis cannot be Empty");
            c_p_id.requestFocus();
            return false;
        }

        if(!clinical_check.isChecked()){

            clinical_check.setError("Diagnosis cannot be Empty");
            c_p_id.requestFocus();
            return false;
        }

        /*
        if(c_p_others.length() == 0){

            c_p_others.setError(" cannot be Empty");
            return false;
        }

        */


        return true;

    }



}