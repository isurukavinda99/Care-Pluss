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

import java.util.ArrayList;
import java.util.List;

public class Pms_clinical_record_list extends AppCompatActivity {

    private DatabaseHelper clinical_record_dbHelp;
    private Context context;
    private ListView clinical_listview;

    private List<clinicalModel> clinical_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_clinical_record_list);

        context = this;
        clinical_record_dbHelp = new DatabaseHelper(this);
        clinical_listview = findViewById(R.id.Listview_clinical_record_list);



        final String receieved_patient_id = getIntent().getStringExtra("Patient_id");

        clinical_data = new ArrayList<>();


        clinical_data = clinical_record_dbHelp.displayClinicalRecordInfo(Integer.parseInt(receieved_patient_id));

        clinical_record_adapter clinical_adapter = new clinical_record_adapter(context,R.layout.activity_pms_single_clinical_record,clinical_data);

        clinical_listview.setAdapter(clinical_adapter);


        //clinicalModel clinical_data = clinical_record_list.displayClinicalRecordInfo(Integer.parseInt(receieved_patient_id));

        clinical_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                clinicalModel clinical_patient_object = clinical_data.get(position);

                AlertDialog.Builder alert_box_builder = new AlertDialog.Builder(context);
                alert_box_builder.setTitle("Record:"+clinical_patient_object.getRecord_id() + " " + "Patient:"+ clinical_patient_object.getClinical_patient_id()); //alert Title
                alert_box_builder.setMessage("Do you need to edit Patient Info");

                alert_box_builder.setPositiveButton("Yes!Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent forword_to_clinical_update = new Intent(getApplicationContext(), Pms_Edit_clinical_record.class);
                        forword_to_clinical_update.putExtra("record_id",String.valueOf(clinical_patient_object.getRecord_id()));
                        startActivity(forword_to_clinical_update);

                    }
                });
                alert_box_builder.show();


            }
        });


    }
}