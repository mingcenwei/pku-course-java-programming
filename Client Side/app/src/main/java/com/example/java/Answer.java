package com.example.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class Answer extends AppCompatActivity {

    public static String answer_content;
    public String question_id;
    private JSONObject myjson;
    private JSONObject myjson1;
    private JSONObject myjson2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent intent = getIntent();
        question_id = intent.getStringExtra("ID");
        String ss=ConnectServer.sendAndReceicveData("[\"fetchQuestion\", \""+Login.token+"\",\""+question_id+"\"]");
        try {
            myjson1=new JSONObject(ss);
            ((TextView)findViewById(R.id.questions)).setText(myjson1.getString("question_text"));
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
    public void button_add_picture(View view) {
        Toast.makeText(Answer.this,"请功能暂且未实现",Toast.LENGTH_SHORT).show();
    }

    public void button_confirm(View view) {
        answer_content= ((EditText)findViewById(R.id.answers)).getText().toString();
        //像数据库访问根据课程问题id找到课程的内容
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+answer_content);
        String ss=ConnectServer.sendAndReceicveData("[\"writeAnswer\", \""+Login.token+"\",\""+question_id+"\",\""+answer_content+"\"]");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+ss);
        if(("False").equals(ss)) {
            Toast.makeText(Answer.this,"回答失败",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Answer.this,"回答成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Class_question.class);
            intent.putExtra("ID",question_id );
            startActivity(intent);
        }

    }

}
