package com.example.careplus.wms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class Wms_view_reports extends AppCompatActivity {
    DatabaseHelper db;
    EditText editDate, editWeight, editMonth, editPatientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wms_view_reports);
        db = new DatabaseHelper(this);

        //button variables
        editDate = findViewById(R.id.editText_date2);
        editWeight = findViewById(R.id.editText_weight);
        editMonth = findViewById(R.id.editText_month);
        editPatientId = findViewById(R.id.editText_PatientID2);

    }
    public void update(View u) {

        System.out.println("update");

        String date = editDate.getText().toString();
        String weight = editWeight.getText().toString();
        String month = editMonth.getText().toString();
        int patientId = Integer.parseInt(editPatientId.getText().toString());

        //content values
        //update query
        ContentValues re = new ContentValues();
        re.put(DatabaseTable.WorkOutReport.REPORT_DATE, date);
        re.put(DatabaseTable.WorkOutReport.REPORT_WEIGHT, weight);
        re.put(DatabaseTable.WorkOutReport.REPORT_MONTH, month);
        re.put(DatabaseTable.WorkOutReport.PATIENT_ID, patientId);

        String where_update = DatabaseTable.WorkOutReport.PATIENT_ID + " = ? ";
        String where_args_update [] = {u.getId()+""};

        int result = db.update(DatabaseTable.WorkOutReport.TABLE_NAME , re , where_update , where_args_update);

        if(result == 1){
            Toast patient = new Toast(this);
            patient.setText("Update is Success");
            patient.show();
        }else {
            Toast patient = new Toast(this);
            patient.setText("Update not Success");
            patient.show();
        }

    }
    public void delete(View d){

        String where_delete = DatabaseTable.WorkOutReport.PATIENT_ID + " = ? ";
        String where_args_delete [] = {d.getId()+(DatabaseTable.WorkOutReport.PATIENT_ID)};

        int delete_id = db.delete(DatabaseTable.WorkOutReport.TABLE_NAME , where_delete , where_args_delete);
        if (delete_id == 1){
            Toast search_empty = new Toast(this);
            search_empty.setText("Delete Success");
            search_empty.show();
        }else {
            Toast search_empty = new Toast(this);
            search_empty.setText("Delete is not Success");
            search_empty.show();
        }
    }

}