package com.example.asus.myapplication.DB;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.asus.myapplication.Model.Note;

import java.util.Date;

import static com.example.asus.myapplication.DB.NoteSchema.*;

/**
 * Created by asus on 2016/11/9.
 */

public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote()
    {
        int position = getInt(getColumnIndex(NoteTable.Cols.Position));
        String  note= getString(getColumnIndex(NoteTable.Cols.Note));
        long date = getLong(getColumnIndex(NoteTable.Cols.Date));

        Note note1 = new Note(position);
        note1.setNote(note);
        note1.setmDate(new Date(date));
        return  note1;
    }
}
