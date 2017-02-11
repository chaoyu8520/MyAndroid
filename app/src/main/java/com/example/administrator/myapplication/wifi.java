package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/2/8.
 */
public class wifi extends Activity {
    private WifiManager manager = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi);
        Button start= (Button) findViewById(R.id.start);
        Button end = (Button) findViewById(R.id.end);
        Button state = (Button) findViewById(R.id.state);
        start.setOnClickListener(new btnStart());
        end.setOnClickListener(new btnEnd());
        state.setOnClickListener(new btnState());
    }
    class btnStart implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            manager = (WifiManager)wifi.this.getSystemService(Context.WIFI_SERVICE);
            manager.setWifiEnabled(true);
            Log.e(TAG, "打开wifi 状态为 state->"+manager.getWifiState());
            Toast.makeText(wifi.this,"当前状态为 "+manager.getWifiState(),Toast.LENGTH_SHORT).show();
        }
    }
    class btnEnd implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            manager = (WifiManager)wifi.this.getSystemService(Context.WIFI_SERVICE);
            manager.setWifiEnabled(false);
            Log.e(TAG, "关闭wifi state->"+manager.getWifiState());
            Toast.makeText(wifi.this,"当前状态为 "+manager.getWifiState(),Toast.LENGTH_SHORT).show();
        }
    }
    class btnState implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            manager = (WifiManager)wifi.this.getSystemService(Context.WIFI_SERVICE);
            Log.e(TAG, "当前状态 state->"+manager.getWifiState());
            Toast.makeText(wifi.this,"当前状态为 "+manager.getWifiState(),Toast.LENGTH_SHORT).show();
        }
    }
}