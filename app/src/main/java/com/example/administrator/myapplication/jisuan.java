package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.content.ContentValues.TAG;

public class jisuan extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jisuan);

        Button dengyu = (Button)findViewById(R.id.dengyu);
        dengyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textOne= (EditText)findViewById(R.id.one);
                EditText textTow= (EditText)findViewById(R.id.tow);
                String one = textOne.getText().toString();
                String tow = textTow.getText().toString();
                Intent obj = new Intent();
                obj.putExtra("one",one);
                obj.putExtra("tow",tow);
                obj.setClass(jisuan.this,Dengyu.class);
                jisuan.this.startActivity(obj);
            }
        });
    }
}
