package com.example.asus.myapplication.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.asus.myapplication.Fragment.DifferentMenuFragment;
import com.example.asus.myapplication.R;

/**
 * Created by asus on 2016/10/19.
 */
public class testActivity  extends  SingleFragmentActivity{


    @Override
    protected Fragment createFragment(){

        return  new DifferentMenuFragment();
    }


}
