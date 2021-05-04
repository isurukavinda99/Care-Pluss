package com.example.careplus.prms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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

public class Prms_delete_prescription extends AppCompatActivity {

    RadioButton prms_morning_radio_delete , prms_day_radio_delete , prms_night_radio_delete;

    EditText prms_txt_search_delete_prec;

    TextView prms_lbl_delete_id, prms_lbl_delete_name , prms_lbl_delete_dob , prms_lbl_delete_admit_date , prms_lbl_delete_reason;

    DatabaseHelper delete_prescription;

    LinearLayout prms_delete_prescription_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prms_delete_prescription);

        prms_morning_radio_delete = findViewById(R.id.prms_morning_radio_delete);
        prms_day_radio_delete = findViewById(R.id.prms_day_radio_delete);
        prms_night_radio_delete = findViewById(R.id.prms_night_radio_delete);

        prms_txt_search_delete_prec = findViewById(R.id.prms_txt_search_delete_prec);

        prms_lbl_delete_id = findViewById(R.id.prms_lbl_delete_id);
        prms_lbl_delete_name  = findViewById(R.id.prms_lbl_delete_name);
        prms_lbl_delete_dob = findViewById(R.id.prms_lbl_delete_dob);
        prms_lbl_delete_admit_date = findViewById(R.id.prms_lbl_delete_admit_date);
        prms_lbl_delete_reason = findViewById(R.id.prms_lbl_delete_reason);

        delete_prescription = new DatabaseHelper(this);

        prms_delete_prescription_list = findViewById(R.id.prms_delete_prescription_list);

    }

    public void check_and_remove(View v){

        if(v.getId() == R.id.prms_morning_radio_delete){
            prms_morning_radio_delete.setChecked(true);
            prms_day_radio_delete.setChecked(false);
            prms_night_radio_delete.setChecked(false);
        }else if(v.getId() == R.id.prms_day_radio_delete){
            prms_morning_radio_delete.setChecked(false);
            prms_day_radio_delete.setChecked(true);
            prms_night_radio_delete.setChecked(false);
        }else if (v.getId() == R.id.prms_night_radio_delete){
            prms_morning_radio_delete.setChecked(false);
            prms_day_radio_delete.setChecked(false);
            prms_night_radio_delete.setChecked(true);
        }

    }

    public void search_to_delete(View v){

        prms_delete_prescription_list.removeAllViews();

        prms_lbl_delete_id.setText(null);
        prms_lbl_delete_name.setText(null);
        prms_lbl_delete_dob.setText(null);
        prms_lbl_delete_reason.setText(null);
        prms_lbl_delete_admit_date.setText(null);


        /*check if search box is empty*/
        if(!prms_txt_search_delete_prec.getText().toString().isEmpty()){

            /*check radio button*/
            if(prms_morning_radio_delete.isChecked() || prms_day_radio_delete.isChecked() || prms_night_radio_delete.isChecked()){

                String pid = prms_txt_search_delete_prec.getText().toString();

                String type = "unknown";
                if(prms_morning_radio_delete.isChecked()){
                    type = "morning";
                }else if(prms_day_radio_delete.isChecked()){
                    type = "day";
                }else if(prms_night_radio_delete.isChecked()){
                    type = "night";
                }

                /*check patient is avalable or not*/
                String patient_coll[] = {DatabaseTable.Patient.PATIENT_ID , DatabaseTable.Patient.PATIENT_FIRST_NAME , DatabaseTable.Patient.PATIENT_DOB , DatabaseTable.Patient.PATIENT_DATE_ADMITTED , DatabaseTable.Patient.PATIENT_REASON};
                String patient_where = DatabaseTable.Patient.PATIENT_ID + " = ? ";
                String patient_where_args [] = {pid};

                Cursor patient = delete_prescription.view(DatabaseTable.Patient.TABLE_NAME , patient_coll , patient_where , patient_where_args , null);

                if(patient.getCount() == 1){

                    while(patient.moveToNext()){

                        prms_lbl_delete_id.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_ID)));
                        prms_lbl_delete_name.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_FIRST_NAME)));
                        prms_lbl_delete_dob.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_DOB)));
                        prms_lbl_delete_admit_date.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_DATE_ADMITTED)));
                        prms_lbl_delete_reason.setText(patient.getString(patient.getColumnIndexOrThrow(DatabaseTable.Patient.PATIENT_REASON)));


                        String presc_col[] = {DatabaseTable.Prescription.PRESC_ID , DatabaseTable.Prescription.PRESC_IS_CURRENT};
                        String presc_where = DatabaseTable.Prescription.PATIENT_ID + " = ? and " + DatabaseTable.Prescription.PRESC_TYPE + " = ? ";
                        String presc_where_args[] = {pid, type};
                        String sort = DatabaseTable.Prescription.PRESC_IS_CURRENT + " DESC";
                        Cursor prescription = delete_prescription.view(DatabaseTable.Prescription.TABLE_NAME, presc_col, presc_where, presc_where_args, sort);

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
                                th_name.setWidth(prms_delete_prescription_list.getWidth() / 3);
                                th_name.setGravity(Gravity.CENTER);
                                th_name.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_name.setTextColor(getResources().getColor(R.color.white));

                                th_dose.setText("Dose");
                                th_dose.setWidth(prms_delete_prescription_list.getWidth() / 3);
                                th_dose.setGravity(Gravity.CENTER);
                                th_dose.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_dose.setTextColor(getResources().getColor(R.color.white));

                                th_beforeMeal.setText("Before Meal");
                                th_beforeMeal.setWidth(prms_delete_prescription_list.getWidth() / 3);
                                th_beforeMeal.setGravity(Gravity.CENTER);
                                th_beforeMeal.setBackgroundColor(getResources().getColor(R.color.com_color_cancel));
                                th_beforeMeal.setTextColor(getResources().getColor(R.color.white));



                                TextView presc_id = new TextView(this);
                                presc_id.setText("Prescription Id : " + prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID)));
                                presc_id.setWidth(prms_delete_prescription_list.getWidth()/3);

                                TextView current = new TextView(this);
                                current.setWidth(prms_delete_prescription_list.getWidth()/3);

                                Button delete_buttion = new Button(this);

                                /*if current this will add*/
                                if(Integer.parseInt(prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_IS_CURRENT))) == 1){
                                    current.setText("Current");
                                    current.setTextColor(getResources().getColor(R.color.prms_red_color));
                                    delete_buttion.setText("Cant Delete");
                                    delete_buttion.setBackgroundColor(getResources().getColor(R.color.prms_table_bg));
                                    delete_buttion.setTextColor(getResources().getColor(R.color.com_color_teal));
                                }else{
                                    current.setText("Not Current");
                                    /*delete button*/
                                    delete_buttion.setText("Delete This");
                                    delete_buttion.setWidth(prms_delete_prescription_list.getWidth()/3);
                                    delete_buttion.setTextColor(getResources().getColor(R.color.prms_red_color));
                                    delete_buttion.setBackgroundResource(R.drawable.prms_delete_button_bg);

                                    TableRow.LayoutParams buttion_lp = new TableRow.LayoutParams();
                                    buttion_lp.setMargins(5, 5 , 5 ,5);
                                    delete_buttion.setLayoutParams(buttion_lp);

                                    delete_buttion.setPadding(0,0,0,0);

                                    delete_buttion.setId(Integer.parseInt(prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID))));

                                    /*set onclick listner for dynamicly added buttons*/
                                    delete_buttion.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            delete_prescription(v);
                                        }
                                    });
                                }


                                about_prescription.addView(presc_id);
                                about_prescription.addView(current);
                                about_prescription.addView(delete_buttion);

                                row.addView(th_name);
                                row.addView(th_dose);
                                row.addView(th_beforeMeal);

                                table.addView(about_prescription);
                                table.addView(row);


                                /*Add drug Entities*/
                                String drug_col[] = {DatabaseTable.Drug.DRUG_NAME , DatabaseTable.Drug.DRUG_DOSE , DatabaseTable.Drug.DRUG_BEFORE_MEAL};
                                String drug_where = DatabaseTable.Drug.PRESCRIPTION_ID + " = ? ";
                                String drug_where_args[] = {prescription.getString(prescription.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID))};

                                Cursor drug_entity = delete_prescription.view(DatabaseTable.Drug.TABLE_NAME , drug_col , drug_where , drug_where_args , null);

                                /*if there is drug entity only add entiy*/
                                if(drug_entity.getCount() != 0){

                                    while(drug_entity.moveToNext()) {

                                        TableRow entity = new TableRow(this);

                                        TextView d_name = new TextView(this);
                                        TextView d_dose = new TextView(this);
                                        TextView d_Before_meal = new TextView(this);

                                        /*set values*/
                                        d_name.setText(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_NAME)));
                                        d_name.setWidth(prms_delete_prescription_list.getWidth()/3);
                                        d_name.setGravity(Gravity.CENTER);

                                        d_dose.setText(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_DOSE)));
                                        d_dose.setWidth(prms_delete_prescription_list.getWidth()/3);
                                        d_dose.setGravity(Gravity.CENTER);


                                        d_Before_meal.setText(drug_entity.getString(drug_entity.getColumnIndexOrThrow(DatabaseTable.Drug.DRUG_BEFORE_MEAL)));
                                        d_Before_meal.setWidth(prms_delete_prescription_list.getWidth()/3);
                                        d_Before_meal.setGravity(Gravity.CENTER);

                                        entity.addView(d_name);
                                        entity.addView(d_dose);
                                        entity.addView(d_Before_meal);

                                        table.addView(entity);


                                    }

                                }


                                prms_delete_prescription_list.addView(table);
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
            Toast search_empty = new Toast(this);
            search_empty.setText("Please Enter A Patient Id");
            search_empty.show();
        }


    }

    public void delete_prescription(View v){

        String presc_id = v.getId()+"";

        /*check prescription exists*/
        /*i user this method insted of sqlite exists method*/
        String presc_del_coll []= {DatabaseTable.Prescription.PRESC_ID};
        String presc_delete_where = DatabaseTable.Prescription.PRESC_ID + " = ? ";
        String presc_delete_where_args [] = {presc_id};

        Cursor presc = delete_prescription.view(DatabaseTable.Prescription.TABLE_NAME , presc_del_coll , presc_delete_where , presc_delete_where_args , null);

        if(presc.getCount() == 1){

            while(presc.moveToNext()) {

                String delete_drug_where = DatabaseTable.Drug.PRESCRIPTION_ID + " = ? ";
                String delete_drug_where_args[] = {presc.getString(presc.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID))};

                int deleted_drugs = delete_prescription.delete(DatabaseTable.Drug.TABLE_NAME , delete_drug_where , delete_drug_where_args);




                /*delete prescription from prescription table*/
                String delete_presc_where = DatabaseTable.Prescription.PRESC_ID + " = ? ";
                String delete_presc_where_args [] = {presc.getString(presc.getColumnIndexOrThrow(DatabaseTable.Prescription.PRESC_ID))};

                int deleted_presc = delete_prescription.delete(DatabaseTable.Prescription.TABLE_NAME , delete_presc_where , delete_presc_where_args);

                if(deleted_presc == 1){

                    Toast search_empty = new Toast(this);
                    search_empty.setText("Delete Success");
                    search_empty.show();

                    search_to_delete(v);

                }else{
                    Toast search_empty = new Toast(this);
                    search_empty.setText("Something Went Wrong");
                    search_empty.show();
                }


                break;
            }

        }else{
            Toast search_empty = new Toast(this);
            search_empty.setText("Error Prescription Id");
            search_empty.show();
        }


    }

}