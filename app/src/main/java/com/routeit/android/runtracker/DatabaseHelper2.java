package com.raywenderlich.android.runtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public DatabaseHelper2(Context context){
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB2) {
MyDB2.execSQL("create Table users(urname Text primary key,password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB2, int oldVersion, int newVersion) {
MyDB2.execSQL("drop Table if exists users");
    }
    public Boolean insertData(String urname,String password){
        SQLiteDatabase myDB2=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("urname",urname);
        contentValues.put("password",password);
        long result =myDB2.insert("users",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return  true;
        }
    }
    public Boolean checkusername(String urname){
        SQLiteDatabase myDB2=this.getWritableDatabase();
        Cursor cursor=myDB2.rawQuery("select * from users where urname= ?",new String[]{urname});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean checkusernamePassword(String urname,String password){
        SQLiteDatabase myDB2=this.getWritableDatabase();
        Cursor cursor=myDB2.rawQuery("select * from users where urname = ? and password = ?",new String[]{urname,password});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }

    }
}
