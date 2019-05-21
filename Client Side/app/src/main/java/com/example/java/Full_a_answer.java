package com.example.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Full_a_answer extends AppCompatActivity {
    String answer_id;
    public JSONObject myjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_a_answer);
        Intent intent=getIntent();
        answer_id = intent.getStringExtra("ID");
        //像数据库访问该问题的内容，以及他的回答
        String ss=ConnectServer.sendAndReceicveData("[\"fetchAnswer \", \""+Login.token+"\",\""+answer_id+"\"]");
        try {
            myjson = new JSONObject(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView) findViewById(R.id.a_fullanswer)).setText(myjson.getString("answer_text"));
        } catch (JSONException e) {
            e.printStackTrace();
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
    public void button_mytopic(View view) {
        Intent intent = new Intent(this,Mytopic.class);
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
    public void button_collect(View view) {
        String ss=ConnectServer.sendAndReceicveData("[\"fetchAnswer\", \""+Login.token+"\",\""+answer_id+"\"]");
        JSONObject myjsonss;
        try {
            myjsonss= new JSONObject(ss);
            String question_id = myjsonss.getString("question_id");
            ss=ConnectServer.sendAndReceicveData("[\"followQuestion\", \""+Login.token+"\",\""+question_id+"\",\""+"True"+"\"]");
            if(("True").equals(ss))
            {
                Toast.makeText(Full_a_answer.this,"关注成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Full_a_answer.this,"关注失败",Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
