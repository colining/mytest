package com.example.asus.myapplication.DB;

/**
 * Created by asus on 2016/11/8.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class    DataBase extends SQLiteOpenHelper {
    private final static int VERSION = 1;
    private final static String DB_NAME = "mynote.db";
    private final static String TABLE_NAME = "note";
    private final static String CREATE_TBL = "create table phone(_id integer primary key autoincrement, name text, sex text, number text, desc text)";
    private SQLiteDatabase db;
    public DataBase(Context context, String name, CursorFactory factory,int version) {
        super(context, name, factory, version);
    }

    public DataBase(Context context) {
        super(context, "diary.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Diary "
                + "()";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String sql="ALTER TABLE diary ADD body varchar(1000) null";
        //db.execSQL(sql);
        System.out.println("DataBase Updated!!");
    }

}