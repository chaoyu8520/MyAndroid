package com.example.administrator.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/1/26.
 */
public class List extends ListActivity {
    private ListView listView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView = getListView();
        Button jiazai= (Button) findViewById(R.id.jiazai);
        jiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addList();
            }
        });
    }
    private ListAdapter list(){
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        for (int i=1;i<(int)(Math.random()*10);i++){
            HashMap<String,String> map1 = new HashMap<String, String>();
            map1.put("name","aaa"+i);
            map1.put("nl",""+i);
            list.add(map1);
        }

        SimpleAdapter ListAdapter = new SimpleAdapter(this,list,R.layout.lists,
                new String[] {"name","nl"},new int[]{R.id.name,R.id.nl});
        return ListAdapter;
    }
    private void addList(){
        setListAdapter(list());
//        notifyDataSetChanged();
        Animation anim = AnimationUtils.loadAnimation(List.this,R.anim.list_anim);
        LayoutAnimationController lac = new LayoutAnimationController(anim);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        listView.setLayoutAnimation(lac);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        System.out.println("position----------- " + position);
        System.out.println("id----------- " + id);
    }
}
