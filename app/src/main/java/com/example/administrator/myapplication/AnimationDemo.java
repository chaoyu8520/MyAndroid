package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/2/9.
 */
public class AnimationDemo extends Activity {
    private ImageView img = null;
    private int x=0;
    private int y=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        Button scale= (Button) findViewById(R.id.scale);
        Button rotate= (Button) findViewById(R.id.rotate);
        Button alpha= (Button) findViewById(R.id.alpha);
        Button translate= (Button) findViewById(R.id.translate);
        Button xmlTranslate= (Button) findViewById(R.id.xmlTranslate);
        img= (ImageView) findViewById(R.id.img);
        xmlTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation set = AnimationUtils.loadAnimation(AnimationDemo.this,R.anim.animet);
                img.startAnimation(set);
            }
        });
        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet set = new AnimationSet(true);

                ScaleAnimation Scale = new ScaleAnimation(1,0.5f,1,0.5f);
                Scale.setDuration(2000);
                set.addAnimation(Scale);
                set.setFillAfter(true);
                img.startAnimation(set);
            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet set = new AnimationSet(true);
                RotateAnimation Rotate = new RotateAnimation(0,360,
                        android.view.animation.Animation.RELATIVE_TO_SELF,0.5f,
                        android.view.animation.Animation.RELATIVE_TO_SELF,0.1f);
                Rotate.setDuration(500);
                set.addAnimation(Rotate);
                set.setFillAfter(true);
                img.startAnimation(set);
            }
        });
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet set = new AnimationSet(true);
                AlphaAnimation alpha = new AlphaAnimation(1,0);
                alpha.setDuration(1000);
                set.addAnimation(alpha);
                set.setFillAfter(true);
                img.startAnimation(set);
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView thisImg = (ImageView) findViewById(R.id.img);
                Log.d(TAG, "X: "+thisImg.getPivotX());
                Log.d(TAG, "Y: "+thisImg.getPivotY());
                AnimationSet set = new AnimationSet(true);
                TranslateAnimation Translate = new TranslateAnimation(x,x+150,y,y+150
                );
                Translate.setDuration(2000);
                set.setFillAfter(true);
                set.addAnimation(Translate);
                Translate.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(android.view.animation.Animation animation) {
                        Log.e(TAG, "onAnimationStart: 1");
                    }

                    @Override
                    public void onAnimationEnd(android.view.animation.Animation animation) {
                        Log.e(TAG, "onAnimationStart: 2");
                        ImageView thisImg = (ImageView) findViewById(R.id.img);
                        Log.d(TAG, "X: "+thisImg.getPivotX());
                        Log.d(TAG, "Y: "+thisImg.getPivotY());
                        x+=150;
                        y+=150;
                    }

                    @Override
                    public void onAnimationRepeat(android.view.animation.Animation animation) {
                        Log.e(TAG, "onAnimationStart: 3");
                    }
                });
//                AlphaAnimation alpha = new AlphaAnimation(1,0.5f);
//                alpha.setDuration(1000);    //执行时间
//                alpha.setStartOffset(1000); //暂停1秒
//                set.addAnimation(alpha);    //加入动画
                img.startAnimation(set);
            }
        });
        
        
    }
}