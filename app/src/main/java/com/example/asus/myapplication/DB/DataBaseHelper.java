package com.example.asus.myapplication.DB;

/**
 * Created by asus on 2016/11/8.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asus.myapplication.DB.NoteSchema.NoteTable;
import com.idescout.sql.SqlScoutServer;

public class DataBaseHelper extends SQLiteOpenHelper {
            private  static  final  int VERSION = 1;
    private  static  final  String DATABASE_NAME = "mynote.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+NoteTable.NAME);
        //创建person表
       // db.execSQL("DROP TABLE IF EXISTS person");

        db.execSQL("create table " + NoteTable.NAME + "("+
        " _id integer primary key autoincrement ,"+
                        NoteTable.Cols.Position + " , "+
                        NoteTable.Cols.Date + " , " +
                        NoteTable.Cols.Note +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}