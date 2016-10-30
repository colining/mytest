package com.example.asus.myapplication.Model;

import java.util.Date;

/**
 * Created by asus on 2016/10/30.
 */
public class Note {
        private String time;
    private String note;
    private  int postion;


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
