package com.example.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ToQuestion extends AppCompatActivity {

    public static String question_content;
    public String topicId;
    private JSONObject myjsons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_question);
        Intent intent = getIntent();
        topicId=intent.getStringExtra("ID");

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
        Toast.makeText(ToQuestion.this,"请功能暂且未实现",Toast.LENGTH_SHORT).show();
    }
    public void button_confirm(View view) {
        question_content = ((EditText)findViewById(R.id.questions)).getText().toString();
        //将该问题的内瓤，所属的课程id，等等创建后向数据库发送。
        String ss=ConnectServer.sendAndReceicveData("[\"createQuestion\", \""+
                Login.token+"\",\""+topicId+"\",\""+"问题\",\""+question_content+"\"]");
        System.out.println("*******************"+"[\"createQuestion\", \""+
                Login.token+"\",\""+topicId+"\",\""+"问题\",\""+question_content+"\"]");
        if(("False").equals(ss))
        {
            Toast.makeText(ToQuestion.this,"提问失败",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(ToQuestion.this,"提问成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Classes.class);
            intent.putExtra("ID",topicId);
            startActivity(intent);
        }

    }
}
