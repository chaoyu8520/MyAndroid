package com.example.administrator.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/1/26.
 */
public class List extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map1 = new HashMap<String, String>();
        HashMap<String,String> map2 = new HashMap<String, String>();
        HashMap<String,String> map3 = new HashMap<String, String>();
        map1.put("name","aaa");
        map1.put("nl","18");

        map2.put("name","bbb");
        map2.put("nl","20");

        map3.put("name","ccc");
        map3.put("nl","30");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map1);
        SimpleAdapter ListAdapter = new SimpleAdapter(this,list,R.layout.lists,
                new String[] {"name","nl"},new int[]{R.id.name,R.id.nl});
        setListAdapter(ListAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        System.out.println("position----------- " + position);
        System.out.println("id----------- " + id);
    }
}
