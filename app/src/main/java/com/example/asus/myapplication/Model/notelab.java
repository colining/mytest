package com.example.asus.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asus.myapplication.DB.DataBaseHelper;
import com.example.asus.myapplication.DB.NoteCursorWrapper;
import com.example.asus.myapplication.DB.NoteSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.asus.myapplication.DB.NoteSchema.*;

/**
 * Created by asus on 2016/10/30.
 */
public class Notelab {
    private  static  Notelab sNotelab;
    private List<Note> mNotes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static Notelab getNotelab(Context context) {
//        if(sNotelab == null)
            sNotelab = new Notelab(context);
        return sNotelab;

    }

//    public List<Note> getNotes() {
//        List<Note> notes = new ArrayList<>();
//        NoteCursorWrapper cursorWrapper = queryNote(null,null);
//        try{
//            cursorWrapper.moveToFirst();
//            while (!cursorWrapper.isAfterLast())
//            {
//                notes.add(cursorWrapper.getNote());
//                cursorWrapper.moveToNext();
//            }
//        }finally {
//            cursorWrapper.close();
//        }
//        return notes;
//    }
public List<Note> getNotes() {
    List<Note> notes = new ArrayList<>();
    NoteCursorWrapper cursorWrapper = queryNote(null,null);  //queryNote(null,null);
    Note note = new Note(-1);
    int i =0;
    try{
        cursorWrapper.moveToLast();
        while (!cursorWrapper.isBeforeFirst()) {
            if (cursorWrapper.getNote().getPostion() == -1) {

                i = 1;
                note = cursorWrapper.getNote();

            } else {
                notes.add(cursorWrapper.getNote());
                Log.d("mytest",cursorWrapper.getNote().getNote());
                //Log.d("lalala","position"+cursorWrapper.getNote().getPostion()+"\n");
                cursorWrapper.moveToPrevious();
            }
        }
        if (i==1)
        {
            notes.add(note);
        }
    }finally {
        cursorWrapper.close();
    }
    return notes;
}
    private  Notelab(Context context)
    {
        mContext = context.getApplicationContext();
        mDatabase = new DataBaseHelper(mContext).getWritableDatabase();
    }

    private  Note getNote(int position)
    {
        Note note;
    NoteCursorWrapper cursorWrapper = queryNote(NoteTable.Cols.Position +"=?",new String[]{String.valueOf(position)});
        try {
            if (cursorWrapper.getCount()==0)
            {
                return null;
            }

            cursorWrapper.moveToFirst();

            return  cursorWrapper.getNote();
        }
        finally {
            cursorWrapper.close();
        }
    }
    private static ContentValues getContentValues(Note note)
    {
        ContentValues values = new ContentValues();
        values.put(NoteTable.Cols.Position,String.valueOf(note.getPostion()));
        values.put(NoteTable.Cols.Date,String.valueOf(note.getmDate().getTime()));
        values.put(NoteTable.Cols.Note,note.getNote());

        return  values;
    }

    public  void addNote(Note note)
    {
        ContentValues values = getContentValues(note);
        mDatabase.insert(NoteTable.NAME,null,values);
    }

    public void  updateNote(Note note)
    {

       ContentValues values = getContentValues(note);
        String where = "position=?";
        String[] whereArgs = new String[] {String.valueOf(note.getPostion())};
        mDatabase.update(NoteTable.NAME,values,where,whereArgs);
    }

    public void  deleteNote(Note note)
    {
        String position = String.valueOf(note.getPostion());
        Log.d("lalaal",position);
        ContentValues values = getContentValues(note);
        mDatabase.delete(NoteTable.NAME,NoteTable.Cols.Position +" = ?",new String[]{position});
    }

    private NoteCursorWrapper queryNote(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(
                NoteTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return  new NoteCursorWrapper(cursor);
    }
    private NoteCursorWrapper querydate()
    {
        Cursor cursor = mDatabase.query(
                NoteTable.NAME,
                null,
               null,
                null,
                null,
                "date DESC",
                null
        );
        return  new NoteCursorWrapper(cursor);
    }

}
