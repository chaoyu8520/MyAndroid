package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Dengyu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dengyu);
        TextView dengyu = (TextView)findViewById(R.id.dengyu);


        Intent param = getIntent();
        String one = param.getStringExtra("one");
        String tow = param.getStringExtra("tow");
        int value1 = Integer.parseInt(one);
        int value2 = Integer.parseInt(tow);
        int zong = value1 * value2;
        dengyu.setText(zong+"");
    }
}

