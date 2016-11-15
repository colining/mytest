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



    private   int notesize=0;
    public int getNotesize() {
        return notesize;
    }
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

    try{
        cursorWrapper.moveToLast();
        while (!cursorWrapper.isBeforeFirst()) {
//            if (cursorWrapper.getNote().getPostion() == -1) {
//                i = 1;
//                note = cursorWrapper.getNote();
//            } else {
                notes.add(cursorWrapper.getNote());
                Log.d("mytest",cursorWrapper.getNote().getNote()+"  "+cursorWrapper.getNote().getPostion());
                //Log.d("lalala","position"+cursorWrapper.getNote().getPostion()+"\n");
                cursorWrapper.moveToPrevious();
           // }
        }

    }finally {
        cursorWrapper.close();
    }
    notes =noteScequence(notes);
    if (notes.size()>0 ){
        if (notes.get(0).getPostion()<500) {
            notesize = notes.get(0).getPostion();
        }
        else{
            notesize= notes.get(1).getPostion();
        }

    }
    return notes;
}
    private  List<Note> noteScequence(List<Note> notes)
    {
        for (int i = 0 ; i<notes.size();i++)
            for (int j = i;j<notes.size();j++)
            {
                if (notes.get(i).getPostion()<notes.get(j).getPostion())
                {
                    Note note=notes.get(i);
                    notes.set(i,notes.get(j));
                    notes.set(j,note);
                }
            }

        return  notes;
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
    public void  updateNote(Note note ,int i)
    {
        ContentValues values = getContentValues(note);
        String where = "position=?";
        String[] whereArgs = new String[] {String.valueOf(i)};
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
