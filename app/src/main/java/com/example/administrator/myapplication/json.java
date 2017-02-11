package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.StringReader;
import java.lang.reflect.Array;

import static android.content.ContentValues.TAG;

/**
 * Create chaoyu on 2017/2/9.
 */
public class json extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json);
        Button start= (Button) findViewById(R.id.json);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = "[{\"name\":\"aa\",\"age\":20},{\"name\":\"bb\",\"age\":18}]";
                parseJson(json);
            }
        });
    }
    public void parseJson(String jsonData){
        try {
            JsonReader reader = new JsonReader(new StringReader(jsonData));
            reader.beginArray();
            int i = 0;
            while (reader.hasNext()){
                reader.beginObject();
                Log.d(TAG, "第 "+i+" 个元素开始");
                while (reader.hasNext()){
                    String tagName = reader.nextName();
                    if (tagName.equals("name")){
                        Log.d(TAG, "name:"+reader.nextString());
                    }else if (tagName.equals("age")){
                        Log.d(TAG, "age:"+reader.nextInt());
                    }
                }
                reader.endObject();
                i++;
            }
            reader.endArray();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}