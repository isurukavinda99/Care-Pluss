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

public class Clinical_patient_list_adapter extends ArrayAdapter {

    private Context context;
    private int layout;
    List<patientModel> Clinical_patient_list;


    Clinical_patient_list_adapter(Context context, int layout, List<patientModel> Clinical_patient_list){
        super(context,layout,Clinical_patient_list);

        this.context = context;
        this.layout = layout;
        this.Clinical_patient_list = Clinical_patient_list;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(layout,parent,false);

        TextView clinic_pID, clinic_pfName,clinic_plName,clinic_pBed;

        clinic_pID = itemView.findViewById(R.id.pms_single_clinical_txt_pid);
        clinic_pfName = itemView.findViewById(R.id.pms_single_clinical_patient_fname);
        clinic_pBed = itemView.findViewById(R.id.pms_single_clinical_patient_bed_no);
        clinic_plName = itemView.findViewById(R.id.pms_single_clinical_patient_lname);

        patientModel clinical_patient = Clinical_patient_list.get(position);

        clinic_pID.setText(String.valueOf(clinical_patient.getP_id()));
        clinic_pfName.setText(clinical_patient.getP_fname());
        clinic_plName.setText(clinical_patient.getP_lname());
        clinic_pBed.setText(clinical_patient.getP_bed_no());


        return itemView;








    }
}
