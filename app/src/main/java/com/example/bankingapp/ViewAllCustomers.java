package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllCustomers extends AppCompatActivity {
    DbTool  dbTool;

    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_customers);
        getSupportActionBar().hide();
        dbTool = new DbTool(getApplicationContext());
        recyclerView = findViewById(R.id.userrecyclerview);

        ArrayList<Users> usersList  = dbTool.getAllUsers();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyRecyclerViewAdapter(usersList, new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Users user) {
                Toast.makeText(ViewAllCustomers.this, "clicked "+user.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ViewAllCustomers.this,CustomerActivity.class);
                intent.putExtra("_id",user.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}