package com.example.asus.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        if(sNotelab == null)
            sNotelab = new Notelab(context);
        return sNotelab;
    }

    public List<Note> getNotes() {
       // return mNotes;
       // return  new ArrayList<>();
        List<Note> notes = new ArrayList<>();
        NoteCursorWrapper cursorWrapper = queryNote(null,null);
        try{
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast())
            {
                notes.add(cursorWrapper.getNote());
                cursorWrapper.moveToNext();
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

        //mNotes= new ArrayList<>();
//      for (int i = 0;i<100;i++)
//      {
//          Note note = new Note();
//
//          note.setNote("lalalal"+i*100);
//          if (i ==2)
//              note.setNote("wwwww");
//          note.setPostion(i);
//          note.setTime("lalalal");
//          mNotes.add(note);
//      }
    }

    private  Note getNote(int position)
    {
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
        values.put(NoteTable.Cols.Position,note.getPostion());
        values.put(NoteTable.Cols.Date,note.getmDate().getTime());
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
        String position = String.valueOf(note.getPostion());
       ContentValues values = getContentValues(note);
        mDatabase . update(NoteTable.NAME,values,NoteTable.Cols.Position +"=?",new String[]{position});
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
}
