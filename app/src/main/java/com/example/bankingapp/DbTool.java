package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.TrafficStats;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbTool extends SQLiteOpenHelper {
    String UserTable = "USERS";
    String TransfersTable = "TRANSFERS";
    public DbTool(@Nullable Context context) {
        super(context,"Bank",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String UserQuery = "CREATE TABLE "+UserTable+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "fullName TEXT,"+
                "email TEXT,"+
                "accountNo TEXT,"+
                "currentBalance INTEGER)";
        String TransferQuery = "CREATE TABLE "+ TransfersTable +"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "senderName TEXT,"+
                "senderEmail TEXT,"+
                "senderAccount INTEGER,"+
                "receiverName TEXT,"+
                "receiverEmail TEXT,"+
                "receiverAccount INTEGER,"+
                "transferAmount INTEGER)";
        sqLiteDatabase.execSQL(UserQuery);
        sqLiteDatabase.execSQL(TransferQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void AddUsers(){
        SQLiteDatabase dbread = getReadableDatabase();
        String Query = "SELECT * FROM "+UserTable;
        Cursor cursor = dbread.rawQuery(Query,null);
        if(cursor.getCount() < 10){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("fullName","Hira Waheed");
            contentValues.put("email","hirawaheed@gmail.com");
            contentValues.put("accountNo","12345688191");
            contentValues.put("currentBalance",10000);
            long i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Fatima Asad");
            contentValues.put("email","fatimaasad@gmail.com");
            contentValues.put("accountNo","23456788898");
            contentValues.put("currentBalance",11000);
            i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Anam Waheed");
            contentValues.put("email","anamwaheed1@gmail.com");
            contentValues.put("accountNo","10389183091");
            contentValues.put("currentBalance",10000);
            i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Zoya Rana");
            contentValues.put("email","zoyaaa1@gmail.com");
            contentValues.put("accountNo","37173017111");
            contentValues.put("currentBalance",11000);
            i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Hammad Ali");
            contentValues.put("email","hammadali123@gmail.com");
            contentValues.put("accountNo","9183901809180");
            contentValues.put("currentBalance",10000);
            i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Sameer");
            contentValues.put("email","sameer@gmail.com");
            contentValues.put("accountNo","19820180281");
            contentValues.put("currentBalance",11000);
            i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Haider Ijaz");
            contentValues.put("email","haiderijaz@gmail.com");
            contentValues.put("accountNo","91209172019");
            contentValues.put("currentBalance",10000);
            i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Haiqa Irfan");
            contentValues.put("email","haiqairfan@gmail.com");
            contentValues.put("accountNo","10281281821");
            contentValues.put("currentBalance",11000);
            i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Tim Burt");
            contentValues.put("email","timburt1@gmail.com");
            contentValues.put("accountNo","18091319611");
            contentValues.put("currentBalance",10000);
            i = db.insert(UserTable,null,contentValues);

            contentValues.put("fullName","Alex Grey");
            contentValues.put("email","alexgrey345@gmail.com");
            contentValues.put("accountNo","683193717109");
            contentValues.put("currentBalance",11000);
            i = db.insert(UserTable,null,contentValues);
        }

    }
    public ArrayList<Users> getAllUsers(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Users> userList = new ArrayList<Users>();
        String Query = "SELECT * FROM "+UserTable;
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor.moveToFirst()){
            do{
                Users user = new Users();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setAccount(cursor.getString(3));
                user.setBalance(cursor.getString(4));
                userList.add(user);
            }while(cursor.moveToNext());
        }
        return userList;
    }

    public Users getSingleUser(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String Query = "SELECT * FROM "+UserTable+" WHERE _id="+id;
        Cursor cursor = db.rawQuery(Query,null);
        Users user = new Users();
        if(cursor.moveToFirst()){
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setAccount(cursor.getString(3));
            user.setBalance(cursor.getString(4));
        }
        return user;
    }

    public void updateUser(String id,String newBalance,Users user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id",id);
        contentValues.put("fullName",user.getName());
        contentValues.put("email",user.getEmail());
        contentValues.put("accountNo",user.getAccount());
        contentValues.put("currentBalance",newBalance);
        long i = db.update(UserTable,contentValues,"_id = ?",new String[]{id});
        if(i != -1){
            Log.d("tagg","User Updated Successfully");
        }else{
            Log.d("tagg","Failed");
        }
    }

    public void addTransfer(String name, String email, String account, String name1, String email1, String account1, String amount) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("senderName",name);
        contentValues.put("senderEmail",email);
        contentValues.put("senderAccount",account);
        contentValues.put("receiverName",name1);
        contentValues.put("receiverEmail",email1);
        contentValues.put("receiverAccount",account1);
        contentValues.put("transferAmount",amount);
        long i = db.insert(TransfersTable,null,contentValues);
    }
}
