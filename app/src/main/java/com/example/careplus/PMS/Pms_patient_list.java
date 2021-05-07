package com.example.careplus.PMS;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

public class Pms_patient_list extends AppCompatActivity {

    //creating objects
    private Button add_patient;
    private ListView listView;
    private TextView patient_count;
    Context context;

    //creating object from DatabaseHelper class
    private DatabaseHelper databaseHelper;



    //creating list type variables

    private List<patientModel> patient_list = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_patient_list);
        this.setTitle("Care Plus|Patient List");



        //matching xml data into created objects

        databaseHelper = new DatabaseHelper(this);
        add_patient = findViewById(R.id.patient_list_btn_add_patient);
        patient_count = findViewById(R.id.patient_list_txt_patient_count);
        listView = findViewById(R.id.patient_list_list_view);
        context =this;

        //initializing ArrayList variable
        patient_list = new ArrayList<>();

        //calling display method
        patient_list = databaseHelper.displayPatientInfo(DatabaseTable.Patient.TABLE_NAME);

        Patient_list_adapter adapter = new Patient_list_adapter(context,R.layout.activity_pms_patient_basic_list,patient_list);

        listView.setAdapter(adapter);







        //initializing DatabaseHelper type object



        //calling countpatient method to view patients

        int total_count = databaseHelper.countPatients(DatabaseTable.Patient.TABLE_NAME);
        patient_count.setText(total_count + " " + "Patients Admitted");

        //forwording user to add_patient activty
        add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent forword_to_add_patient = new Intent(getApplicationContext(), Pms_add_patient.class);
                startActivity(forword_to_add_patient);

            }
        });

        //alertbox

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //object from patientMoel class

                patientModel patient_model = patient_list.get(position);


                AlertDialog.Builder alert_box_builder = new AlertDialog.Builder(context);
                alert_box_builder.setTitle(patient_model.getP_fname() + patient_model.getP_lname()); //alert Title
                alert_box_builder.setMessage(patient_model.getP_reason()); //alert message


                //update
                alert_box_builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent forword_to_update = new Intent(getApplicationContext(), Pms_edit_patient_basic_info.class);
                        forword_to_update.putExtra("Patient_id",String.valueOf(patient_model.getP_id()));
                        startActivity(forword_to_update);



                    }
                }); //end update

                //Delete
                alert_box_builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        databaseHelper.deletePatientInfo(DatabaseTable.Patient.TABLE_NAME, patient_model.getP_id());


                        Toast.makeText(getApplicationContext(),"Item Deleted Successfully!",Toast.LENGTH_SHORT).show();

                        Intent back_to_home_after_delete = new Intent(getApplicationContext(),Pms_patient_list.class);
                        startActivity(back_to_home_after_delete);

                    }
                });//end delete

                alert_box_builder.show(); //show dialog box



            }
        });









    }
}