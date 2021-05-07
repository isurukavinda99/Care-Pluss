package com.example.careplus.PMS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careplus.R;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder> {

    Context context;
    ArrayList p_id, p_fname, p_lname, p_bed_no, p_dob, p_date_admitted, p_additional, p_guardian, p_contact,p_address,p_reason;



    customAdapter(
            Context context,
            ArrayList p_id,
            ArrayList p_fname,
            ArrayList p_lname,
            ArrayList p_bed_no,
            ArrayList p_dob,
            ArrayList p_date_admitted,
            ArrayList p_additional,
            ArrayList p_guardian,
            ArrayList p_contact,
            ArrayList p_address,
            ArrayList p_reason){


        this.context = context;
        this.p_id = p_id;
        this.p_fname = p_fname;
        this.p_lname = p_lname;
        this.p_dob = p_dob;
        this.p_guardian = p_guardian;
        this.p_address = p_address;
        this.p_contact = p_contact;
        this.p_reason = p_reason;
        this.p_bed_no = p_bed_no;
        this.p_date_admitted = p_date_admitted;
        this.p_additional = p_additional;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_pms_patient_basic_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.patient_id.setText(String.valueOf(p_id.get(position)));
        holder.patient_fname.setText(String.valueOf(p_fname.get(position)));
        holder.patient_lname.setText(String.valueOf(p_lname.get(position)));
        holder.patient_dob.setText(String.valueOf(p_dob.get(position)));
        holder.patient_guardian.setText(String.valueOf(p_guardian.get(position)));
        holder.patient_address.setText(String.valueOf(p_address.get(position)));
        holder.patient_contact.setText(String.valueOf(p_contact.get(position)));
        holder.patient_reason.setText(String.valueOf(p_reason.get(position)));
        holder.patient_bed_no.setText(String.valueOf(p_bed_no.get(position)));
        holder.patient_date_admitted.setText(String.valueOf(p_date_admitted.get(position)));
        holder.patient_additional.setText(String.valueOf(p_additional.get(position)));



    }

    @Override
    public int getItemCount() {
        return p_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView patient_id, patient_fname, patient_lname, patient_bed_no, patient_dob, patient_date_admitted, patient_additional, patient_guardian, patient_contact,patient_address,patient_reason;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            patient_id = itemView.findViewById(R.id.patient_basic_txtview_patient_id);
            patient_fname = itemView.findViewById(R.id.patient_basic_txt_fname);
            patient_lname = itemView.findViewById(R.id.patient_basic_txt_lname);
            patient_bed_no = itemView.findViewById(R.id.patient_basic_txtview_patient_bed_no);
            patient_dob = itemView.findViewById(R.id.patient_basic_txt_dob);
            patient_date_admitted = itemView.findViewById(R.id.patient_basic_txt_date_admited);
            patient_additional = itemView.findViewById(R.id.patient_basic_txt_additional);
            patient_guardian = itemView.findViewById(R.id.patient_basic_txt_guardian_name);
            patient_contact = itemView.findViewById(R.id.patient_basic_txt_contact);
            patient_address = itemView.findViewById(R.id.patient_basic_txt_address);
            patient_reason = itemView.findViewById(R.id.patient_basic_txt_reason);



        }
    }
}
