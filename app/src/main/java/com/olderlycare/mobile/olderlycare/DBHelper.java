package com.olderlycare.mobile.olderlycare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "DATABASE.db";
    public static final String TABLE_NAME = "med_table";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "TIME";
    public static final String COL_2 = "AM";
    public static final String COL_3 = "SCHE";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + TABLE_NAME +" (NAME TEXT PRIMARY KEY,LATITUDE REAL,LONGITUDE REAL)");
//        Log.d("myDebug_ONCreate","START");
        db.execSQL("create table " + TABLE_NAME +" (ID integer PRIMARY KEY, TIME TEXT, AM TEXT, SCHE TEXT)");
        db.execSQL("create table " + "home_table" +" (NAME TEXT PRIMARY KEY,LATITUDE REAL,LONGITUDE REAL)");
//        Log.d("myDebug_ONCreate","END");
    }
    public boolean insertData(String time, String am,String sche) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,time);
        contentValues.put(COL_2,am);
        contentValues.put(COL_3,sche);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
