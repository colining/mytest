package com.example.asus.myapplication.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by asus on 2016/10/30.
 */
public class Notelab {
    private  static  Notelab sNotelab;
    private List<Note> mNotes;

    public static Notelab getNotelab(Context context) {
        if(sNotelab == null)
            sNotelab = new Notelab(context);
        return sNotelab;
    }

    public List<Note> getNotes() {
        return mNotes;
    }
    private  Notelab(Context context)
    {
        mNotes= new ArrayList<>();
      for (int i = 0;i<100;i++)
      {
          Note note = new Note();

          note.setNote("lalalal"+i*100);
          if (i ==2)
              note.setNote("wwwww");
          note.setPostion(i);
          note.setTime("lalalal");
          mNotes.add(note);
      }
    }

    private  Note getNote(int position)
    {
        for (Note note : mNotes)
        {
            if (note.getPostion()<=position)
                return  note;
        }
        return null;
    }


}
