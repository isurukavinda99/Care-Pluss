package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

import static android.widget.Toast.LENGTH_SHORT;

public class Mms_addNewPlan extends AppCompatActivity {

    //MMS_initialize objects
    EditText editPlanName,editPatient, editDay,editBreakfast,editLunch, editDinner;
    Button btnSavePlan,btnCancelPlan;

    boolean allCorrect = false;//MMS_initialize a variable for validation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_add_new_plan);


        this.setTitle("Care plus | Add new meal plan");//MMS_set title on app



        //MMS_get references
        editPlanName =findViewById(R.id.editPlanName);//Plan name
        editPatient = findViewById(R.id.editPatientType);//patient type
        editDay=findViewById(R.id.editDay);//Day of the week
        editBreakfast=findViewById(R.id.editBreakfast);//breakfast
        editLunch=findViewById(R.id.editLunch);//lunch
        editDinner=findViewById(R.id.editDinner);//dinner
        btnSavePlan=findViewById(R.id.btnSavePlan);//save button
        btnCancelPlan=findViewById(R.id.btnCancelPlan);//cancel button




        //MMS_create an event listener on btnSavePlan
        btnSavePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allCorrect = formValidater();
                if (allCorrect == true){

                    ContentValues plan = new ContentValues();//MMS_instantiate ContentValues

                    plan.put(DatabaseTable.MealPlan.PLAN_NAME, editPlanName.getText().toString());
                    plan.put(DatabaseTable.MealPlan.PLAN_TYPE, editPatient.getText().toString());
                    plan.put(DatabaseTable.MealPlan.PLAN_DAY, editDay.getText().toString());
                    plan.put(DatabaseTable.MealPlan.BREAKFAST, editBreakfast.getText().toString());
                    plan.put(DatabaseTable.MealPlan.LUNCH, editLunch.getText().toString());
                    plan.put(DatabaseTable.MealPlan.DINNER, editDinner.getText().toString());

                    DatabaseHelper mms_db=new DatabaseHelper(getApplicationContext());//MMS_instantiate databaseHelper class
                    boolean result = mms_db.save(DatabaseTable.MealPlan.TABLE_NAME,plan);


                    //MMS_checking
                    if(result==false){
                        Toast.makeText(getApplicationContext(),"Data not added", LENGTH_SHORT).show();//MMS_show an error toast message
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Data added", LENGTH_SHORT).show();//MMS_show a success toast message

                        //MMS_create an intent
                        Intent intent_save = new Intent(getApplicationContext(),Mms_viewMealPlans.class);
                        startActivity(intent_save);
                    }

                }



            }

        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //MMS_create an event listener on btnCancelPlan
        btnCancelPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //MMS_create an intent
                Intent intent_cancel = new Intent(getApplicationContext(),Mms_dashboard.class);
                startActivity(intent_cancel);
            }

        });


    }//end onCreate

    //MMS_form validation
    private boolean formValidater(){

        if (editPlanName.length() == 0){
            editPlanName.setError("Please enter the plan name");
            editPlanName.requestFocus();
            return false;
        }
        if (editPatient.length() == 0){
            editPatient.setError("Please enter the day");
            editPatient.requestFocus();
            return false;
        }
        if (editDay.length() == 0){
            editDay.setError("Please enter a valid patient type");
            editDay.requestFocus();
            return false;
        }
        if (editBreakfast.length() == 0){
            editBreakfast.setError("Please enter breakfast");
            editBreakfast.requestFocus();
            return false;
        }
        if (editLunch.length() == 0){
            editLunch.setError("Please enter lunch");
            editLunch.requestFocus();
            return false;
        }
        if (editDinner.length() == 0){
            editDinner.setError("Please enter dinner");
            editDinner.requestFocus();
            return false;
        }


        return  true;
    }







}// end class