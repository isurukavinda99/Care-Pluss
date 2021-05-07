package com.example.careplus.PMS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.careplus.R;

import java.util.List;

public class clinical_record_adapter extends ArrayAdapter {

    private Context context;
    private int layout;
    List<clinicalModel> clinical_list;

    clinical_record_adapter(Context context, int layout, List<clinicalModel> clinical_list ){

        super(context,layout,clinical_list);

        this.context = context;
        this.layout = layout;
        this.clinical_list = clinical_list;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(layout,parent,false);

        TextView clinic_record_Id, clinic_record_date, clinic_record_pressure, clinic_record_sugar, clinic_record_diag;

        clinic_record_Id = itemView.findViewById(R.id.single_clinical_record_record_id);
        clinic_record_date = itemView.findViewById(R.id.singli_clinical_record_date);
        clinic_record_pressure = itemView.findViewById(R.id.single_clinical_record_BP);
        clinic_record_sugar = itemView.findViewById(R.id.single_clinical_record_sugar);
        clinic_record_diag = itemView.findViewById(R.id.single_clinical_record_diagnosis);

        clinicalModel clinical_records = clinical_list.get(position);

        clinic_record_Id.setText(String.valueOf(clinical_records.getRecord_id()));
        clinic_record_date.setText(String.valueOf(clinical_records.getRecord_date()));
        clinic_record_pressure.setText(String.valueOf(clinical_records.getBlood_pressure()));
        clinic_record_sugar.setText(String.valueOf(clinical_records.getBlood_glucose()));
        clinic_record_diag.setText(clinical_records.getDiagnosis());


        return itemView;




    }
}
