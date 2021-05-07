package com.example.careplus.PMS;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

public class Pms_clinical_patient_list extends AppCompatActivity {



    private ListView clinical_patient_listview;
    Context context;
    private DatabaseHelper clinical_patient_dbHelp;
    private List<patientModel> clinic_patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_clinical_patient_list);

        this.setTitle("Care Plus|Admitted Patient");

        clinical_patient_dbHelp = new DatabaseHelper(this);
        clinical_patient_listview = findViewById(R.id.ListView_clinical_patient_list);
        context = this;

        clinic_patient = new ArrayList<>();

        clinic_patient = clinical_patient_dbHelp.displayClinicalPatientInfo(DatabaseTable.Patient.TABLE_NAME);

        Clinical_patient_list_adapter clinic_p_adapter = new Clinical_patient_list_adapter(context,R.layout.pms_single_clinical_patient,clinic_patient);

        clinical_patient_listview.setAdapter(clinic_p_adapter);


        //on item clic listner for each row in list view
        clinical_patient_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                patientModel clinical_patient_model = clinic_patient.get(position);


                AlertDialog.Builder alert_box_builder = new AlertDialog.Builder(context);
                alert_box_builder.setTitle(clinical_patient_model.getP_fname() + clinical_patient_model.getP_lname()); //alert Title
                alert_box_builder.setMessage("Confidential Data! Do you want to Proceed"); //alert message

                //forword to clinical list
                alert_box_builder.setPositiveButton("Yes!Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent forword_to_clinical_list = new Intent(getApplicationContext(), Pms_clinical_record_list.class);
                        forword_to_clinical_list.putExtra("Patient_id",String.valueOf(clinical_patient_model.getP_id()));
                        startActivity(forword_to_clinical_list);

                    }
                });

                //end forword to clinical list


                alert_box_builder.show();





            }
        }); //on item click view listview ends






    }





}