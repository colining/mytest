package com.example.asus.myapplication.Model;

import java.util.Date;

/**
 * Created by asus on 2016/10/30.
 */
public class Note {
        private Date mDate;
    private String note;
    private  int postion;
    public Note(int postion)
    {
        this.postion = postion;
        mDate = new Date();
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date time) {
        mDate= time;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
