package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerActivity extends AppCompatActivity {
    int _id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_customer);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        _id = intent.getIntExtra("_id",0);

        Bundle bundle = new Bundle();
        bundle.putInt("_id",_id);
        ViewOneCustomerFragment customFrag = new  ViewOneCustomerFragment();
        customFrag.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(android.R.id.content,customFrag);
        ft.commit();
    }

    public void SelectReceiverCustomer(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("_id",_id);
        TransferMoneyFragment frag = new TransferMoneyFragment();
        frag.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(android.R.id.content,frag);
        ft.commit();
    }
}