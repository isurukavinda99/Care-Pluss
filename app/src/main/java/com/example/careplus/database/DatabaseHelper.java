package com.example.careplus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.careplus.PMS.clinicalModel;
import com.example.careplus.PMS.patientModel;

import com.example.careplus.mms.Mms_mealPlanModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "carePlusInfo.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = getWritableDatabase();
        onCreate(db);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {



            /*exe crete pms table*/
            db.execSQL(DatabaseTable.Patient.CREATE_TABLE_STRING);

           // db.execSQL(DatabaseTable.Guardian.CREATE_TABLE_STRING);  //stopped executing creation of guardian table

            db.execSQL(DatabaseTable.BeaHeadCard.CREATE_TABLE_STRING);


            /*exe create mms tables*/

            db.execSQL(DatabaseTable.MealPlan.CREATE_TABLE_STRING);

            db.execSQL(DatabaseTable.Portion.CREATE_TABLE_STRING);

            db.execSQL(DatabaseTable.PatentHasMealPlan.CREATE_TABLE_STRING);




            /*exe create wms tables*/
            db.execSQL(DatabaseTable.WorkoutPlan.CREATE_TABLE_STRING);

            db.execSQL(DatabaseTable.Exercise.CREATE_TABLE_STRING);

            db.execSQL(DatabaseTable.WorkOutReport.CREATE_TABLE_STRING);


            /*exe create prms tables*/
            db.execSQL(DatabaseTable.Prescription.CREATE_TABLE_STRING);

            db.execSQL(DatabaseTable.Drug.CREATE_TABLE_STRING);

            /*create table user 2021 05 04*/
            db.execSQL(DatabaseTable.User.CREATE_TABLE_STRING);

        }catch(Exception e){
            System.out.println("############ database error");
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean save(String TABLE_NAME , ContentValues contentValues){
        SQLiteDatabase db = getWritableDatabase();

        long result = db.insert(TABLE_NAME , null , contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor view(String TABLE_NAME , String [] columns , String where , String whereArgs [] , String sortingOrder){

        /*
            * table name must be existing table name or names
            * columns is array ["col_name1" , "col_name2"...] if you want to select all then simply ["*"]
            * where close ex - "where id = 1" convert this to "id ?" !important dont put 'where' and values inside where closes
            * put null as the parameter when there is no any selection
            * whereArgs this contains values that map to '?' in whew close  ! please putt null when there is no selection args
            * sorting order look MAD Lab sheet this same as its sorting order
            * this method return cursor object
         */

        SQLiteDatabase db = getWritableDatabase();
        Cursor result = db.query(
                TABLE_NAME,
                columns,
                where,
                whereArgs,
                null,
                null,
                sortingOrder
        );

        return result;
    }

    public int update(String TABLE_NAME , ContentValues values , String where , String whereArgs[]){

        /*
            * Table name
            * values -> this is object of ContentValue , put column name and new value as key and value
            * where -> "where username like 10" this should be "username like ?" , dont put 'where' and values inside hear
            * whereArgs -> whereArgs this contains values that map to '?' in whew close
            * return effected row count
         */

        SQLiteDatabase db = getWritableDatabase();

        int effected_rows = 0;

        try{
             effected_rows = db.update(
                    TABLE_NAME ,
                    values,
                    where,
                    whereArgs
            );

        }catch (Exception e){

        }


        return effected_rows;
    }

    public int delete(String TABLE_NAME , String where , String whereArgs[]){

        /*
            *where -> "where username like 10" this should be "username like ?" , dont put 'where' and values inside hear
            *whereArgs -> whereArgs this contains values that map to '?' in whew close
            *return effected row count
         */

        SQLiteDatabase db = getWritableDatabase();
        int effected_rows = db.delete(TABLE_NAME , where , whereArgs);

        return effected_rows;

    }

    //creating display data method for patient

  /*  public Cursor displayAllData(String TABLE_NAME){

        String viewQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = null;

        if(db != null){

            result = db.rawQuery(viewQuery, null);

        }

        return result;

    }

   */

    //code segmaant to get patient count

    public int countPatients(String TABLE_NAME){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();

    }

    //code segmant to view patients in ListView

    public List<patientModel> displayPatientInfo(String TABLE_NAME){

        List<patientModel> pList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){


            do{

                patientModel pModel = new patientModel();

                pModel.setP_id(cursor.getInt(0));
                pModel.setP_fname(cursor.getString(1));
                pModel.setP_lname(cursor.getString(2));
                pModel.setP_dob(cursor.getString(3));
                pModel.setP_guardian(cursor.getString(4));
                pModel.setP_address(cursor.getString(5));
                pModel.setP_contact(cursor.getString(6));
                pModel.setP_reason(cursor.getString(7));
                pModel.setP_bed_no(cursor.getString(8));
                pModel.setP_date_admitted(cursor.getString(9));
                pModel.setP_additional(cursor.getString(10));


                //adding values to ArrayList
                pList.add(pModel);


            } while (cursor.moveToNext());




        }

        return pList;


    }



    //delete patient method

    public void deletePatientInfo(String TABLE_NAME, int patient_id){

        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, DatabaseTable.Patient.PATIENT_ID +" =?", new String[]{String.valueOf(patient_id)});

        db.close();

    }//end of deletePatientInfo


    // get a row from database

    public patientModel getOneRow(int p_id,String TABLE_NAME){

        SQLiteDatabase db = getWritableDatabase();

        Cursor row_data = db.query(TABLE_NAME, new String[]{DatabaseTable.Patient.PATIENT_ID,DatabaseTable.Patient.PATIENT_FIRST_NAME, DatabaseTable.Patient.PATIENT_LAST_NAME,DatabaseTable.Patient.PATIENT_BED_NO,DatabaseTable.Patient.PATIENT_ADDITIONAL_INFO,DatabaseTable.Patient.GUARDIAN_NAME,DatabaseTable.Patient.GUARDIAN_CONTACT_NUMBER,DatabaseTable.Patient.GUARDIAN_ADDRESS,DatabaseTable.Patient.PATIENT_REASON,DatabaseTable.Patient.PATIENT_DOB,DatabaseTable.Patient.PATIENT_DATE_ADMITTED},DatabaseTable.Patient.PATIENT_ID + "= ?",new String[]{String.valueOf(p_id)},null,null,null);

        //object from model class
        patientModel patient_model;






        if(row_data != null){

            row_data.moveToFirst();




            patient_model = new patientModel(
                    row_data.getInt(0), //PATIENT_ID
                    row_data.getString(1), //PATIENT_FIRST_NAME
                    row_data.getString(2), //PATIENT_LAST_NAME
                    row_data.getString(3), //PATIENT_bed_no
                    row_data.getString(4), //additional
                    row_data.getString(5), //guradian
                    row_data.getString(6), //contact
                    row_data.getString(7), //address
                    row_data.getString(8), //reason
                    row_data.getString(9), //dob
                    row_data.getString(10) //data admitted


                    );

            return patient_model;


        }


        return null;




    }//end of get single row


    //update method

    public int update_patient_data(patientModel patient){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues update_patient = new ContentValues();

        update_patient.put(DatabaseTable.Patient.PATIENT_FIRST_NAME, patient.getP_fname());
        update_patient.put(DatabaseTable.Patient.PATIENT_LAST_NAME, patient.getP_lname());
        update_patient.put(DatabaseTable.Patient.PATIENT_DOB, patient.getP_dob());
        update_patient.put(DatabaseTable.Patient.PATIENT_BED_NO, patient.getP_bed_no());
        update_patient.put(DatabaseTable.Patient.GUARDIAN_NAME, patient.getP_guardian());
        update_patient.put(DatabaseTable.Patient.GUARDIAN_CONTACT_NUMBER, patient.getP_contact());
        update_patient.put(DatabaseTable.Patient.GUARDIAN_ADDRESS, patient.getP_address());
        update_patient.put(DatabaseTable.Patient.PATIENT_REASON, patient.getP_reason());
        update_patient.put(DatabaseTable.Patient.PATIENT_DATE_ADMITTED, patient.getP_date_admitted());
        update_patient.put(DatabaseTable.Patient.PATIENT_ADDITIONAL_INFO, patient.getP_additional());

        int result = db.update(DatabaseTable.Patient.TABLE_NAME, update_patient, DatabaseTable.Patient.PATIENT_ID +" =?",new String[]{String.valueOf(patient.getP_id())});

        return result;


    }//end of patient update



    //display clinical patient list
    public List<patientModel> displayClinicalPatientInfo(String TABLE_NAME){

        List<patientModel> CpList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){


            do{

                patientModel CpModel = new patientModel();

                CpModel.setP_id(cursor.getInt(0));
                CpModel.setP_fname(cursor.getString(1));
                CpModel.setP_lname(cursor.getString(2));
                //pModel.setP_dob(cursor.getLong(3));
               //pModel.setP_guardian(cursor.getString(4));
                //pModel.setP_address(cursor.getString(5));
               // pModel.setP_contact(cursor.getString(6));
                //pModel.setP_reason(cursor.getString(7));
                CpModel.setP_bed_no(cursor.getString(8));
               // pModel.setP_date_admitted(cursor.getLong(9));
               // pModel.setP_additional(cursor.getString(10));


                //adding values to ArrayList
                CpList.add(CpModel);


            } while (cursor.moveToNext());




        }

        return CpList;


    }//end of display clinical patient list


    public List<clinicalModel> displayClinicalRecordInfo(int p_id){

        List<clinicalModel> clinic_record_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+ DatabaseTable.BeaHeadCard.TABLE_NAME + " WHERE " + DatabaseTable.BeaHeadCard.PATIENT_ID_FK + "='" + p_id + "'";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){


            do{

                //patientModel CpModel = new patientModel();

                clinicalModel cRmodel = new clinicalModel();


                cRmodel.setRecord_id(cursor.getInt(0));
                cRmodel.setRecord_date(cursor.getLong(1));
                cRmodel.setBlood_pressure(Double.parseDouble(cursor.getString(2)));
                cRmodel.setBlood_glucose(Double.parseDouble(cursor.getString(3)));
                cRmodel.setDiagnosis(cursor.getString(4));

                clinic_record_list.add(cRmodel);




                //CpModel.setP_id(cursor.getInt(0));
                //CpModel.setP_fname(cursor.getString(1));
                //CpModel.setP_lname(cursor.getString(2));
                //pModel.setP_dob(cursor.getLong(3));
                //pModel.setP_guardian(cursor.getString(4));
                //pModel.setP_address(cursor.getString(5));
                // pModel.setP_contact(cursor.getString(6));
                //pModel.setP_reason(cursor.getString(7));
                //CpModel.setP_bed_no(cursor.getString(8));
                // pModel.setP_date_admitted(cursor.getLong(9));
                // pModel.setP_additional(cursor.getString(10));


                //adding values to ArrayList
                //CpList.add(CpModel);


            } while (cursor.moveToNext());




        }

        return clinic_record_list;


    }//end

    // get a row from database

    public clinicalModel getOneRowfromBedHead(int card_id){

        SQLiteDatabase db = getWritableDatabase();

        Cursor row_data = db.query(DatabaseTable.BeaHeadCard.TABLE_NAME, new String[]{DatabaseTable.BeaHeadCard.CARD_ID,DatabaseTable.BeaHeadCard.CARD_DATE,DatabaseTable.BeaHeadCard.CARD_BLOOD_PRESSURE, DatabaseTable.BeaHeadCard.CARD_SUGAR_LEVEL, DatabaseTable.BeaHeadCard.CARD_DIAGNOSIS,DatabaseTable.BeaHeadCard.PATIENT_ID_FK},DatabaseTable.BeaHeadCard.CARD_ID + "= ?",new String[]{String.valueOf(card_id)},null,null,null);

        //object from model class
        clinicalModel clinical_model;






        if(row_data != null){

            row_data.moveToFirst();



            //int clinical_patient_id, int record_id, long record_date, String diagnosis, double blood_pressure, double blood_glucose

            clinical_model = new clinicalModel(
                    row_data.getInt(0), //recordid
                    row_data.getInt(5), //PATIENT_ID
                    row_data.getLong(1), //record date
                    row_data.getString(4), //diagnosis
                    row_data.getDouble(2), //blood_pressure
                    row_data.getDouble(3) //blood_glucose

                    /*row_data.getString(2), //PATIENT_LAST_NAME
                    row_data.getString(3), //PATIENT_bed_no
                    row_data.getString(4), //additional
                    row_data.getString(5), //guradian
                    row_data.getString(6), //contact
                    row_data.getString(7), //address
                    row_data.getString(8), //reason
                    row_data.getLong(9), //dob
                    row_data.getLong(10) //data admitted


                     */

            );

            return clinical_model;


        }


        return null;




    }//end of get single row



    //clinical update start
    public int update_clinical_data(clinicalModel clinic){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues update_clinical = new ContentValues();

        /*
        update_patient.put(DatabaseTable.Patient.PATIENT_FIRST_NAME, patient.getP_fname());
        update_patient.put(DatabaseTable.Patient.PATIENT_LAST_NAME, patient.getP_lname());
        update_patient.put(DatabaseTable.Patient.PATIENT_DOB, patient.getP_dob());
        update_patient.put(DatabaseTable.Patient.PATIENT_BED_NO, patient.getP_bed_no());
        update_patient.put(DatabaseTable.Patient.GUARDIAN_NAME, patient.getP_guardian());
        update_patient.put(DatabaseTable.Patient.GUARDIAN_CONTACT_NUMBER, patient.getP_contact());
        update_patient.put(DatabaseTable.Patient.GUARDIAN_ADDRESS, patient.getP_address());
        update_patient.put(DatabaseTable.Patient.PATIENT_REASON, patient.getP_reason());
        update_patient.put(DatabaseTable.Patient.PATIENT_DATE_ADMITTED, patient.getP_date_admitted());
        update_patient.put(DatabaseTable.Patient.PATIENT_ADDITIONAL_INFO, patient.getP_additional());


         */

        update_clinical.put(DatabaseTable.BeaHeadCard.CARD_BLOOD_PRESSURE,clinic.getBlood_pressure());
        update_clinical.put(DatabaseTable.BeaHeadCard.CARD_SUGAR_LEVEL, clinic.getBlood_glucose());
        update_clinical.put(DatabaseTable.BeaHeadCard.CARD_DIAGNOSIS, clinic.getDiagnosis());


        int result = db.update(DatabaseTable.BeaHeadCard.TABLE_NAME, update_clinical, DatabaseTable.BeaHeadCard.CARD_ID +" =?",new String[]{String.valueOf(clinic.getRecord_id())});

        return result;


    }//end of patient update



    //MMS_method for view all meal plans
    public List<Mms_mealPlanModel> viewMealPlans() {

        List<Mms_mealPlanModel> mealPlans = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseTable.MealPlan.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Mms_mealPlanModel mealPlan = new Mms_mealPlanModel();
                mealPlan.setId(cursor.getInt(0));
                mealPlan.setPlanName(cursor.getString(1));
                mealPlan.setPatientType(cursor.getString(2));
                mealPlan.setDay(cursor.getString(3));
                mealPlan.setBreakfast(cursor.getString(4));
                mealPlan.setLunch(cursor.getString(5));
                mealPlan.setDinner(cursor.getString(6));

                mealPlans.add(mealPlan);

            } while (cursor.moveToNext());


        }
        return mealPlans;
    }// end getMealPlan method

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //MMS_method for get one plan
    public Mms_mealPlanModel getMealPlan(int id){

        SQLiteDatabase db = getWritableDatabase();

        String[] columns= {DatabaseTable.MealPlan.PLAN_ID,DatabaseTable.MealPlan.PLAN_NAME,DatabaseTable.MealPlan.PLAN_TYPE,
                DatabaseTable.MealPlan.PLAN_DAY,
                DatabaseTable.MealPlan.BREAKFAST,
                DatabaseTable.MealPlan.LUNCH,
                DatabaseTable.MealPlan.DINNER};

        String[] id_column  = {String.valueOf(id)};//MMS_selection args

        Cursor cursor = db.query(DatabaseTable.MealPlan.TABLE_NAME,columns,DatabaseTable.MealPlan.PLAN_ID + "= ?",id_column,null,null,null);

        Mms_mealPlanModel mealPlanModel;//instantiate Mms_mealPlanModel class

        if (cursor != null){

            cursor.moveToFirst();
            mealPlanModel = new Mms_mealPlanModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );

            return mealPlanModel;//return object
        }

        return  null;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int updateMealPlan(Mms_mealPlanModel mealPlanModel){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues plan = new ContentValues();//MMS_instantiate ContentValues

        plan.put(DatabaseTable.MealPlan.PLAN_NAME,mealPlanModel.getPlanName());
        plan.put(DatabaseTable.MealPlan.PLAN_TYPE, mealPlanModel.getPatientType());
        plan.put(DatabaseTable.MealPlan.PLAN_DAY, mealPlanModel.getDay());
        plan.put(DatabaseTable.MealPlan.BREAKFAST, mealPlanModel.getBreakfast());
        plan.put(DatabaseTable.MealPlan.LUNCH, mealPlanModel.getLunch());
        plan.put(DatabaseTable.MealPlan.DINNER, mealPlanModel.getDinner());

        int status = db.update(DatabaseTable.MealPlan.TABLE_NAME,plan,DatabaseTable.MealPlan.PLAN_ID + "=?", new String[]{String.valueOf(mealPlanModel.getId())});

        db.close();

        return  status;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteMealPlan(int id){

        SQLiteDatabase db = getWritableDatabase();

        db.delete(DatabaseTable.MealPlan.TABLE_NAME,DatabaseTable.MealPlan.PLAN_ID + "=?",new String[]{String.valueOf(id)});

        db.close();
    }










}
