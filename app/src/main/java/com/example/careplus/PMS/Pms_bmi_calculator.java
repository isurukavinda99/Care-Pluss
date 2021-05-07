package com.example.careplus.PMS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.careplus.R;

public class Pms_bmi_calculator extends AppCompatActivity {

    TextView numeric_bmi;
    EditText  height, weight;
    Button calculate;

    boolean everythingChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pms_bmi_calculator);

        this.setTitle("Care Plus | BMI Calculator");

        height = (EditText) findViewById(R.id.bmi_calculator_height);
        weight = (EditText) findViewById(R.id.bmi_calculator_weight);
        calculate = findViewById(R.id.btn_calculate_bmi);
        numeric_bmi = findViewById(R.id.bmi_result_numeric);



        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                everythingChecked = validation();

                if(validation()){

                    String height_input = height.getText().toString();
                    String weight_input = weight.getText().toString();

                    double p_height = Double.parseDouble(String.valueOf(height_input));
                    double p_weight = Double.parseDouble(String.valueOf(weight_input));


                    double bmi = calculateBMI(p_height,p_weight);



                    //double rounded_bmi = Math.round(bmi*10.0)/10.0;


                    String bmi_type = findBmiCategory(bmi);

                    numeric_bmi.setText(String.valueOf(bmi) + " " + bmi_type);

                }





            }
        });




    }


    public double calculateBMI(double height, double weight){

        double bmi =  (weight / (0.0001 * ( height * height )));

        return Math.round(bmi*10.0)/10.0;



    }

    //bmi category finder
    public String findBmiCategory(double bmi){

        if(bmi < 18.5){

            String result = "Underweight";
            return result;

        }

        else if(bmi >= 18.5 || bmi < 25){

            String result = "Healthy";
            return result;

        }

        else if(bmi >= 25 || bmi < 30 ){

            String result = "Overweight";
            return result;

        }

        else{

            String result = "Obese";
            return result;

        }




    }// end of bmi category finder


    private boolean validation(){

        if(height.length() == 0){

            height.setError("Height Cannot be Empty");
            return false;

        }

        else if(height.length() > 3 || height.length() < 2){

            height.setError("Invalid Height");
            return false;
        }

        if(weight.length() == 0){

            weight.setError("weight Cannot be Empty");
            return false;

        }

        else if(weight.length() > 3){

            weight.setError("Invalid weight");
            return false;
        }

        return true;


    }





}