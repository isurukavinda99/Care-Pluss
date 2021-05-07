package com.example.careplus.wms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class Wms_create_schedule extends AppCompatActivity {
    DatabaseHelper db;
    EditText editId, editExerciseName, editArea, editSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wms_create_schedule);
        db = new DatabaseHelper(this);

        editId = findViewById(R.id.editText_id);
        editExerciseName = findViewById(R.id.editText_name);
        editArea = findViewById(R.id.editText_area);
        editSteps = findViewById(R.id.editText_steps);

    }


    public void save(View v){


        System.out.println("in save");

        String name = editExerciseName.getText().toString();
        String area = editArea.getText().toString();
        String step = editSteps.getText().toString();
        int pid =Integer.parseInt( editId.getText().toString());

        ContentValues ex = new ContentValues();
        ex.put(DatabaseTable.Exercise.EXE_NAME , name);
        ex.put(DatabaseTable.Exercise.EXE_AREA , area);
        ex.put(DatabaseTable.Exercise.EXE_STEPS , step);
        ex.put(DatabaseTable.Exercise.PLAN_ID , pid);

        boolean reult = db.save(DatabaseTable.Exercise.TABLE_NAME , ex);

        if(reult){
            Toast t = new Toast(this);
            t.setDuration(Toast.LENGTH_SHORT);
            t.setText("success");
            t.show();
        }else{
            Toast t = new Toast(this);
            t.setDuration(Toast.LENGTH_SHORT);
            t.setText("error");
            t.show();
        }

        editExerciseName.setText(null);
        editArea.setText(null);
        editSteps.setText(null);



    }

    public void view(View v){

        String id = editId.getText().toString();

        String col [] = {"*"};
        String where = DatabaseTable.Exercise.PLAN_ID + " = ? ";
        String whereArgs [] = {id};



        Cursor result =  db.view(DatabaseTable.Exercise.TABLE_NAME , col , where , whereArgs , null);

        if(result.getCount() > 0){

            while(result.moveToNext()){
                editExerciseName.setText(result.getString(result.getColumnIndex(DatabaseTable.Exercise.EXE_NAME)));
                editArea.setText(result.getString(result.getColumnIndexOrThrow(DatabaseTable.Exercise.EXE_AREA)));
                editSteps.setText(result.getString(result.getColumnIndexOrThrow(DatabaseTable.Exercise.EXE_STEPS)));

                break;
            }

        }else{
            Toast t = new Toast(this);
            t.setDuration(Toast.LENGTH_SHORT);
            t.setText("Error");
            t.show();
        }

    }



}