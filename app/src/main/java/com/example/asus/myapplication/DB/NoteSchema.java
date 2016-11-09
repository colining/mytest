package com.example.asus.myapplication.DB;

import java.util.Date;

/**
 * Created by asus on 2016/11/9.
 */

public class NoteSchema {
    public  static  class NoteTable {

        public  static  final String NAME = "mynote";
        public  static  final class  Cols{
            public  static  final  String Position = "position";
            public  static  final String Date = "date";
            public  static  final  String Note = "note";
        }
    }
}
