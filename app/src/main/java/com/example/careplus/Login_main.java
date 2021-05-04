package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class Login_main extends AppCompatActivity {

    DatabaseHelper loginDatabase;
    EditText login_txt_user_name , login_txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        login_txt_user_name = findViewById(R.id.login_txt_user_name);
        login_txt_password = findViewById(R.id.login_txt_password);

        loginDatabase = new DatabaseHelper(this);
        /*create_users();*/

    }

    /*this method use to create users staticly*/
    public void create_users(){

        ContentValues user = new ContentValues();
        user.put(DatabaseTable.User.USER_NAME , "root");
        user.put(DatabaseTable.User.USER_PASSWORD , "testpassword");

        boolean result = loginDatabase.save(DatabaseTable.User.TABLE_NAME , user);

        if(result){
            System.out.println("User Create Success");
        }else{
            System.out.println("User Create Un success");
        }

    }


    public void login(View v){

        String userName = login_txt_user_name.getText().toString();
        String password = login_txt_password.getText().toString();

        /*check text fields are empty*/
        if(!userName.isEmpty() && !password.isEmpty()) {

            String userColl[] = {"*"};
            String where = DatabaseTable.User.USER_NAME + " like ? ";
            String whereArgs[] = {userName};

            Cursor user = loginDatabase.view(DatabaseTable.User.TABLE_NAME, userColl, where, whereArgs, null);

            if (user.getCount() == 1) {

                while(user.moveToNext()){
                    if (user.getString(user.getColumnIndexOrThrow(DatabaseTable.User.USER_PASSWORD)).equals(password)){

                        /*main activoty intent gose to hear*/
                        System.out.println("login success");

                    }else{
                        Toast login_password= new Toast(this);
                        login_password.setText("Password is in correct");
                        login_password.setDuration(Toast.LENGTH_SHORT);
                        login_password.show();
                    }
                }


            } else {
                Toast login_user_error = new Toast(this);
                login_user_error.setText("User Name Is Error");
                login_user_error.setDuration(Toast.LENGTH_SHORT);
                login_user_error.show();
            }
        }else{
            Toast login_empty = new Toast(this);
            login_empty.setText("Fill Required fields");
            login_empty.setDuration(Toast.LENGTH_SHORT);
            login_empty.show();
        }

    }
}