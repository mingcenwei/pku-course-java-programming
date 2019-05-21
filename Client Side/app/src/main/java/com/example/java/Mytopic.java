package com.example.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Mytopic extends AppCompatActivity {
   private  int Class_id[]={
            R.id.class_1,R.id.class_2,R.id.class_3,
            R.id.class_4,R.id.class_5,R.id.class_6,
            R.id.class_7,R.id.class_8,R.id.class_9,
            R.id.class_10,R.id.class_11,R.id.class_12,
            R.id.class_13, R.id.class_14,R.id.class_15,
            R.id.class_16, R.id.class_17,R.id.class_18,
            R.id.class_19, R.id.class_20,R.id.class_21,
            R.id.class_22, R.id.class_23, R.id.class_24,
            R.id.class_25,R.id.class_26, R.id.class_27,
            R.id.class_28,R.id.class_29, R.id.class_30
    };
   private int id[]={
           R.id.id1,R.id.id2,R.id.id3,
           R.id.id4,R.id.id5,R.id.id6,
           R.id.id7,R.id.id8,R.id.id9,
           R.id.id10,R.id.id11,R.id.id12,R.id.id13,
           R.id.id14,R.id.id15,R.id.id16,
           R.id.id17,R.id.id18,R.id.id19,
           R.id.id20,R.id.id21,R.id.id22,R.id.id23,
           R.id.id24,R.id.id25,R.id.id26,
           R.id.id27,R.id.id28,R.id.id29,
           R.id.id30
    };
   private int Class_contents[]={
           R.id.content1,R.id.content2,R.id.content3,
           R.id.content4,R.id.content5,R.id.content6,
           R.id.content7,R.id.content8,R.id.content9,
           R.id.content10,R.id.content11,R.id.content12,
           R.id.content13, R.id.content14,R.id.content15,
           R.id.content16, R.id.content17,R.id.content18,
           R.id.content19, R.id.content20,R.id.content21,
           R.id.content22,R.id.content23, R.id.content24,
           R.id.content25,R.id.content26, R.id.content27,
           R.id.content28,R.id.content29, R.id.content30,
   };

    public JSONArray myjson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytopic);
        //获得body内容以及设置可显示部分
        String ss=ConnectServer.sendAndReceicveData("[\"fetchTopicsFollowed\", \""+Login.token+"\"]");
        try {
             myjson = new JSONArray(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }

       //{"topic_title":"Achong","topic_description":"Next-Door Bike Store","topic_id":"1"}
        for(int i=0;i<myjson.length();i++) {
            try {
                JSONObject myjObject=myjson.getJSONObject(i);
                ((TextView) findViewById(Class_id[i])).setText(myjObject.getString("topic_title"));
                findViewById(Class_id[i]).setVisibility(View.VISIBLE);
                ((TextView) findViewById(id[i])).setText(String.valueOf(i+1));
                findViewById(id[i]).setVisibility(View.VISIBLE);
                ((Button) findViewById(Class_contents[i])).setText(myjObject.getString("topic_description"));
                findViewById(Class_contents[i]).setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       for(int i=myjson.length();i<30;i++) {
            ((TextView) findViewById(Class_id[i])).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(id[i])).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(Class_contents[i])).setVisibility(View.INVISIBLE);
        }
    }

    public void button_home(View view) {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
    public void button_index(View view) {
        Intent intent = new Intent(this,Index.class);
        startActivity(intent);
    }
    public void button_question(View view) {
        Intent intent = new Intent(this,Question.class);
        startActivity(intent);
    }
    public void button_mysave(View view) {
        Intent intent = new Intent(this,Mysave.class);
        startActivity(intent);
    }
    public void button_search(View view) {
        Intent intent = new Intent(this,Mytopic.class);
        startActivity(intent);
    }
    public void search(View view){
        Toast.makeText(Mytopic.this, "对不起，搜索功能尚未实现", Toast.LENGTH_LONG).show();
    }
    public void  button_enter_class_1(View view) {
        String ID="";

        for(int i=0;i<myjson.length();i++)
        {

            if(Class_contents[i]==view.getId())
            {
                try {
                    ID=  (myjson.getJSONObject(i)).getString("topic_id");
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",ID);

        startActivity(intent);
    }

}
