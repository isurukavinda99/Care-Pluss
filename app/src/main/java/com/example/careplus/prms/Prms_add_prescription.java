package com.example.careplus.prms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Prms_add_prescription extends AppCompatActivity {

    /*Create Entity Array To get what are the Entities*/
    ArrayList<String[]> entity = new ArrayList<>();

    DatabaseHelper add_prescription;

    RadioButton prms_morning_radio , prms_day_radio , prms_night_radio;

    EditText prms_txt_search_add_prec;

    TextView prms_lbl_add_name , prms_lbl_add_dob , prms_lbl_add_admit_date , prms_lbl_add_reason , prms_lbl_add_id;

    EditText prms_txt_add_drug_name, prms_txt_add_dose;
    CheckBox prms_chk_before_meal;

    TableLayout prms_add_entity_table;

    EditText prms_txt_add_doc_name, prms_txt_add_doc_moh , prms_txt_add_doc_descripton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prms_add_prescription);

        add_prescription = new DatabaseHelper(this);

        prms_morning_radio = findViewById(R.id.prms_morning_radio);
        prms_day_radio = findViewById(R.id.prms_day_radio);
        prms_night_radio = findViewById(R.id.prms_night_radio);

        prms_txt_search_add_prec = findViewById(R.id.prms_txt_search_view_prec);

        prms_lbl_add_name = findViewById(R.id.prms_lbl_view_name);
        prms_lbl_add_dob = findViewById(R.id.prms_lbl_view_dob);
        prms_lbl_add_admit_date = findViewById(R.id.prms_lbl_view_admit_date);
        prms_lbl_add_reason = findViewById(R.id.prms_lbl_view_reason);
        prms_lbl_add_id = findViewById(R.id.prms_lbl_view_id);

        prms_txt_add_drug_name = findViewById(R.id.prms_txt_add_drug_name);
        prms_txt_add_dose = findViewById(R.id.prms_txt_add_dose);
        prms_chk_before_meal = findViewById(R.id.prms_chk_before_meal);

        prms_add_entity_table = findViewById(R.id.prms_add_entity_table);

        prms_txt_add_doc_name = findViewById(R.id.prms_txt_add_doc_name);
        prms_txt_add_doc_moh = findViewById(R.id.prms_txt_add_doc_moh);
        prms_txt_add_doc_descripton = findViewById(R.id.prms_txt_add_doc_descripton);



    }

    public void reselect_radio(View v){

        if(v.getId() == R.id.prms_morning_radio){
            prms_morning_radio.setChecked(true);
            prms_day_radio.setChecked(false);
            prms_night_radio.setChecked(false);
        }else if(v.getId() == R.id.prms_day_radio){
            prms_morning_radio.setChecked(false);
            prms_day_radio.setChecked(true);
            prms_night_radio.setChecked(false);
        }else if (v.getId() == R.id.prms_night_radio){
            prms_morning_radio.setChecked(false);
            prms_day_radio.setChecked(false);
            prms_night_radio.setChecked(true);
        }


    }


    public void search_patient(View v){
        String pid = prms_txt_search_add_prec.getText().toString();


        if(!pid.isEmpty()){

            String col[] = {DatabaseTable.Patient.PATIENT_ID,DatabaseTable.Patient.PATIENT_FIRST_NAME,DatabaseTable.Patient.PATIENT_LAST_NAME , DatabaseTable.Patient.PATIENT_DOB, DatabaseTable.Patient.PATIENT_DATE_ADMITTED, DatabaseTable.Patient.PATIENT_REASON};
            String where = DatabaseTable.Patient.PATIENT_ID + " = ?";
            String whereArgs[] = {pid};

            Cursor patient = add_prescription.view(DatabaseTable.Patient.TABLE_NAME , col , where , whereArgs ,null);



            if(patient.getCount() == 1 ){

                while(patient.moveToNext()){
                    prms_lbl_add_id.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_ID)));
                    prms_lbl_add_name.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_FIRST_NAME)));
                    prms_lbl_add_dob.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_DOB)));
                    prms_lbl_add_admit_date.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_DATE_ADMITTED)));
                    prms_lbl_add_reason.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_REASON)));
                }

            }else{
                Toast no_patient = new Toast(this);
                no_patient.setDuration(Toast.LENGTH_SHORT);
                no_patient.setText("There Is No Any Patient Along This Id");
                no_patient.show();
            }


        }else{
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText("Please Enter A Patient Id");
            toast.show();
        }

    }


    public void add_entity(View v){

        String drug_name = prms_txt_add_drug_name.getText().toString();
        String dose = prms_txt_add_dose.getText().toString();
        boolean before_meal = prms_chk_before_meal.isChecked();

        if(!drug_name.isEmpty() && !dose.isEmpty()){

            TableRow newRow = new TableRow(this);

            newRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


            TextView d_name = new TextView(this);
            TextView d_dose = new TextView(this);
            TextView d_before = new TextView(this);



            d_name.setText(drug_name);
            d_dose.setText(dose);
            d_before.setText(before_meal+"");

            /*Set this element to array list*/
            entity.add(new String[]{drug_name , dose , before_meal+""});

            //set width
            d_name.setWidth(prms_add_entity_table.getWidth()/3);
            d_dose.setWidth(prms_add_entity_table.getWidth()/3);
            d_before.setWidth(prms_add_entity_table.getWidth()/3);

            /*set gravity to center*/
            d_name.setGravity(Gravity.CENTER);
            d_dose.setGravity(Gravity.CENTER);
            d_before.setGravity(Gravity.CENTER);


            newRow.addView(d_name);
            newRow.addView(d_dose);
            newRow.addView(d_before);


            prms_add_entity_table.addView(newRow);

            prms_txt_add_drug_name.setText(null);
            prms_txt_add_dose.setText(null);
            prms_chk_before_meal.setChecked(false);

            System.out.println(entity.isEmpty());

        }else{
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText("Please Enter Drug Name And Dose");
            toast.show();
        }

    }

    public void create_prescription_to_patient(View v){

        /*check there is any patient selected*/
        String patient = prms_lbl_add_id.getText().toString();

        if (!patient.isEmpty()) {

            /*check if select prescription type*/
            if (prms_morning_radio.isChecked() || prms_day_radio.isChecked() || prms_night_radio.isChecked()) {

                /*Check is there entity array list is empty or not*/
                if (!entity.isEmpty()) {

                    /*check patient data fill or not*/
                    String doc_name = prms_txt_add_doc_name.getText().toString();
                    String doc_moh = prms_txt_add_doc_moh.getText().toString();
                    String doc_desc = prms_txt_add_doc_descripton.getText().toString();

                    if (!doc_name.isEmpty() && !doc_moh.isEmpty() && !doc_desc.isEmpty()) {

                        /*create new prescription*/

                        /*get date*/
                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String formattedDate = df.format(c);

                        /*get prescription type*/
                        String type = "unknown";
                        if(prms_morning_radio.isChecked()){
                            type = "morning";
                        }else if(prms_day_radio.isChecked()){
                            type = "day";
                        }else if(prms_night_radio.isChecked()){
                            type = "night";
                        }

                        ContentValues prescription = new ContentValues();
                        prescription.put(DatabaseTable.Prescription.PRESC_DOCTOR_NAME , doc_name);
                        prescription.put(DatabaseTable.Prescription.PRESC_DOC_MOH_NUMBER , doc_moh);
                        prescription.put(DatabaseTable.Prescription.PRESC_DESCRIPTION , doc_desc);
                        prescription.put(DatabaseTable.Prescription.PRESC_TYPE,type);
                        prescription.put(DatabaseTable.Prescription.PRESC_ALLOCATED_DATE , formattedDate);
                        prescription.put(DatabaseTable.Prescription.PRESC_IS_CURRENT, true);
                        prescription.put(DatabaseTable.Prescription.PATIENT_ID , patient);


                        /*set other all prescriptions to is current false*/
                        ContentValues falseIsCurrent = new ContentValues();
                        falseIsCurrent.put(DatabaseTable.Prescription.PRESC_IS_CURRENT,false);

                        String updateWhere = DatabaseTable.Prescription.PATIENT_ID + " = ? and " + DatabaseTable.Prescription.PRESC_TYPE + " = ? and " + DatabaseTable.Prescription.PRESC_IS_CURRENT + " = ? ";
                        String updateWhereArgs[] = {patient , type , "1"};

                        add_prescription.update(DatabaseTable.Prescription.TABLE_NAME , falseIsCurrent , updateWhere , updateWhereArgs);


                        /*create new and assign is current true*/
                        add_prescription.save(DatabaseTable.Prescription.TABLE_NAME , prescription);

                        /*crete drug entity and add it in to above prescription*/

                        String presc_coll[] = {DatabaseTable.Prescription.PRESC_ID};
                        String where = DatabaseTable.Prescription.PRESC_IS_CURRENT + " = ? and " + DatabaseTable.Prescription.PRESC_TYPE + " = ? and " + DatabaseTable.Prescription.PATIENT_ID + " = ? ";
                        String whereArgs[] = {"1" , type , patient};

                        Cursor current_presc = add_prescription.view(DatabaseTable.Prescription.TABLE_NAME , presc_coll , where ,whereArgs , null);


                        if(current_presc.getCount() == 1){

                            while(current_presc.moveToNext()){

                                String prec_id = current_presc.getString(current_presc.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID));

                                /*add all drug entities to table*/
                                for(String[] drug : entity){


                                    ContentValues drug_to_add = new ContentValues();
                                    drug_to_add.put(DatabaseTable.Drug.DRUG_NAME , drug[0]);
                                    drug_to_add.put(DatabaseTable.Drug.DRUG_DOSE , drug[1]);
                                    drug_to_add.put(DatabaseTable.Drug.DRUG_BEFORE_MEAL , drug[2]);
                                    drug_to_add.put(DatabaseTable.Drug.PRESCRIPTION_ID , prec_id);


                                    add_prescription.save(DatabaseTable.Drug.TABLE_NAME , drug_to_add);

                                }

                                /*set null all text views and edit text*/
                                prms_txt_search_add_prec.setText(null);

                                prms_lbl_add_id.setText(null);
                                prms_lbl_add_dob.setText(null);
                                prms_lbl_add_name.setText(null);
                                prms_lbl_add_reason.setText(null);
                                prms_lbl_add_admit_date.setText(null);

                                prms_morning_radio.setChecked(false);
                                prms_day_radio.setChecked(false);
                                prms_night_radio.setChecked(false);

                                prms_txt_add_doc_name.setText(null);
                                prms_txt_add_doc_moh.setText(null);
                                prms_txt_add_doc_descripton.setText(null);

                                for(int x = 1 ; x < prms_add_entity_table.getChildCount() ; x++){
                                    View row = prms_add_entity_table.getChildAt(x);
                                    prms_add_entity_table.removeView(row);

                                }


                                entity.clear();
                                Toast toast = new Toast(this);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setText("Drug Added Success");
                                toast.show();

                                break;
                            }

                        }else{
                            Toast toast = new Toast(this);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.setText("Something Went wrong please try again");
                            toast.show();
                        }


                    } else {
                        Toast toast = new Toast(this);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setText("Please Fill Doctor Information");
                        toast.show();
                    }

                } else {
                    Toast toast = new Toast(this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setText("Please Add Drug Entity");
                    toast.show();
                }

            } else {
                Toast toast = new Toast(this);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setText("Please Select A Prescription Type");
                toast.show();
            }

        }else{
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText("Please Select A Patient");
            toast.show();
        }

    }

}