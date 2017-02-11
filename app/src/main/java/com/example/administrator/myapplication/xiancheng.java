package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/2/3.
 */
public class xiancheng extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);

        Button start = (Button)findViewById(R.id.start);
        Button end = (Button)findViewById(R.id.end);
        Log.e(TAG, "xiancheng-----------------:"+Thread.currentThread().getId());
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerThread handlerThread = new HandlerThread("handler_thread");
                handlerThread.start();
                ceshiHandler ceshi = new ceshiHandler(handlerThread.getLooper());
                Message msg = ceshi.obtainMessage();
                msg.arg1=1;
                msg.sendToTarget();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "handleMessage: "+Thread.currentThread().getId());
                Log.e(TAG, "handleMessage: "+Thread.currentThread().getName());
                Log.d(TAG, "开始线程 ");
                new Thread(){
                    @Override
                    public void run() {
                        Log.e(TAG, "handleMessage: "+Thread.currentThread().getId());
                        Log.e(TAG, "handleMessage: "+Thread.currentThread().getName());
                        Message msg = hand.obtainMessage();
                        msg.arg1 = 111;
                        hand.sendMessage(msg);
                    }
                }.start();
            }
        });
    }
    final Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: "+msg.arg1);
        }
    };
    class ceshiHandler extends Handler{
        public ceshiHandler() {

        }

        public ceshiHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "msg:"+msg.arg1);
            Log.e(TAG, "handleMessage: "+Thread.currentThread().getId());
            Log.e(TAG, "handleMessage: "+Thread.currentThread().getName());

        }
    }
}