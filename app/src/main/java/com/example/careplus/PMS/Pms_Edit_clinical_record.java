package com.example.careplus.PMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.PMS.Pms_clinical_patient_list;
import com.example.careplus.PMS.clinicalModel;
import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;

public class Pms_Edit_clinical_record extends AppCompatActivity {

    EditText edit_clinical_bloodP, edit_clinical_sugar, edit_clinical_diagnosis;
    CheckBox edit_clinic_check_box;
    private DatabaseHelper databaseHelper;
    Context context;
    Button save_edits, cancel_edits;

    boolean everythingChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms__edit_clinical_record);

        context = this;

        databaseHelper = new DatabaseHelper(context);


        edit_clinical_bloodP = findViewById(R.id.pms_edit_clinical_txt_p_bp);
        edit_clinical_sugar = findViewById(R.id.pms_edit_clinical_txt_p_bgluc);
        edit_clinical_diagnosis = findViewById(R.id.pms_edit_clinical_txt_p_diag);

        edit_clinic_check_box = findViewById(R.id.pms_edit_clinical_checkbox);
        save_edits = findViewById(R.id.pms_edit_clinic_btn_save);

        final String receieved_clinical_id = getIntent().getStringExtra("record_id");

        clinicalModel recieved_clinical_data = databaseHelper.getOneRowfromBedHead(Integer.parseInt(receieved_clinical_id));

        edit_clinical_sugar.setText(String.valueOf(recieved_clinical_data.getBlood_glucose()));
        edit_clinical_bloodP.setText(String.valueOf(recieved_clinical_data.getBlood_pressure()));
        edit_clinical_diagnosis.setText(recieved_clinical_data.getDiagnosis());




            save_edits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    everythingChecked = checkAllEditText();

                    if(checkAllEditText()){

                        String updated_glucose = edit_clinical_sugar.getText().toString();
                        String updated_bloodP = edit_clinical_bloodP.getText().toString();
                        String updated_diagnosis = edit_clinical_diagnosis.getText().toString();

                        clinicalModel update_clinical = new clinicalModel(

                                Integer.parseInt(receieved_clinical_id),
                                updated_diagnosis,
                                Double.parseDouble(updated_bloodP),
                                Double.parseDouble(updated_glucose)

                        );

                        int Update_clinical_data = databaseHelper.update_clinical_data(update_clinical);




                        Toast.makeText(getApplicationContext(),Update_clinical_data + " Records Updated",Toast.LENGTH_SHORT).show();

                        Intent finished_clinical_update = new Intent(context, Pms_clinical_patient_list.class);
                        startActivity(finished_clinical_update);


                    }









                }
            });











    }



    private boolean checkAllEditText(){

        //edit_clinical_bloodP, edit_clinical_sugar, edit_clinical_diagnosis

        if(edit_clinical_bloodP.length() == 0){

            edit_clinical_bloodP.setError("Blood Pressure cannot be Empty");
            edit_clinical_bloodP.requestFocus();
            return false;
        }

        /*
        if(c_p_name.length() == 0){

            c_p_name.setError("Name cannot be Empty");
            return false;
        }

         */

        if(edit_clinical_sugar.length() == 0){

            edit_clinical_sugar.setError("Blood Glucose cannot be Empty");
            edit_clinical_sugar.requestFocus();
            return false;
        }

        if( edit_clinical_diagnosis.length() == 0){

            edit_clinical_diagnosis.setError("Diagnosis cannot be Empty");
            edit_clinical_diagnosis.requestFocus();
            return false;
        }


        if(!edit_clinic_check_box.isChecked()){

            edit_clinic_check_box.setError("Required");
            edit_clinic_check_box.requestFocus();
            return false;
        }



        return true;

    }











}