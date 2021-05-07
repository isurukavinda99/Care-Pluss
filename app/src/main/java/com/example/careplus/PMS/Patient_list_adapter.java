package com.example.careplus.PMS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.example.careplus.R;

public class Patient_list_adapter extends ArrayAdapter {

    private Context context;
    private int layout;
    List<patientModel> patient_list;


    Patient_list_adapter(Context context, int layout, List<patientModel> patient_list){
        super(context, layout, patient_list);
        this.context = context;
        this.layout = layout;
        this.patient_list = patient_list;


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(layout,parent,false);

       // TextView patient_id, patient_fname, patient_lname, patient_bed_no, patient_dob, patient_date_admitted, patient_additional, patient_guardian, patient_contact,patient_address,patient_reason;

        TextView patient_id = itemView.findViewById(R.id.patient_basic_txtview_patient_id);
        TextView patient_fname = itemView.findViewById(R.id.patient_basic_txt_fname);
        TextView patient_lname = itemView.findViewById(R.id.patient_basic_txt_lname);
        TextView patient_bed_no = itemView.findViewById(R.id.patient_basic_txtview_patient_bed_no);
        TextView patient_dob = itemView.findViewById(R.id.patient_basic_txt_dob);
        TextView patient_date_admitted = itemView.findViewById(R.id.patient_basic_txt_date_admited);
        TextView patient_additional = itemView.findViewById(R.id.patient_basic_txt_additional);
        TextView patient_guardian = itemView.findViewById(R.id.patient_basic_txt_guardian_name);
        TextView patient_contact = itemView.findViewById(R.id.patient_basic_txt_contact);
        TextView patient_address = itemView.findViewById(R.id.patient_basic_txt_address);
        TextView patient_reason = itemView.findViewById(R.id.patient_basic_txt_reason);


        patientModel PM = patient_list.get(position);

        patient_id.setText(String.valueOf(PM.getP_id())); //patient id
        patient_fname.setText(PM.getP_fname()); //first name
        patient_lname.setText(PM.getP_lname()); //last name
        patient_bed_no.setText(PM.getP_bed_no()); //bed no
        patient_dob.setText(String.valueOf(PM.getP_dob())); //dob
        patient_date_admitted.setText(String.valueOf(PM.getP_date_admitted())); //date admitted
        patient_additional.setText(PM.getP_additional()); //additional
        patient_guardian.setText(PM.getP_guardian()); //guardian
        patient_contact.setText(PM.getP_contact()); //contact
        patient_address.setText(PM.getP_address()); //address
        patient_reason.setText(PM.getP_reason()); //reason

        return itemView;





    }



}
