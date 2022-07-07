package com.example.bankingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOError;
import java.util.ArrayList;

public class MyRecyclerViewAdapter  extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    ArrayList<Users> usersList = new ArrayList<>();
    private ItemClickListener mItemListener;
    public MyRecyclerViewAdapter(ArrayList<Users> usersList,ItemClickListener itemClickListener) {
        this.usersList = usersList;
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        Users user = usersList.get(position);
        holder.Name.setText(user.getName());
        holder.Email.setText(user.getEmail());
        holder.Account.setText(user.getAccount());
        holder.Balance.setText(user.getBalance());
        holder.Icon.setImageResource(R.drawable.person);
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(usersList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public interface ItemClickListener{
        void onItemClick(Users user);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Email,Account,Balance;
        ImageView Icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.textViewname);
            Email = itemView.findViewById(R.id.textViewemail);
            Account = itemView.findViewById(R.id.textViewaccount);
            Balance =itemView.findViewById(R.id.textViewbalance);
            Icon = itemView.findViewById(R.id.imageViewicon);
        }
    }

}
