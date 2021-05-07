package com.example.careplus.mms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.careplus.R;

import java.util.List;

public class Mms_MealPlanAdapter extends ArrayAdapter {

    //MMS_initialize variables
    private Context context;
    private int resource;
    List<Mms_mealPlanModel> mealPlans;


    //MMS_ArrayAdapter constructor
    public Mms_MealPlanAdapter(@NonNull Context context, int resource, @NonNull List<Mms_mealPlanModel> mealPlans) {
        super(context, resource, mealPlans);

        this.context= context;
        this.resource = resource;
        this.mealPlans = mealPlans;
    }


    //MMS_getView method
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);//MMS_instantiate a layoutInflater object
        View single_row = inflater.inflate(resource,parent,false);//MMS_instantiate a view object

        //MMS_get references
        TextView planName = single_row.findViewById(R.id.txtViewPlanName);
        TextView patientType = single_row.findViewById(R.id.txtViewPatientType);
        TextView day = single_row.findViewById(R.id.txtViewDay);
        TextView breakfast = single_row.findViewById(R.id.txtDisplayFoodB);
        TextView lunch = single_row.findViewById(R.id.txtDisplayFoodL);
        TextView dinner = single_row.findViewById(R.id.txtDisplayFoodD);

        TextView breakfastCard = single_row.findViewById(R.id.txtDisplayBreakfast);
        TextView lunchCard = single_row.findViewById(R.id.txtDisplayLunch);
        TextView dinnerCard = single_row.findViewById(R.id.txtDisplayDinner);


        Mms_mealPlanModel mealPlan = mealPlans.get(position);//MMS_get the position of List object- mealPlans[obj1,obj2,..]

        planName.setText(mealPlan.getPlanName());
        patientType.setText(mealPlan.getPatientType());
        day.setText(mealPlan.getDay());
        breakfast.setText(mealPlan.getBreakfast());
        lunch.setText(mealPlan.getLunch());
        dinner.setText(mealPlan.getDinner());

        return single_row;//MMS_return View object

    }//end getView method
}
