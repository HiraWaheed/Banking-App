package com.example.bankingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class TransferMoneyFragment extends Fragment {
    DbTool dbTool;
    int _id;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbTool = new DbTool(getContext());
        if (getArguments() != null) {
            _id = getArguments().getInt("_id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.transfer_money_fraglayout,container,false);
        getInfo(root);
        return root;
    }

    private void getInfo(ViewGroup root) {
        EditText editText = root.findViewById(R.id.edittextbalance);
        RecyclerView recyclerView = root.findViewById(R.id.receiverrecyclerview);
        MyRecyclerViewAdapter adapter;

        Users sender = dbTool.getSingleUser(_id);
        int senderBalance = Integer.parseInt(String.valueOf(sender.getBalance()));

        ArrayList<Users> usersList  = dbTool.getAllUsers();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyRecyclerViewAdapter(usersList, new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Users receiver) {
                if(editText.getText().toString().equals("0")){
                    Snackbar snackbar = Snackbar.make(root, "Enter amount to transfer first", Snackbar.LENGTH_LONG);
                    TextView snackbarActionTextView = (TextView)  snackbar.getView().findViewById( com.google.android.material.R.id.snackbar_text);
                    snackbarActionTextView.setTextSize( 20 );
                    snackbar.show();
                }else{
                    if ((receiver.getName().equals(sender.getName()))) {
                        Snackbar snackbar = Snackbar.make(root, "Sender cannot be same as receiver", Snackbar.LENGTH_LONG);
                        TextView snackbarActionTextView = (TextView)  snackbar.getView().findViewById( com.google.android.material.R.id.snackbar_text);
                        snackbarActionTextView.setTextSize( 20 );
                        snackbar.show();
                    } else {
                        int amount = Integer.parseInt(String.valueOf(editText.getText()));
                        if(amount <= senderBalance) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Confirm Transfer");
                            builder.setMessage("Send " + editText.getText() + " to " + receiver.getName() + "?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            int updatedAmount = senderBalance-amount;
                                            int updatedReceiverAmount = Integer.parseInt(receiver.getBalance()) + amount;
                                            dbTool.updateUser(Integer.toString(_id),Integer.toString(updatedAmount),sender); //updates current balance of sender in database
                                            dbTool.updateUser(Integer.toString(receiver.getId()),Integer.toString(updatedReceiverAmount),receiver);//updates current balance of receiver in database
                                            //adds transfer in transfer table
                                            dbTool.addTransfer(sender.getName(),sender.getEmail(),sender.getAccount(),receiver.getName(),receiver.getEmail(),receiver.getAccount(),Integer.toString(amount));
                                            Snackbar snackbar = Snackbar.make(root, "Money sent", Snackbar.LENGTH_LONG);
                                            TextView snackbarActionTextView = (TextView)  snackbar.getView().findViewById( com.google.android.material.R.id.snackbar_text);
                                            snackbarActionTextView.setTextSize( 20 );
                                            snackbar.show();
                                            Intent intent = new Intent(getContext(),MainActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("Cancel", null);
                            AlertDialog alert = builder.create();
                            alert.show();
                            alert.getWindow().setBackgroundDrawableResource(R.color.white);
                            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(android.R.color.holo_blue_light));
                            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(20);
                            alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(android.R.color.holo_blue_light));
                            alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(20);
                        }else{
                            Snackbar snackbar = Snackbar.make(root, "Insufficient amount", Snackbar.LENGTH_LONG);
                            TextView snackbarActionTextView = (TextView)  snackbar.getView().findViewById( com.google.android.material.R.id.snackbar_text);
                            snackbarActionTextView.setTextSize( 20 );
                            snackbar.show();
                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
