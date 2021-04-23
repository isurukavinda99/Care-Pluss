package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.ConditionVariable;

import com.example.careplus.database.DatabaseHelper;
import com.example.careplus.database.DatabaseTable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper mydb = new DatabaseHelper(this);
    }
}