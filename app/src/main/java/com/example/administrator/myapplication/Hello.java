package com.example.administrator.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Hello extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello);
        TextView hello = (TextView)findViewById(R.id.helloText);
        Button button = (Button)findViewById(R.id.buttonText);
        Button jisuan = (Button)findViewById(R.id.jisuan);
        hello.setText(R.string.hello);
        button.setText(R.string.myButton);
        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: 点击OK");
                Toast.makeText(Hello.this,"aaaaaaaaaa",Toast.LENGTH_SHORT).show();
                Button cancel = (Button) findViewById(R.id.cancel);
                cancel.setVisibility(View.VISIBLE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("aa","aasdf");
                intent.setClass(Hello.this,LoginActivity.class);
                Hello.this.startActivity(intent);
            }
        });
        jisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Hello.this,"sdf",Toast.LENGTH_SHORT);
                Intent intent = new Intent();
                intent.setClass(Hello.this,jisuan.class);
                Hello.this.startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,1,"退出");
        menu.add(0,2,2,"关于");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
