package com.example.bankingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ViewOneCustomerFragment extends Fragment {
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.view_one_customer_fragment,container,false);
        displayCustomerInfo(root);
        return root;
    }

    private void displayCustomerInfo(ViewGroup root) {
        TextView txtName,txtEmail,txtAccount,txtBalance;
        ImageView imageView;

        txtName= root.findViewById(R.id.textViewuname);
        txtEmail = root.findViewById(R.id.textViewuemail);
        txtAccount = root.findViewById(R.id.textViewuaccount);
        txtBalance = root.findViewById(R.id.textViewubalance);
        imageView = root.findViewById(R.id.imageViewuser);
        Users user = dbTool.getSingleUser(_id);

        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtAccount.setText(user.getAccount());
        txtBalance.setText(user.getBalance());
        imageView.setImageResource(R.drawable.person);
    }
}
