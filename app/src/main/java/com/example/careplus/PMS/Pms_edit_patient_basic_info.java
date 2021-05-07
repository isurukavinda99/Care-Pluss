package com.example.careplus.PMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class Pms_edit_patient_basic_info extends AppCompatActivity {

    private EditText update_patient_first_name, update_patient_last_name, update_patient_dob, update_patient_bed_no, update_patient_guardian_name, update_patient_contact, update_patient_address, update_patient_reason_stay, update_patient_date_admitted, update_patient_additional;
    private DatabaseHelper databaseHelper;
    private Context context;
    private Button btn_update, btn_cancel;

    boolean everythingChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_edit_patient_basic_info);

        context = this;

        databaseHelper = new DatabaseHelper(context);


        update_patient_first_name = findViewById(R.id.update_patient_txt_first_name);
        update_patient_last_name = findViewById(R.id.update_patient_txt_last_name);
        update_patient_dob = findViewById(R.id.update_patient_txt_dob);
        update_patient_bed_no = findViewById(R.id.update_patient_txt_bed_no);
        update_patient_guardian_name = findViewById(R.id.update_patient_txt_guardian_name);
        update_patient_contact = findViewById(R.id.update_patient_txt_contact);
        update_patient_address = findViewById(R.id.update_patient_txt_address);
        update_patient_reason_stay = findViewById(R.id.update_patient_txt_reason_stay);
        update_patient_date_admitted = findViewById(R.id.update_patient_txt_date_admitted);
        update_patient_additional = findViewById(R.id.update_patient_txt_additional_info);
        btn_update = findViewById(R.id.update_patient_btn_update);
        btn_cancel = findViewById(R.id.update_patient_btn_cancel);


        final String receieved_patient_id = getIntent().getStringExtra("Patient_id");
        //System.out.println(receieved_patient_id);


        patientModel recieved = databaseHelper.getOneRow(Integer.parseInt(receieved_patient_id), DatabaseTable.Patient.TABLE_NAME);

        update_patient_first_name.setText(recieved.getP_fname());
        update_patient_last_name.setText(recieved.getP_lname());
        //update_patient_first_name.setText(recieved.getP_fname());
        update_patient_dob.setText(String.valueOf(recieved.getP_dob()));
        update_patient_bed_no.setText(recieved.getP_bed_no());
        update_patient_guardian_name.setText(recieved.getP_guardian());
        update_patient_contact.setText(recieved.getP_contact());
        update_patient_address.setText(recieved.getP_address());
        update_patient_reason_stay.setText(recieved.getP_reason());
        update_patient_date_admitted.setText(String.valueOf(recieved.getP_date_admitted()));
        update_patient_additional.setText(recieved.getP_additional());


        //update method

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                everythingChecked = InputValidator();

                if (InputValidator()) {

                    String updated_patient_first_name = update_patient_first_name.getText().toString();
                    String updated_patient_last_name = update_patient_last_name.getText().toString();
                    String updated_patient_dob = update_patient_dob.getText().toString();
                    String updated_patient_bed_no = update_patient_bed_no.getText().toString();
                    String updated_patient_guardian_name = update_patient_guardian_name.getText().toString();
                    String updated_patient_contact = update_patient_contact.getText().toString();
                    String updated_patient_address = update_patient_address.getText().toString();
                    String updated_patient_reason_stay = update_patient_reason_stay.getText().toString();
                    String updated_patient_date_admitted = update_patient_date_admitted.getText().toString();
                    String updated_patient_additional = update_patient_additional.getText().toString();


                    patientModel update_patient = new patientModel(
                            Integer.parseInt(receieved_patient_id),
                            updated_patient_first_name,
                            updated_patient_last_name,
                            updated_patient_bed_no,
                            updated_patient_additional,
                            updated_patient_guardian_name,
                            updated_patient_contact,
                            updated_patient_address,
                            updated_patient_reason_stay,
                            (updated_patient_dob),
                            (updated_patient_date_admitted)


                    );

                    int update_results = databaseHelper.update_patient_data(update_patient);


                    Toast.makeText(getApplicationContext(), update_results + " Patients Updated", Toast.LENGTH_SHORT).show();

                    Intent finished_update = new Intent(context, Pms_patient_list.class);
                    startActivity(finished_update);

                }




            }
        });//end update


    }


    private boolean InputValidator() {

        // patient_bed_no, patient_guardian_name, patient_contact, patient_address, patient_reason_stay, patient_date_admitted, patient_additional

        //, , , , , , , , ,

        if (update_patient_first_name.length() == 0) {
            update_patient_first_name.setError("First Name Cannot be Empty");
            update_patient_first_name.requestFocus();
            return false;
        }

        if (update_patient_last_name.length() == 0) {
            update_patient_last_name.setError("Last Name Cannot be Empty");
            update_patient_last_name.requestFocus();
            return false;
        }
        if (update_patient_dob.length() == 0) {
            update_patient_dob.setError("Date of Birth Cannot be Empty");
            update_patient_dob.requestFocus();
            return false;
        }
        if (update_patient_bed_no.length() == 0) {
            update_patient_bed_no.setError("Bed No Cannot be Empty");
            update_patient_bed_no.requestFocus();
            return false;
        }
        if (update_patient_guardian_name.length() == 0) {
            update_patient_guardian_name.setError("Guardian Name Cannot be Empty");
            update_patient_guardian_name.requestFocus();
            return false;
        }
        if (update_patient_contact.length() == 0) {
            update_patient_contact.setError("Contact Number Cannot be Empty");
            update_patient_contact.requestFocus();
            return false;
        }


        if (update_patient_address.length() == 0) {
            update_patient_address.setError("Address Cannot be Empty");
            update_patient_address.requestFocus();
            return false;
        }
        if (update_patient_reason_stay.length() == 0) {
            update_patient_reason_stay.setError("Reason Cannot be Empty");
            update_patient_reason_stay.requestFocus();
            return false;
        }
        if (update_patient_date_admitted.length() == 0) {
            update_patient_date_admitted.setError("Admitted Date Cannot be Empty");
            update_patient_date_admitted.requestFocus();
            return false;
        }
        if (update_patient_additional.length() == 0) {
            update_patient_additional.setError("Additional Data Cannot be Empty");
            update_patient_additional.requestFocus();
            return false;
        }

        return true;

    }
}