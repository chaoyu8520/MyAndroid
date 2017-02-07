package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/2/4.
 */
public class jindutiao extends Activity {
    Button start = null;
    ProgressBar jindu = null;
    int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jindutiao);
        start = (Button) findViewById(R.id.startJindu);
        Button end = (Button) findViewById(R.id.end);
        jindu = (ProgressBar) findViewById(R.id.jindu);
        start.setOnClickListener(new ButtonClick());
        end.setOnClickListener(new endButtonClick());
    }
    class ButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            jindu.setVisibility(View.VISIBLE);
            updataJindu.post(xiancheng);
        }
    }
    class endButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            i = 0;
            jindu.setProgress(0);
            jindu.setVisibility(View.GONE);
            updataJindu.removeCallbacks(xiancheng);
        }
    }
    Handler updataJindu = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            jindu.setProgress(msg.arg1);
        }
    };

    Runnable xiancheng = new Runnable() {
        @Override
        public void run() {
            i += (int)(Math.random()*20);
            Message msg = updataJindu.obtainMessage();
            msg.arg1 = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updataJindu.sendMessage(msg);
            if (i >= jindu.getMax()){
                jindu.setVisibility(View.GONE);
                updataJindu.removeCallbacks(xiancheng);
                return;
            }
            updataJindu.post(xiancheng);
        }
    };
}