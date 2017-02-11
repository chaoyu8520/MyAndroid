package com.example.administrator.myapplication;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/2/10.
 */
public class cache extends Activity {
    private Map<String,String> huancun = new HashMap<String, String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cache);
        Button huoqu= (Button) findViewById(R.id.huoqu);
        Button add= (Button) findViewById(R.id.add);

        huoqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (huancun.containsKey("ceshi")){
                    String zhi = huancun.get("ceshi");
                    Log.d(TAG, "缓存值: "+zhi);
                }else{
                    Log.e(TAG, "没有缓存");
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huancun.put("ceshi","aa123123");
            }
        });
    }
}