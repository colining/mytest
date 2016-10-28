package com.example.asus.myapplication.Fragment;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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
      //  toolbar.setNavigationIcon(R.drawable.more);
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


        //设置导航栏NavigationView的点击事件
        NavigationView mNavigationView = (NavigationView) view.findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.item_green:
                        Toast.makeText(getActivity(),"lallalal",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_blue:
                       // getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentTwo()).commit();
                        Toast.makeText(getActivity(),"lallalal",Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("我的留言");
                        break;
                    case R.id.item_pink:
                       // getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentThree()).commit();
                        Toast.makeText(getActivity(),"lallalal",Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("附近的人");
                        break;
                }
                menuItem.setChecked(true);//点击了把它设为选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });

    return  view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);


    }

}
