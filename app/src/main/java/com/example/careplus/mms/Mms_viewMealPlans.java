package com.example.careplus.mms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.careplus.R;
import com.example.careplus.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Mms_viewMealPlans extends AppCompatActivity {

    //MMS_initialize objects
    ListView listViewDisplay;
    //SearchView searchView;
    Context context;


    private List<Mms_mealPlanModel> mealPlans;//MMS_create a new List variable



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_view_meal_plans);

        this.setTitle("Care plus | View meal plan");

        DatabaseHelper mms_db = new DatabaseHelper(this);//MMS_instantiate DatabaseHelper class

        //MMS_get references
        listViewDisplay = findViewById(R.id.listViewDisplay);
        context = this;




        ////////
        mealPlans = new ArrayList<>();//MMS_assign to a new arrayList object
        mealPlans = mms_db.viewMealPlans();//MMS_call the viewMealPlans method

        Mms_MealPlanAdapter adapter = new Mms_MealPlanAdapter(getApplicationContext(), R.layout.mms_single_plan_view, mealPlans);
        listViewDisplay.setAdapter(adapter);




        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //MMS_create an alert dialog box
        listViewDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Mms_mealPlanModel mealPlanModel = mealPlans.get(position);
                AlertDialog.Builder builder_meal = new AlertDialog.Builder(Mms_viewMealPlans.this);

                builder_meal.setTitle(mealPlanModel.getPlanName());//MMS_set plan name as the message title
                builder_meal.setMessage("Do you want update or delete ?");//MMS_set message text


                //MMS_set a button for delete
                builder_meal.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mms_db.deleteMealPlan(mealPlanModel.getId());

                        Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();//MMS-toast message for conform deletion


                        //MMS_create an intent
                        Intent intent_delete = new Intent(getApplicationContext(),Mms_viewMealPlans.class);
                        startActivity(intent_delete);

                    }
                });

                //MMS_set a button for update
                builder_meal.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //MMS_create an intent
                        Intent intent_update = new Intent(getApplicationContext(),Mms_updateEntry.class);
                        intent_update.putExtra("id",String.valueOf(mealPlanModel.getId()));
                        startActivity(intent_update);



                    }
                });


                builder_meal.show();//MMS_show alert dialog box

            }

        });





    }//end onCreate
}