package com.example.etymology;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;


public class DataBase extends SQLiteOpenHelper
{  final String DataBaseName="Etymology.db";
   final String TableName="Etymology";
   final String col1="SNo";
   final String col2="word";

    public DataBase(Context context) {
        super(context,"Etymology.db" ,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("Create Table Etymology(SNo INTEGER PRIMARY KEY AUTOINCREMENT,word TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE if exists Etymology");
        onCreate(db);
    }
//Inserting Data
    public void InsertData(String word)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,word);
        this.getWritableDatabase().insert("Etymology", null, contentValues);
    }
//Retreiving data


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from Etymology",null);
        return  result;
    }

}






