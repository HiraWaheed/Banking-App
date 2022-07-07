package com.example.bankingapp;

public class Users {
    int Id;
    String Name;
    String Email;
    String Account;
   String Balance;

    public Users() {
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public Users(String name, String email,String account, String balance) {
        Name = name;
        Email = email;
        Account = account;
        Balance = balance;
    }
}
