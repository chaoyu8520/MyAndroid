package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/2/6.
 */
public class sqlite extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);
        Button create = (Button) findViewById(R.id.create);
        Button add = (Button) findViewById(R.id.add);
        Button update = (Button) findViewById(R.id.update);
        Button list = (Button) findViewById(R.id.list);
        Button del = (Button) findViewById(R.id.del);
        create.setOnClickListener(new btnCreate());
        add.setOnClickListener(new btnAdd());
        update.setOnClickListener(new btnUpdate());
        list.setOnClickListener(new btnList());
        del.setOnClickListener(new btnDel());
    }
    class btnCreate implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(sqlite.this,"ceshibiao");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }
    class btnAdd implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ContentValues values = new ContentValues();
            int id = (int) (Math.random()*100);
            int name = (int)(Math.random()*100);
            values.put("id",id);
            values.put("name",name+"");
            DatabaseHelper dbHelper = new DatabaseHelper(sqlite.this,"ceshibiao");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert("user",null,values);
            Log.d(TAG, "增加数据：id="+id+" name="+name);
            Toast.makeText(sqlite.this,"增加数据：id="+id+" name="+name, Toast.LENGTH_SHORT).show();

        }
    }
    class btnUpdate implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(sqlite.this,"ceshibiao");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name","000");
            db.update("user",values,"id=?",new String[]{"1"});
        }
    }
    class btnList implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(sqlite.this,"ceshibiao");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("user",new String[]{"id","name"},"id=?",new String[]{"3"},null,null,null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Log.d(TAG, "query----->:   "+name);
            }
        }
    }
    class btnDel implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(sqlite.this,"ceshibiao");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int num = db.delete("user","id=?",new String[]{"1"});
            Log.d(TAG, "删除数量："+num);
            Toast.makeText(sqlite.this,"删除数量："+num, Toast.LENGTH_SHORT).show();
        }
    }
}