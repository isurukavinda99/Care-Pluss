package com.example.careplus.PMS;

import androidx.appcompat.app.AppCompatActivity;
/*

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.widget.Toast;

import android.database.Cursor;


*/

import android.os.Bundle;

import com.example.careplus.R;


public class pms_patient_basic_list extends AppCompatActivity {

    /*
    RecyclerView recyclerView;

    DatabaseHelper db;
    ArrayList<String> p_id, p_fname, p_lname, p_bed_no, p_dob, p_date_admitted, p_additional, p_guardian, p_contact,p_address,p_reason;

    customAdapter adapter;


    DatabaseHelper databaseHelper;


     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_patient_basic_list);

        //matching to xml
/*
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_plist);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        p_id = new ArrayList<>();
        p_fname = new ArrayList<>();
        p_lname = new ArrayList<>();
        p_bed_no = new ArrayList<>();
        p_dob = new ArrayList<>();
        p_date_admitted = new ArrayList<>();
        p_additional = new ArrayList<>();
        p_guardian = new ArrayList<>();
        p_contact = new ArrayList<>();
        p_reason = new ArrayList<>();
        p_address = new ArrayList<>();


        displayPatientList();

        adapter = new customAdapter(pms_patient_basic_list.this,p_id, p_fname, p_lname, p_bed_no, p_dob, p_date_admitted, p_additional, p_guardian, p_contact,p_address,p_reason);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(pms_patient_basic_list.this));

*/
    }

  /*
    void displayPatientList() {



        Cursor cursor = databaseHelper.displayAllData(DatabaseTable.Patient.TABLE_NAME);

            if(cursor.getCount() == 0){

                Toast.makeText(this,"No data available in this moment",Toast.LENGTH_SHORT).show();

            }

            else{

                while (cursor.moveToNext()){


                    p_id.add(cursor.getString(0));
                    p_fname.add(cursor.getString(1));
                    p_lname.add(cursor.getString(2));
                    p_dob.add(cursor.getString(3));
                    p_guardian.add(cursor.getString(4));
                    p_address.add(cursor.getString(5));
                    p_contact.add(cursor.getString(6));
                    p_reason.add(cursor.getString(7));
                    p_bed_no.add(cursor.getString(8));
                    p_date_admitted.add(cursor.getString(9));
                    p_additional.add(cursor.getString(10));


                }


            }









    }


   */

}