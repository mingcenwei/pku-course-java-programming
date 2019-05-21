package com.example.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Class_question extends AppCompatActivity {

    String question;
    String question_id;
    private JSONArray myjson;
    private JSONObject myjson1;
    private JSONObject myjson2;
    private String answer_id="";
    int id[]= {
            R.id.number1,R.id.number2,
            R.id.number3,R.id.number4,
            R.id.number5,R.id.number6,
            R.id.number7,R.id.number8,
            R.id.number9,R.id.number10,
    };
    int answer[]= {
            R.id.A1,R.id.A2,
            R.id.A3,R.id.A4,
            R.id.A5,R.id.A6,
            R.id.A7,R.id.A8,
            R.id.A9,R.id.A10,
    };
String topic_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_question);
        Intent intent=getIntent();
        //得到问题的id
        question_id = intent.getStringExtra("ID");
        topic_id=intent.getStringExtra("topic_id");

        String sss=ConnectServer.sendAndReceicveData("[\"fetchQuestion\", \""+Login.token+"\",\""+question_id+"\"]");
        //像数据库访问该问题的内容，以及他的回答
        String ss=ConnectServer.sendAndReceicveData("[\"getAnswersOfQuestion\", \""+Login.token+"\",\""+question_id+"\"]");
        System.out.println("******************"+ss);
        try {

            myjson = new JSONArray(ss);
            myjson2=new JSONObject(sss);
            ((TextView)findViewById(R.id.questions)).setText(myjson2.getString("question_text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject de=null;
        try {
            String ssss=ConnectServer.sendAndReceicveData("[\"fetchTopicById\", \""+Login.token+"\",\""+myjson2.getString("topic_id")+"\"]");
            de= new JSONObject(ssss);
            ((TextView)findViewById(R.id.classname)).setText(de.getString("topic_title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<myjson.length();i++) {
            try {
                myjson1 = new JSONObject(ConnectServer.sendAndReceicveData("[\"fetchAnswer\"," +
                        " \""+Login.token+"\",\""+myjson.getString(i)+"\"]"));
                System.out.println("[\"fetchAnswer\"," +
                        " \""+Login.token+"\",\""+myjson.getString(i)+"\"]");
                ((TextView) findViewById(answer[i])).setText(myjson1.getString("answer_text"));
                findViewById(answer[i]).setVisibility(View.VISIBLE);
                ((TextView) findViewById(id[i])).setText(String.valueOf(i+1));
                findViewById(id[i]).setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i=myjson.length();i<10;i++) {
            ((TextView) findViewById(answer[i])).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(id[i])).setVisibility(View.INVISIBLE);
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
    public void button_answer(View view) {
        //将 问题的id 传递给问题回答界面。
        Intent intent = new Intent(this,Answer.class);
        intent.putExtra("ID",question_id);
        startActivity(intent);
    }
    public void button_detail_1(View view) {
        if (myjson.length() == 1) {
            Toast.makeText(Class_question.this,"你已经回答该问题，请勿重复回答",Toast.LENGTH_SHORT).show();
        } else {
            String answer_ID = "";
            for (int i = 0; i < myjson.length(); i++) {
                if (answer[i] == view.getId()) {
                    try {
                        answer_ID = (myjson.getJSONObject(i)).getString("answer_id");
                        break;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent(this, Full_a_answer.class);
            intent.putExtra("ID", answer_ID);
            startActivity(intent);
        }
    }
    public void focus(View view)
    {
        String ss=ConnectServer.sendAndReceicveData("[\"followQuestion\", \""+Login.token+"\",\""+question_id+"\",\""+"True"+"\"]");
        if(("True").equals(ss))
        {
            Toast.makeText(Class_question.this,"收藏成功",Toast.LENGTH_SHORT).show();
        }
    }


}
