package com.example.careplus.wms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class Wms_view_plans extends AppCompatActivity {
    DatabaseHelper db;
    EditText editExerciseId, editExerciseName, editExerciseArea, editExerciseSteps, editSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wms_view_plans);
        db = new DatabaseHelper(this);

        editExerciseId = findViewById(R.id.editText_ex_id3);
        editExerciseName = findViewById(R.id.editText_ex_name3);
        editExerciseArea = findViewById(R.id.editText_ex_area3);
        editExerciseSteps = findViewById(R.id.editText_ex_steps3);
        editSearch = findViewById(R.id.editText_Search1);

    }

    public void search(View v){
       String id  = editSearch.getText().toString();
        String coll_s[] = {"*"};
        String where_s = DatabaseTable.Exercise.EXE_ID + " = ? ";
        String where_arhs_s [] = { id };

        Cursor res = db.view(DatabaseTable.Exercise.TABLE_NAME , coll_s , where_s , where_arhs_s , null);

        if(res.getCount() > 0){

            while (res.moveToNext()){

                editExerciseId.setText(res.getString(res.getColumnIndexOrThrow(DatabaseTable.Exercise.EXE_ID)));
                editExerciseName.setText(res.getString(res.getColumnIndexOrThrow(DatabaseTable.Exercise.EXE_NAME)));
                editExerciseArea.setText(res.getString(res.getColumnIndexOrThrow(DatabaseTable.Exercise.EXE_AREA)));
                editExerciseSteps.setText(res.getString(res.getColumnIndexOrThrow(DatabaseTable.Exercise.EXE_STEPS)));

                break;
            }

        }else{
            //toast error
            Toast t = new Toast(this);
            t.setDuration(Toast.LENGTH_SHORT);
            t.setText("Error");
            t.show();
        }

    }

    public void update(View u) {

        String name= editExerciseName.getText().toString();
        String area = editExerciseArea.getText().toString();
        String steps = editExerciseSteps.getText().toString();
        String id = editExerciseId.getText().toString();

        ContentValues up = new ContentValues();
        up.put(DatabaseTable.Exercise.EXE_NAME , name);
        up.put(DatabaseTable.Exercise.EXE_AREA , area);
        up.put(DatabaseTable.Exercise.EXE_STEPS , steps);

        String where = DatabaseTable.Exercise.EXE_ID + " = ? ";
        String where_args [] = {id};

        int result = db.update(DatabaseTable.Exercise.TABLE_NAME , up , where , where_args);
        if(result > 0){
            //success message
            /*Toast patient = new Toast(this);
            patient.setDuration(Toast.LENGTH_SHORT);
            patient.setText("Update is Success");
            patient.show();*/
            System.out.println("success test");
            Toast.makeText(getApplicationContext(), "update is success", Toast.LENGTH_SHORT).show();
        }else{
            //error message
            Toast patient = new Toast(this);
            patient.setDuration(Toast.LENGTH_SHORT);
            patient.setText("Update not Success");
            patient.show();
        }

    }

    public void delete(View d){

        String id = editExerciseId.getText().toString();
        String del_where = DatabaseTable.Exercise.EXE_ID + " = ? ";
        String del_args [] = {id};

        int res = db.delete(DatabaseTable.Exercise.TABLE_NAME , del_where , del_args);

        if(res > 0){
            //success message
            Toast patient = new Toast(this);
            patient.setText("Delete is Success");
            patient.show();
            System.out.println("Success");
        }else{
            //error message
            Toast patient = new Toast(this);
            patient.setText("Delete not Success");
            patient.show();
        }

    }


}