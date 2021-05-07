package com.example.careplus.mms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.careplus.R;

public class Mms_supplementManager extends AppCompatActivity {

    //MMS_initialize objects
    EditText selectSupplement;
    TextView txtDisplayCode,txtDisplaySp1,txtDisplaySp2,txtDisplaySp3,txtDisplaySp4,txtDisplaySp5,txtDisplaySp6,displaySname,displayUseFor;
    Button  btnShow;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms_supplement_manager);

        this.setTitle("Care plus | Measure nutrients");//MMS_set title on app

        //MMS_get references
        selectSupplement = findViewById(R.id.selectSupplement);//EditText
        txtDisplayCode= findViewById(R.id.txtDispalyCode);
        txtDisplaySp1= findViewById(R.id.txtDisplaySp1);
        txtDisplaySp2= findViewById(R.id.txtDisplaySp2);
        txtDisplaySp3= findViewById(R.id.txtDisplaySp3);
        txtDisplaySp4= findViewById(R.id.txtDisplaySp4);
        txtDisplaySp5= findViewById(R.id.txtDisplaySp5);
        txtDisplaySp6= findViewById(R.id.txtDisplaySp6);
        btnShow= findViewById(R.id.btnShow);//Button
        displaySname= findViewById(R.id.displaySname);//TextView
        displayUseFor= findViewById(R.id.displayUseFor);//TextView



        //MMS_set an event listener on show button
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = selectSupplement.getText().toString();


                //MMS_check whether input field is empty(validation)
                if (TextUtils.isEmpty(string)){
                    selectSupplement.setError("Please enter supplement code from the table ");
                    selectSupplement.requestFocus();
                    return;
                }

                //MMS_get the user input
                String code = string;


                //MMS_call the functions to display values
                String displayName = showSupplementName(code);
                String displayUse = showUseFor(code);

                //MMS_set the display values
                displaySname.setText(displayName);
                displayUseFor.setText(displayUse);


            }
        });



    }// end of onCreate


    //MMS_method for show the supplement name
    public String showSupplementName(String s_code){


        //MMS_initialize variables
        String zinc = "sp001";
        String vitB = "sp002";
        String mVit = "sp003";
        String hetaS = "sp004";
        String hiCal = "sp005";
        String sstgn = "sp006";


             //MMS_checking conditions
               if(s_code.equals(zinc)) {

                   String name = "Zinc";
                   return name;
               }

               else if(s_code.equals(vitB)) {
                   String name = "Vitamin B";
                   return name;

               }

                else if(s_code.equals(mVit)) {
                    String name = "Multivitamin";
                    return name;

                }

                else if(s_code.equals(hetaS)) {
                    String name = "Heta-Starch";
                    return name;

                }
                else if(s_code.equals(hiCal)) {
                    String name = "Hi-Cal";
                    return name;

                }
                else if(s_code.equals(sstgn)) {
                    String name = "Sustagen";
                    return name;

                }
                else {
                   String error = "Error";
                   return error;
               }
    }


    //MMS_method for display, who can use the supplement
    public String showUseFor(String s_code){

        //MMS_initialize variables
        String zinc = "sp001";
        String vitB = "sp002";
        String mVit = "sp003";
        String hetaS = "sp004";
        String hiCal = "sp005";
        String sstgn = "sp006";


        //MMS_checking conditions
            if(s_code.equals(zinc)) {
                String use = "Use for: Disabled";
                return use;
            }

            if(s_code.equals(vitB)) {
                String use ="Use for: Disabled";
                return use;
            }

            if(s_code.equals(mVit)) {
                String use ="Use for: Disabled";
                return use;
            }

            if(s_code.equals(hetaS)) {
                String  use ="Use for: Mental illness";
                return use;
            }
            if(s_code.equals(hiCal)) {
                String use ="Use for: Burns, Stroke";
                return use;
            }
            if(s_code.equals(sstgn)) {
                String use ="Use for: Stroke";
                return use;
            }
            else {
                String error = "Invalid code";
                return error;
            }


    }


}// end class