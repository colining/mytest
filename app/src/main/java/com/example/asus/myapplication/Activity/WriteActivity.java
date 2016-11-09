package com.example.asus.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v4.app.Fragment;

import com.example.asus.myapplication.Fragment.WriteFragment;

/**
 * Created by asus on 2016/10/30.
 */
public class WriteActivity extends  SingleFragmentActivity {
    @Override

    protected Fragment createFragment() {
        return new WriteFragment();
    }

    public  static  final String  EXTRA_NOTE_POSITION = "com.example.asus.myappliction.note_position";
    public  static  final  String SQL= "com.example.asus.myappliction.SQL_CODE";
    public  static Intent newIntent(Context packageContext,int position,int sqlcode)
    {
        Intent intent = new Intent(packageContext,WriteActivity.class);
        intent.putExtra(EXTRA_NOTE_POSITION,position);
        intent.putExtra(SQL,sqlcode);
        return  intent;
    }
}
