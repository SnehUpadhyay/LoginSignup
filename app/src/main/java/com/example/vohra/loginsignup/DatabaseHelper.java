package com.example.vohra.loginsignup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by vohra on 21-03-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_Name = "company.db";
    public static final String Table_Name = "user";
    public static final String Col_1 = "_ID";
    public static final String Col_2 = "Name";
    public static final String Col_3 = "Email";
    public static final String Col_4 = "Password";
    public static final String Col_5 = "PhoneNumber";
    //public static final String Col_6 = "Otp";
    final String createTable = " create table " + Table_Name + "( _id integer primary key autoincrement, name text, email text, password text, phonenumber text )";
    static SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + Table_Name);
        onCreate(db);
    }

    public boolean insertdata(String name, String email, String password, String phonenumber ) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_2, name);
        cv.put(Col_3, email);
        cv.put(Col_4, password);
        cv.put(Col_5, phonenumber);
        //cv.put(Col_6, otp);
        long result = db.insert(Table_Name, null, cv);
        if (result == -1) {
            return false;
        }else {
            return true;
        }  }

    public boolean checkAlreadyExist(String email) {
        db = this.getWritableDatabase();
        String query = "SELECT " + Col_3 + " FROM " + Table_Name + " WHERE " + Col_3 + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else
            return true;
}

//    public Cursor sendotp(String otp)
//    {
//        db = this.getWritableDatabase();
//        String query1 = "SELECT " + Col_6 + " FROM " + Table_Name + " WHERE " + Col_6 + " =?";
//        Cursor cursor1 = db.rawQuery(query1, new String[]{otp});
//        if (cursor1.getCount() > 0) {
//            return cursor1;
//        } else
//            return cursor1;
//    }

    public int Login(String username, String password) {
        String[] selectionArgs = new String[]{username, password};
        try {
            int i = 0;
            Cursor c = null;
            c = db.rawQuery("SELECT * FROM " + Table_Name + " WHERE " + Col_3 + " =? and " + Col_4 + " =?", selectionArgs);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getall(String email) {
        db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table_Name + " WHERE " + Col_3 + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        cursor.moveToFirst();
        String name=cursor.getString(1);
        return name;
    }
}

