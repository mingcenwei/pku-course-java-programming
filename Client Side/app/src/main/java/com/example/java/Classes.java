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
public class Classes extends AppCompatActivity {

    private JSONObject myjson;
    private JSONObject myjson1;
    private JSONArray myjsonarray;
    private String ID="";

    int id[]= {
            R.id.number1,R.id.number2,
            R.id.number3,R.id.number4,
            R.id.number5,R.id.number6,
            R.id.number7,R.id.number8,
            R.id.number9,R.id.number10,
            R.id.number11,R.id.number12,
            R.id.number13,R.id.number14,
            R.id.number15,R.id.number16,
            R.id.number17,R.id.number18,
            R.id.number19,R.id.number20
            };
    int question[]= {
            R.id.Q1,R.id.Q2,
            R.id.Q3,R.id.Q4,
            R.id.Q5,R.id.Q6,
            R.id.Q7,R.id.Q8,
            R.id.Q9,R.id.Q10,
            R.id.Q11,R.id.Q12,
            R.id.Q13,R.id.Q14,
            R.id.Q15,R.id.Q16,
            R.id.Q17,R.id.Q18,
            R.id.Q19,R.id.Q20
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        Intent intent = getIntent();
        //此ID为课程的ID
        ID=intent.getStringExtra("ID");
        String ss = ConnectServer.sendAndReceicveData("[\"fetchTopicById\", \""+Login.token+"\",\""+ID+"\"]");
        System.out.println("###################"+ID+"  "+ss);
        System.out.println("[\"fetchTopicById\", \""+Login.token+"\",\""+ID+"\"]");
        try {
            myjson = new JSONObject(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)findViewById(R.id.classname)).setText(myjson.getString("topic_title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ss = ConnectServer.sendAndReceicveData("[\"fetchQuestionsUnderTopic\", \""+Login.token+"\",\""+ID+"\"]");
        try {
            myjsonarray = new JSONArray(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i< myjsonarray.length();i++) {
            try {
                myjson1 = new JSONObject(ConnectServer.sendAndReceicveData("[\"fetchQuestion\"," +
                        " \""+Login.token+"\",\""+myjsonarray.getString(i)+"\"]"));
                ((Button) findViewById(question[i])).setText(myjson1.getString("question_text"));
                findViewById(question[i]).setVisibility(View.VISIBLE);
                ((TextView) findViewById(id[i])).setText(String.valueOf(i+1));
                findViewById(id[i]).setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i=myjsonarray.length();i<20;i++) {
            ((Button) findViewById(question[i])).setVisibility(View.INVISIBLE);
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

    public void button_question_detail_1(View view) {
        //此IDs为点击课程该问题的ID
        String IDs="";
        for(int i=0;i<myjsonarray.length();i++)
        {
            if(question[i]==view.getId())
            {
                try {
                    IDs =  myjsonarray.getString(i);
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Intent intent = new Intent(this,Class_question.class);
        intent.putExtra("ID",IDs);
        intent.putExtra("topic_id",ID);
        startActivity(intent);
    }

    public void button_raise_question(View view) {
        Intent intent = new Intent(this,ToQuestion.class);
        intent.putExtra("ID",ID);
        startActivity(intent);
    }
    public void focus(View view)
    {
        String ss=ConnectServer.sendAndReceicveData("[\"followTopic\", \""+Login.token+"\",\""+ID+"\",\""+"True"+"\"]");
        if(("True").equals(ss))
        {
            Toast.makeText(Classes.this,"关注成功",Toast.LENGTH_SHORT).show();
        }
    }
}
