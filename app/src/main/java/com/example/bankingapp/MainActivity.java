package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    DbTool dbTool;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        dbTool = new DbTool(getApplicationContext());
        getSupportActionBar().hide();
        imageView.setImageResource(R.drawable.bank);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbTool.AddUsers();
    }

    public void viewAllCustomers(View view) {
        Intent intent = new Intent(MainActivity.this,ViewAllCustomers.class);
        startActivity(intent);
    }
}