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

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.asus.myapplication.Activity.WriteActivity;
import com.example.asus.myapplication.DB.DataBaseHelper;
import com.example.asus.myapplication.Model.Note;
import com.example.asus.myapplication.Model.Notelab;
import com.example.asus.myapplication.R;
import com.idescout.sql.SqlScoutServer;

import java.util.ArrayList;
import java.util.List;

/**
 * SwipeMenuListView
 * Created by baoyz on 15/6/29.
 */
public class DifferentMenuFragment extends Fragment {

    private List<Note> mNotesList;
    private  List<Note> testlist;
    private AppAdapter mAdapter;
    private  DrawerLayout drawerLayout;
    private  Toolbar toolbar;
    protected DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private  SwipeMenuListView listView;
    private  Notelab mNotelab;
    private boolean  flag;
    private  int MYSQL_CODE=0;
    private   int note_id = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub


        super.onCreate(savedInstanceState);
       // SqlScoutServer.create(getActivity(), getActivity().getPackageName());
        setHasOptionsMenu(true);
        mAdapter=new AppAdapter();
        updateUI();

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
//                if (TextUtils.isEmpty((newText))){
//                    listView.clearTextFilter();
//                }else{
                        mAdapter.getFilter().filter(newText);
                  //  }
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



    public  void updateUI()
    {
        mNotelab=Notelab.getNotelab(getActivity());
        mNotesList = mNotelab.getNotes();
        Log.d("lalala",""+mNotesList.size());
        testlist = mNotesList;


        mAdapter.notifyDataSetChanged();
        Log.d("lalala","lalal");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list,container,false);
      toolbar = (Toolbar) view.findViewById(R.id.include);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        activity.setSupportActionBar(toolbar);

     activity.getSupportActionBar().setTitle("便签");
        FloatingActionButton write = (FloatingActionButton) view.findViewById(R.id.fab);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MYSQL_CODE=0;
                Intent intent = WriteActivity.newIntent(getActivity(),mNotelab.getNotes().size(),MYSQL_CODE);
                startActivity(intent);
            }
        });
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








         listView = (SwipeMenuListView) view.findViewById(R.id.listView);
        listView.setTextFilterEnabled(true); //设置可过滤

        listView.setAdapter(mAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               MYSQL_CODE=1;
                note_id= testlist.get(position).getPostion();
               Intent intent = WriteActivity.newIntent(getActivity(),note_id,MYSQL_CODE);
               startActivity(intent);
           }
       });





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
                        createMenu0(menu);
                        break;
//                    case 2:
//                        createMenu3(menu);
//                        break;
                }
            }

            private void createMenu1(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.GRAY));
                item1.setWidth(dp2px(100));
                item1.setTitle("置顶");
                item1.setTitleSize(24);
                item1.setTitleColor(Color.BLACK);
                menu.addMenuItem(item1);

                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
//                item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//                        0xCE)));
                item2.setWidth(dp2px(40));
                item2.setIcon(R.drawable.delete);
                menu.addMenuItem(item2);
            }

            private void createMenu0(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.GRAY));
                item1.setWidth(dp2px(100));
                item1.setTitle("取消置顶");
                item1.setTitleColor(Color.BLACK);
                item1.setTitleSize(24);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
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
                Note item =testlist.get(position);
                Log.d("postion",""+position);
                switch (index) {

                    case 0:
                    {
                        if (position==0)
                        {
                            testlist.remove(position);
                            testlist.add(testlist.size(),item);
                            mAdapter.notifyDataSetChanged();
                        }
                        else {

                        testlist.remove(position);
                        testlist.add(0,item);
                            mAdapter.notifyDataSetChanged();

                        }

                        listView.smoothScrollToPositionFromTop(0,0);
                        //listView.setSelection(0);
                    }
                        break;
                    case 1: {
                        testlist.remove(position);
                        mNotelab.deleteNote(item);
                        mAdapter.notifyDataSetChanged();
                    }
                        break;
                }

                return false;
            }
        });
        return  view;
    }

    @Override
    public void onResume() {

        super.onResume();
        updateUI();
    }

    class AppAdapter extends BaseAdapter implements Filterable {

//        public  void setNotes(List<Note> notes)
//        {
//            testlist=notes;
//        }
        @Override
        public int getCount() {

            return testlist.size();
        }

        @Override
        public Note getItem(int position) {
            return testlist.get(position);
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
            if(position==0)
            {
                    return  1;
            }
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {     //设定了列表项的view
            if (convertView == null) {
                convertView = View.inflate(getActivity().getApplicationContext(),
                        R.layout.test_list, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
           Note item = getItem(position);
           holder.mTextView.setText(testlist.get(position).getNote());
            holder.mTextView2.setText(testlist.get(position).getmDate().toString());

            return convertView;
        }

        @Override
        public Filter getFilter()
        {

            return new Filter()
            {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence)
                {
                    Log.d("lalala",""+charSequence);
                    FilterResults results = new FilterResults();

                    //If there's nothing to filter on, return the original data for your list
                    if(charSequence == null || charSequence.length() == 0)
                    {
                        results.values = mNotesList;
                        results.count = mNotesList.size();

                    }
                    else
                    {
                        ArrayList<Note> filterResultsData = new ArrayList<Note>();

                        for(Note data : mNotesList)
                        {
                            //In this loop, you'll filter through originalData and compare each item to charSequence.
                            //If you find a match, add it to your new ArrayList
                            charSequence=charSequence.toString().toLowerCase();
                            if(data.getNote().toLowerCase().startsWith(charSequence.toString()))
                            {
                                Log.d("lalala",""+charSequence);
                                filterResultsData.add(data);
                            }
                        }

                        results.values = filterResultsData;

                        results.count = filterResultsData.size();

                    }

                    return results;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults)
                {

                   testlist = (ArrayList<Note>)filterResults.values;
                    Log.e("VALUES", ""+testlist.size());
                    notifyDataSetChanged();
                }
            };


        }



        class ViewHolder {                                                      //

            TextView mTextView;
            TextView mTextView2;

            public ViewHolder(View view) {

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
