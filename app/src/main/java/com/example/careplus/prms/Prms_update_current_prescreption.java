package com.example.careplus.prms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Trace;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class Prms_update_current_prescreption extends AppCompatActivity {

    LinearLayout prms_update_prescr_list;
    EditText prms_txt_search_update_prec;

    RadioButton prms_morning_radio_update , prms_day_radio_update , prms_night_radio_update;


    TextView prms_lbl_update_id ,prms_lbl_update_name,prms_lbl_update_dob,prms_lbl_update_admit_date,prms_lbl_update_reason;

    DatabaseHelper update_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prms_update_current_prescreption);


        prms_update_prescr_list = findViewById(R.id.prms_update_prescr_list);

        prms_txt_search_update_prec = findViewById(R.id.prms_txt_search_update_prec);

        prms_morning_radio_update = findViewById(R.id.prms_morning_radio_update);
        prms_day_radio_update = findViewById(R.id.prms_day_radio_update);
        prms_night_radio_update = findViewById(R.id.prms_night_radio_update);

        prms_lbl_update_id = findViewById(R.id.prms_lbl_update_id);
        prms_lbl_update_name = findViewById(R.id.prms_lbl_update_name);
        prms_lbl_update_dob = findViewById(R.id.prms_lbl_update_dob);
        prms_lbl_update_admit_date = findViewById(R.id.prms_lbl_update_admit_date);
        prms_lbl_update_reason = findViewById(R.id.prms_lbl_update_reason);

        update_database = new DatabaseHelper(this);

    }

    public void check_and_remove(View v){

        if(v.getId() == R.id.prms_morning_radio_update){
            prms_morning_radio_update.setChecked(true);
            prms_day_radio_update.setChecked(false);
            prms_night_radio_update.setChecked(false);
        }else if(v.getId() == R.id.prms_day_radio_update){
            prms_morning_radio_update.setChecked(false);
            prms_day_radio_update.setChecked(true);
            prms_night_radio_update.setChecked(false);
        }else if (v.getId() == R.id.prms_night_radio_update){
            prms_morning_radio_update.setChecked(false);
            prms_day_radio_update.setChecked(false);
            prms_night_radio_update.setChecked(true);
        }

    }

    public void search_to_update(View v){


        /*Clear list view*/
        prms_update_prescr_list.removeAllViews();

        prms_lbl_update_id.setText(null);
        prms_lbl_update_name.setText(null);
        prms_lbl_update_dob.setText(null);
        prms_lbl_update_admit_date.setText(null);
        prms_lbl_update_reason.setText(null);

        if(!prms_txt_search_update_prec.getText().toString().isEmpty()) {

            /*check prescription type*/
            if(prms_morning_radio_update.isChecked() || prms_day_radio_update.isChecked() || prms_night_radio_update.isChecked()) {

                String pid = prms_txt_search_update_prec.getText().toString();

                String type = "unknown";
                if(prms_morning_radio_update.isChecked()){
                    type = "morning";
                }else if(prms_day_radio_update.isChecked()){
                    type = "day";
                }else if(prms_night_radio_update.isChecked()){
                    type = "night";
                }

                /*check patient is avalable or not*/
                String patient_coll[] = {DatabaseTable.Patient.PATIENT_ID , DatabaseTable.Patient.PATIENT_FIRST_NAME , DatabaseTable.Patient.PATIENT_DOB , DatabaseTable.Patient.PATIENT_DATE_ADMITTED , DatabaseTable.Patient.PATIENT_REASON};
                String patient_where = DatabaseTable.Patient.PATIENT_ID + " = ? ";
                String patient_where_args [] = {pid};

                Cursor patient = update_database.view(DatabaseTable.Patient.TABLE_NAME , patient_coll , patient_where , patient_where_args , null);

                if(patient.getCount() == 1){

                    while(patient.moveToNext()) {

                        prms_lbl_update_id.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_ID)));
                        prms_lbl_update_name.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_FIRST_NAME)));
                        prms_lbl_update_dob.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_DOB)));
                        prms_lbl_update_admit_date.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_DATE_ADMITTED)));
                        prms_lbl_update_reason.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_REASON)));


                        String presc_col[] = {DatabaseTable.Prescription.PRESC_ID , DatabaseTable.Prescription.PRESC_IS_CURRENT};
                        String presc_where = DatabaseTable.Prescription.PATIENT_ID + " = ? and " + DatabaseTable.Prescription.PRESC_TYPE + " = ? and " + DatabaseTable.Prescription.PRESC_IS_CURRENT + " = ? ";
                        String presc_where_args[] = {pid, type , "1"};
                        String sort = DatabaseTable.Prescription.PRESC_IS_CURRENT + " DESC";
                        Cursor prescription = update_database.view(DatabaseTable.Prescription.TABLE_NAME, presc_col, presc_where, presc_where_args, sort);

                        if (prescription.getCount() != 0) {

                            while (prescription.moveToNext()) {

                                TableLayout table = new TableLayout(this);
                                table.setPadding(0,0,0,50);

                                TableRow.LayoutParams lp = new TableRow.LayoutParams();
                                lp.setMargins(0,0,0,50);
                                table.setLayoutParams(lp);

                                table.setBackgroundColor(getResources().getColor(R.color.prms_table_bg));

                                TableRow row = new TableRow(this);

                                TableRow about_prescription = new TableRow(this);


                                /*Table hedders*/
                                TextView th_name = new TextView(this);
                                TextView th_dose = new TextView(this);
                                TextView th_beforeMeal = new TextView(this);
                                TextView th_edit = new TextView(this);

                                th_name.setText("Drug Name");
                                th_name.setWidth(prms_update_prescr_list.getWidth() / 4);
                                th_name.setGravity(Gravity.CENTER);
                                th_name.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_name.setTextColor(getResources().getColor(R.color.white));

                                th_dose.setText("Dose");
                                th_dose.setWidth(prms_update_prescr_list.getWidth() / 4);
                                th_dose.setGravity(Gravity.CENTER);
                                th_dose.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_dose.setTextColor(getResources().getColor(R.color.white));

                                th_beforeMeal.setText("Before Meal");
                                th_beforeMeal.setWidth(prms_update_prescr_list.getWidth() / 4);
                                th_beforeMeal.setGravity(Gravity.CENTER);
                                th_beforeMeal.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_beforeMeal.setTextColor(getResources().getColor(R.color.white));

                                th_edit.setText("Edit");
                                th_edit.setWidth(prms_update_prescr_list.getWidth()/4);
                                th_edit.setGravity(Gravity.CENTER);
                                th_edit.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_edit.setTextColor(getResources().getColor(R.color.white));



                                TextView presc_id = new TextView(this);
                                presc_id.setText("Prescription Id : " + prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID)));
                                presc_id.setWidth(prms_update_prescr_list.getWidth()/4);

                                TextView current = new TextView(this);
                                current.setWidth(prms_update_prescr_list.getWidth()/4);

                                /*if current this will add*/
                                if(Integer.parseInt(prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_IS_CURRENT))) == 1){
                                    current.setText("Current");
                                    current.setTextColor(getResources().getColor(R.color.prms_red_color));
                                }else{
                                    current.setText("Not Current");
                                }


                                about_prescription.addView(presc_id);
                                about_prescription.addView(current);

                                row.addView(th_name);
                                row.addView(th_dose);
                                row.addView(th_beforeMeal);
                                row.addView(th_edit);

                                table.addView(about_prescription);
                                table.addView(row);


                                /*Add drug Entities*/
                                String drug_col[] = {DatabaseTable.Drug.DRUG_ID,DatabaseTable.Drug.DRUG_NAME , DatabaseTable.Drug.DRUG_DOSE , DatabaseTable.Drug.DRUG_BEFORE_MEAL};
                                String drug_where = DatabaseTable.Drug.PRESCRIPTION_ID + " = ? ";
                                String drug_where_args[] = {prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID))};

                                Cursor drug_entity = update_database.view(DatabaseTable.Drug.TABLE_NAME , drug_col , drug_where , drug_where_args , null);

                                /*if there is drug entity only add entiy*/
                                if(drug_entity.getCount() != 0){

                                    while(drug_entity.moveToNext()) {

                                        TableRow entity = new TableRow(this);

                                        entity.setPadding(0 , 10 , 0 , 10);

                                        EditText d_name = new EditText(this);
                                        EditText d_dose = new EditText(this);
                                        CheckBox d_Before_meal = new CheckBox(this);

                                        /*set values*/
                                        d_name.setText(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_NAME)));
                                        d_name.setWidth(prms_update_prescr_list.getWidth()/4);
                                        d_name.setGravity(Gravity.CENTER);
                                        d_name.setTag("drug_name_" + drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_ID)));

                                        d_dose.setText(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_DOSE)));
                                        d_dose.setWidth(prms_update_prescr_list.getWidth()/4);
                                        d_dose.setGravity(Gravity.CENTER);
                                        d_dose.setTag("drug_dose_" + drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_ID)));



                                        if(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_BEFORE_MEAL)).equals( "true" )){

                                            d_Before_meal.setChecked(true);
                                        }else{
                                            d_Before_meal.setChecked(false);
                                        }

                                        d_Before_meal.setWidth(prms_update_prescr_list.getWidth()/4);
                                        d_Before_meal.setGravity(Gravity.CENTER);
                                        d_Before_meal.setTag("drug_before_" + drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_ID)));;

                                        Button update_btn = new Button(this);
                                        update_btn.setText("Update");
                                        update_btn.setTag("drug_btn_" + drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_ID)));
                                        update_btn.setId(Integer.parseInt(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_ID))));
                                        update_btn.setTextColor(getResources().getColor(R.color.com_color_update));
                                        update_btn.setBackgroundResource(R.drawable.prms_reallocate_button_bg);
                                        update_btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                update_drug(v);
                                            }
                                        });


                                        entity.addView(d_name);
                                        entity.addView(d_dose);
                                        entity.addView(d_Before_meal);
                                        entity.addView(update_btn);

                                        table.addView(entity);


                                    }

                                }


                                prms_update_prescr_list.addView(table);
                            }

                        } else {
                            Toast patient_error = new Toast(this);
                            patient_error.setText("There Is No Any Prescription");
                            patient_error.show();
                        }


                        break;
                    }

                }else{
                    Toast patient_error = new Toast(this);
                    patient_error.setText("There Is No Any Patient");
                    patient_error.show();
                }


            }else{
                Toast patient_error = new Toast(this);
                patient_error.setText("Select Prescription Type");
                patient_error.show();
            }

        }else{
            Toast patient_error = new Toast(this);
            patient_error.setText("Enter A Patient Id To search");
            patient_error.show();
        }

    }


    public void update_drug(View v){

        EditText updated_d_name = (EditText)prms_update_prescr_list.findViewWithTag("drug_name_"+v.getId());
        EditText updated_d_dose = (EditText)prms_update_prescr_list.findViewWithTag("drug_dose_"+v.getId());
        CheckBox updated_d_before_m = (CheckBox)prms_update_prescr_list.findViewWithTag("drug_before_"+v.getId());

        /*check updated values are empty or not*/
        if(!updated_d_name.getText().toString().isEmpty()){

            if(!updated_d_dose.getText().toString().isEmpty()){

                //update Query
                /*check drug entity exist or not*/

                ContentValues update_values = new ContentValues();
                update_values.put(DatabaseTable.Drug.DRUG_NAME , updated_d_name.getText().toString());
                update_values.put(DatabaseTable.Drug.DRUG_DOSE , updated_d_dose.getText().toString());
                update_values.put(DatabaseTable.Drug.DRUG_BEFORE_MEAL, (updated_d_before_m.isChecked()) ? "true" : "false");

                String where_update = DatabaseTable.Drug.DRUG_ID + " = ? ";
                String where_args_update [] = {v.getId()+""};

                int result = update_database.update(DatabaseTable.Drug.TABLE_NAME , update_values , where_update , where_args_update);

                if(result == 1){
                    Toast patient_error = new Toast(this);
                    patient_error.setText("Update Success");
                    patient_error.show();

                    /*after update success call serch method again to refesh values*/
                    search_to_update(v);
                }else{
                    Toast patient_error = new Toast(this);
                    patient_error.setText("update un success");
                    patient_error.show();
                }

            }else{
                Toast patient_error = new Toast(this);
                patient_error.setText("Enter A Value For Drug Dose");
                patient_error.show();
            }

        }else{
            Toast patient_error = new Toast(this);
            patient_error.setText("Enter A Value For Drug Name");
            patient_error.show();
        }

    }

}