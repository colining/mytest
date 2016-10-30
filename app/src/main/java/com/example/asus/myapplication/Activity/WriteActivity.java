package com.example.asus.myapplication.Activity;

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
}
