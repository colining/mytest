/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 baoyongzhang <baoyz94@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.example.asus.myapplication.Fragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.asus.myapplication.R;

import java.util.List;

/**
 * SwipeMenuListView
 * Created by baoyz on 15/6/29.
 */
public class DifferentMenuFragment extends Fragment {

    private List<ApplicationInfo> mAppList;
    private AppAdapter mAdapter;
    private  DrawerLayout drawerLayout;
    private  Toolbar toolbar;
    protected DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private  SwipeMenuListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.test, menu);
        MenuItem searchitem = menu.findItem(R.id.ab_search);
        MenuItem item = menu.findItem(R.id.ab_search);
        SearchView searchView = new SearchView(getActivity());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                Log.d("lalallala",""+listView.getCount());
                if (TextUtils.isEmpty((newText))){
                    listView.clearTextFilter();
                }else{

                    listView.setFilterText(newText.toString());}
                return false;
            }

        });
        searchView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                          }
                                      }
        );



    }
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        mActionBarDrawerToggle.syncState();
//    }




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list,container,false);
      toolbar = (Toolbar) view.findViewById(R.id.include);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        activity.setSupportActionBar(toolbar);

     activity.getSupportActionBar().setTitle("便签");
     // toolbar.setNavigationIcon(R.drawable.more);    //这边要留意的是setNavigationIcon需要放在 setSupportActionBar之后才会生效。
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




//        //toolbar.setNavigationIcon(R.drawable.more);//设置导航栏图标
//        toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
//        toolbar.setTitle("Title");//设置主标题
//        toolbar.setSubtitle("Subtitle");//设置子标题
//
//        toolbar.inflateMenu(R.menu.test);//设置右上角的填充菜单
//        activity.setSupportActionBar(toolbar);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int menuItemId = item.getItemId();
//                if (menuItemId == R.id.action_item1) {
//                    Toast.makeText(getActivity() ,"test" , Toast.LENGTH_SHORT).show();
//
//                } else
//                    Toast.makeText(getActivity() , "lalal" , Toast.LENGTH_SHORT).show();
//
//                return true;
//            }
//        });


        //for crate home button


    mAppList = getActivity().getPackageManager().getInstalledApplications(0);

         listView = (SwipeMenuListView) view.findViewById(R.id.listView);
        listView.setTextFilterEnabled(true); //设置可过滤
        mAdapter = new AppAdapter();
        listView.setAdapter(mAdapter);
        listView.setFilterText("lala");




        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type   初始化菜单

                switch (menu.getViewType()) {
                    case 0:
                        createMenu1(menu);
                        break;
                    case 1:
                        createMenu2(menu);
                        break;
                    case 2:
                        createMenu3(menu);
                        break;
                }
            }

            private void createMenu1(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(224, 224,
                        224)));
                item1.setWidth(dp2px(40));
                item1.setIcon(R.drawable.delete);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
//                item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//                        0xCE)));
                item2.setWidth(dp2px(40));
                item2.setIcon(R.drawable.delete);
                menu.addMenuItem(item2);
            }

            private void createMenu2(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
                        0x3F)));
                item1.setWidth(dp2px(40));
                item1.setIcon(R.drawable.delete);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                item2.setWidth(dp2px(40));
                item2.setIcon(R.drawable.delete);
                menu.addMenuItem(item2);
            }

            private void createMenu3(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
                        0xF5)));

                item1.setWidth(dp2px(40));
                item1.setIcon(R.drawable.delete);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                item2.setWidth(dp2px(40));
                item2.setIcon(R.drawable.delete);
                menu.addMenuItem(item2);
            }
        };
        // set creator
        listView.setMenuCreator(creator);


        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {     //菜单事件
                ApplicationInfo item = mAppList.get(position);
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
//					delete(item);
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        return  view;
    }

    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            // menu type count
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            // current menu type
            return position % 3;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {     //设定了列表项的view
            if (convertView == null) {
                convertView = View.inflate(getActivity().getApplicationContext(),
                        R.layout.test_list, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            ApplicationInfo item = getItem(position);
            if (position%2==0)
            {
                holder.mTextView.setText("fututut");
                holder.mTextView2.setText("天气不错阿打发实打实的");
            }
            else
            {
                holder.mTextView.setText("lala");
                holder.mTextView2.setText("lalalal");
            }
//            holder.iv_icon.setImageDrawable(item.loadIcon(getActivity().getPackageManager()));
//            holder.tv_name.setText(item.loadLabel(getActivity().getPackageManager()));
            return convertView;
        }

        class ViewHolder {                                                      //
//            ImageView iv_icon;
//            TextView tv_name;
//
//            public ViewHolder(View view) {
//                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
//                tv_name = (TextView) view.findViewById(R.id.tv_name);
//                view.setTag(this);
//            }
            TextView mTextView;
            TextView mTextView2;
            //
            public ViewHolder(View view) {
//                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
//                tv_name = (TextView) view.findViewById(R.id.tv_name);
//                view.setTag(this);
                mTextView = (TextView) view.findViewById(R.id.textView);
                mTextView2 = (TextView) view.findViewById( R.id.textView2);
                view.setTag(this);

            }
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
