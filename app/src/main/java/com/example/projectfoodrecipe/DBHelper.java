package com.example.projectfoodrecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String Dbname="Login.db";//name of database

    public DBHelper(Context context) {//constructor

        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username text primary key,password text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");


    }
    public boolean insert(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValue=new ContentValues();
        contentValue.put("username",username);
        contentValue.put("password",password);
        long result=db.insert("users",null,contentValue);
        if(result==-1)
            return false;
        else
            return true;

    }

    public boolean check(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public boolean checkPass(String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and  password =?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }


}
