package com.example.careplus.prms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
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

public class Prms_reallocate_prescription extends AppCompatActivity {

    EditText prms_txt_search_reallocate_prec;

    RadioButton prms_morning_radio_reallocate , prms_day_radio_reallocate , prms_night_radio_reallocate;

    TextView prms_lbl_reallocate_id , prms_lbl_reallocate_name , prms_lbl_reallocate_dob , prms_lbl_reallocate_admit_date , prms_lbl_reallocate_reason;

    DatabaseHelper reallocate_database;

    LinearLayout prms_reallocate_prec_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prms_reallocate_prescription);

        prms_txt_search_reallocate_prec = findViewById(R.id.prms_txt_search_reallocate_prec);

        prms_morning_radio_reallocate = findViewById(R.id.prms_morning_radio_reallocate);
        prms_day_radio_reallocate = findViewById(R.id.prms_day_radio_reallocate);
        prms_night_radio_reallocate = findViewById(R.id.prms_night_radio_reallocate);

        prms_lbl_reallocate_id = findViewById(R.id.prms_lbl_reallocate_id);
        prms_lbl_reallocate_name = findViewById(R.id.prms_lbl_reallocate_name);
        prms_lbl_reallocate_dob = findViewById(R.id.prms_lbl_reallocate_dob);
        prms_lbl_reallocate_admit_date = findViewById(R.id.prms_lbl_reallocate_admit_date);
        prms_lbl_reallocate_reason = findViewById(R.id.prms_lbl_reallocate_reason);

        prms_reallocate_prec_list = findViewById(R.id.prms_reallocate_prec_list);

        reallocate_database = new DatabaseHelper(this);


    }

    public void check_and_remove(View v){

        if(v.getId() == R.id.prms_morning_radio_reallocate){
            prms_morning_radio_reallocate.setChecked(true);
            prms_day_radio_reallocate.setChecked(false);
            prms_night_radio_reallocate.setChecked(false);
        }else if(v.getId() == R.id.prms_day_radio_reallocate){
            prms_morning_radio_reallocate.setChecked(false);
            prms_day_radio_reallocate.setChecked(true);
            prms_night_radio_reallocate.setChecked(false);
        }else if (v.getId() == R.id.prms_night_radio_reallocate){
            prms_morning_radio_reallocate.setChecked(false);
            prms_day_radio_reallocate.setChecked(false);
            prms_night_radio_reallocate.setChecked(true);
        }

    }

    public void search_to_reallocate(View v){

        /*Clear list view*/
        prms_reallocate_prec_list.removeAllViews();

        prms_lbl_reallocate_id.setText(null);
        prms_lbl_reallocate_name.setText(null);
        prms_lbl_reallocate_dob.setText(null);
        prms_lbl_reallocate_admit_date.setText(null);
        prms_lbl_reallocate_reason.setText(null);

        if(!prms_txt_search_reallocate_prec.getText().toString().isEmpty()) {

            /*check prescription type*/
            if(prms_morning_radio_reallocate.isChecked() || prms_day_radio_reallocate.isChecked() || prms_night_radio_reallocate.isChecked()) {

                String pid = prms_txt_search_reallocate_prec.getText().toString();

                String type = "unknown";
                if(prms_morning_radio_reallocate.isChecked()){
                    type = "morning";
                }else if(prms_day_radio_reallocate.isChecked()){
                    type = "day";
                }else if(prms_night_radio_reallocate.isChecked()){
                    type = "night";
                }

                /*check patient is avalable or not*/
                String patient_coll[] = {DatabaseTable.Patient.PATIENT_ID , DatabaseTable.Patient.PATIENT_FIRST_NAME , DatabaseTable.Patient.PATIENT_DOB , DatabaseTable.Patient.PATIENT_DATE_ADMITTED , DatabaseTable.Patient.PATIENT_REASON};
                String patient_where = DatabaseTable.Patient.PATIENT_ID + " = ? ";
                String patient_where_args [] = {pid};

                Cursor patient = reallocate_database.view(DatabaseTable.Patient.TABLE_NAME , patient_coll , patient_where , patient_where_args , null);

                if(patient.getCount() == 1){

                    while(patient.moveToNext()) {

                        prms_lbl_reallocate_id.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_ID)));
                        prms_lbl_reallocate_name.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_FIRST_NAME)));
                        prms_lbl_reallocate_dob.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_DOB)));
                        prms_lbl_reallocate_admit_date.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_DATE_ADMITTED)));
                        prms_lbl_reallocate_reason.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_REASON)));


                        String presc_col[] = {DatabaseTable.Prescription.PRESC_ID , DatabaseTable.Prescription.PRESC_IS_CURRENT};
                        String presc_where = DatabaseTable.Prescription.PATIENT_ID + " = ? and " + DatabaseTable.Prescription.PRESC_TYPE + " = ? ";
                        String presc_where_args[] = {pid, type};
                        String sort = DatabaseTable.Prescription.PRESC_IS_CURRENT + " DESC";
                        Cursor prescription = reallocate_database.view(DatabaseTable.Prescription.TABLE_NAME, presc_col, presc_where, presc_where_args, sort);

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

                                th_name.setText("Drug Name");
                                th_name.setWidth(prms_reallocate_prec_list.getWidth() / 3);
                                th_name.setGravity(Gravity.CENTER);
                                th_name.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_name.setTextColor(getResources().getColor(R.color.white));

                                th_dose.setText("Dose");
                                th_dose.setWidth(prms_reallocate_prec_list.getWidth() / 3);
                                th_dose.setGravity(Gravity.CENTER);
                                th_dose.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_dose.setTextColor(getResources().getColor(R.color.white));

                                th_beforeMeal.setText("Before Meal");
                                th_beforeMeal.setWidth(prms_reallocate_prec_list.getWidth() / 3);
                                th_beforeMeal.setGravity(Gravity.CENTER);
                                th_beforeMeal.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_beforeMeal.setTextColor(getResources().getColor(R.color.white));



                                TextView presc_id = new TextView(this);
                                presc_id.setText("Prescription Id : " + prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID)));
                                presc_id.setWidth(prms_reallocate_prec_list.getWidth()/3);

                                TextView current = new TextView(this);
                                current.setWidth(prms_reallocate_prec_list.getWidth()/3);

                                Button reallocate_buttion = new Button(this);

                                /*if current this will add*/
                                if(Integer.parseInt(prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_IS_CURRENT))) == 1){
                                    current.setText("Current");
                                    current.setTextColor(getResources().getColor(R.color.prms_red_color));
                                    reallocate_buttion.setText("Cant Reallocate");
                                    reallocate_buttion.setBackgroundColor(getResources().getColor(R.color.prms_table_bg));
                                    reallocate_buttion.setTextColor(getResources().getColor(R.color.com_color_teal));
                                }else{
                                    current.setText("Not Current");
                                    /*delete button*/
                                    reallocate_buttion.setText("Allocate");
                                    reallocate_buttion.setWidth(prms_reallocate_prec_list.getWidth()/3);
                                    reallocate_buttion.setTextColor(getResources().getColor(R.color.com_color_update));
                                    reallocate_buttion.setBackgroundResource(R.drawable.prms_reallocate_button_bg);

                                    TableRow.LayoutParams buttion_lp = new TableRow.LayoutParams();
                                    buttion_lp.setMargins(5, 5 , 5 ,5);
                                    reallocate_buttion.setLayoutParams(buttion_lp);

                                    reallocate_buttion.setPadding(0,0,0,0);

                                    reallocate_buttion.setId(Integer.parseInt(prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID))));

                                    /*set onclick listner for dynamicly added buttons*/
                                    reallocate_buttion.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            reallocate_prescription(v);
                                        }
                                    });
                                }


                                about_prescription.addView(presc_id);
                                about_prescription.addView(current);
                                about_prescription.addView(reallocate_buttion);

                                row.addView(th_name);
                                row.addView(th_dose);
                                row.addView(th_beforeMeal);

                                table.addView(about_prescription);
                                table.addView(row);


                                /*Add drug Entities*/
                                String drug_col[] = {DatabaseTable.Drug.DRUG_NAME , DatabaseTable.Drug.DRUG_DOSE , DatabaseTable.Drug.DRUG_BEFORE_MEAL};
                                String drug_where = DatabaseTable.Drug.PRESCRIPTION_ID + " = ? ";
                                String drug_where_args[] = {prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID))};

                                Cursor drug_entity = reallocate_database.view(DatabaseTable.Drug.TABLE_NAME , drug_col , drug_where , drug_where_args , null);

                                /*if there is drug entity only add entiy*/
                                if(drug_entity.getCount() != 0){

                                    while(drug_entity.moveToNext()) {

                                        TableRow entity = new TableRow(this);

                                        TextView d_name = new TextView(this);
                                        TextView d_dose = new TextView(this);
                                        TextView d_Before_meal = new TextView(this);

                                        /*set values*/
                                        d_name.setText(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_NAME)));
                                        d_name.setWidth(prms_reallocate_prec_list.getWidth()/3);
                                        d_name.setGravity(Gravity.CENTER);

                                        d_dose.setText(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_DOSE)));
                                        d_dose.setWidth(prms_reallocate_prec_list.getWidth()/3);
                                        d_dose.setGravity(Gravity.CENTER);


                                        d_Before_meal.setText(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_BEFORE_MEAL)));
                                        d_Before_meal.setWidth(prms_reallocate_prec_list.getWidth()/3);
                                        d_Before_meal.setGravity(Gravity.CENTER);

                                        entity.addView(d_name);
                                        entity.addView(d_dose);
                                        entity.addView(d_Before_meal);

                                        table.addView(entity);


                                    }

                                }


                                prms_reallocate_prec_list.addView(table);
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

    public void reallocate_prescription(View v){

        String type = "unknown";
        if(prms_morning_radio_reallocate.isChecked()){
            type = "morning";
        }else if(prms_day_radio_reallocate.isChecked()){
            type = "day";
        }else if(prms_night_radio_reallocate.isChecked()){
            type = "night";
        }

        String patient = prms_lbl_reallocate_id.getText().toString();

        ContentValues update_currrent_f = new ContentValues();
        update_currrent_f.put(DatabaseTable.Prescription.PRESC_IS_CURRENT , 0);

        String update_where = DatabaseTable.Prescription.PATIENT_ID + " = ? and " + DatabaseTable.Prescription.PRESC_TYPE + " = ? ";
        String update_where_args [] = {patient , type};

        int result = reallocate_database.update(DatabaseTable.Prescription.TABLE_NAME , update_currrent_f , update_where , update_where_args);

        if(result > 0){

            ContentValues set_current_t = new ContentValues();
            set_current_t.put(DatabaseTable.Prescription.PRESC_IS_CURRENT , 1);

            String set_t_where = DatabaseTable.Prescription.PRESC_ID + " = ? ";
            String set_t_where_args [] = {v.getId()+""};

            int success = reallocate_database.update(DatabaseTable.Prescription.TABLE_NAME , set_current_t , set_t_where , set_t_where_args);

            if(success == 1){

                Toast patient_error = new Toast(this);
                patient_error.setText("Allocation Success");
                patient_error.show();

                /*recall search method*/
                search_to_reallocate(v);

            }else{
                Toast patient_error = new Toast(this);
                patient_error.setText("Something Went Wrong Please Try Again Later");
                patient_error.show();
            }

        }else{
            Toast patient_error = new Toast(this);
            patient_error.setText("Something Went Wrong Please Try Again Later");
            patient_error.show();
        }

    }

}