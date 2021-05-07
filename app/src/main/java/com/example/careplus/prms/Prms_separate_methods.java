package com.example.careplus.prms;

public class Prms_separate_methods {

    public String calculate_age(String date , String current_year){
        try {
            String birth_year = String.valueOf(date.charAt(6)) + String.valueOf(date.charAt(7)) + String.valueOf(date.charAt(8)) + String.valueOf(date.charAt(9));

            int b_year = Integer.parseInt(birth_year);
            int c_year = Integer.parseInt(current_year);

            if (b_year > c_year) {
                return "Birth Year Error";
            } else {
                return c_year - b_year + "";
            }
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return "-1";
        }catch (NumberFormatException e){
            e.printStackTrace();
            return "-2";
        }
    }

}
