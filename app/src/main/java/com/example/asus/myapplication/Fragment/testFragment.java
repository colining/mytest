package com.example.asus.myapplication.Fragment;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.example.asus.myapplication.R;
import com.baoyz.swipemenulistview.*;

/**
 * Created by asus on 2016/10/19.
 */
public class testFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    protected Toolbar toolbar;
    protected DrawerLayout mDrawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.test,container,false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        toolbar= (Toolbar) view.findViewById(R.id.include);

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("便签");
        toolbar.setNavigationIcon(R.drawable.more);
        mDrawerLayout= (DrawerLayout) view.findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }



            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);//设置监听器
        return  view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);


    }

}
