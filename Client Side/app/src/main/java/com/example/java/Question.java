package com.example.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Question extends AppCompatActivity {

int id[]={
        R.id.number1,R.id.number2,
        R.id.number3,R.id.number4,
        R.id.number5,R.id.number6,
        R.id.number7,R.id.number8,
        R.id.number9,R.id.number10,
        R.id.number11,R.id.number12,
        R.id.number13,R.id.number14,
        R.id.number15,R.id.number16,
        R.id.number17,R.id.number18,
        R.id.number19,R.id.number20,
        R.id.number21,R.id.number22,
        R.id.number23,R.id.number24,
        R.id.number25,R.id.number26,
        R.id.number27,R.id.number28,
        R.id.number29,R.id.number30
};
int question[]={
        R.id.question1,R.id.question2,
        R.id.question3,R.id.question4,
        R.id.question5,R.id.question6,
        R.id.question7,R.id.question8,
        R.id.question9,R.id.question10,
        R.id.question11,R.id.question12,
        R.id.question13,R.id.question14,
        R.id.question15,R.id.question16,
        R.id.question17,R.id.question18,
        R.id.question19,R.id.question20,
        R.id.question21,R.id.question22,
        R.id.question23,R.id.question24,
        R.id.question25,R.id.question26,
        R.id.question27,R.id.question28,
        R.id.question29,R.id.question30
};
    private JSONArray myjson;
    private JSONObject myjsons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        String ss=ConnectServer.sendAndReceicveData("[\"fetchMyQuestions\",\""+Login.token+"\"]");
        try {
            myjson = new JSONArray(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<myjson.length();i++) {
            try {
                myjsons = new JSONObject(ConnectServer.sendAndReceicveData("[\"fetchQuestion\",\"" +Login.token+"\",\"" +myjson.getString(i)+"\"]"));
                ((Button) findViewById(question[i])).setText(myjsons.getString("question_text"));
                findViewById(question[i]).setVisibility(View.VISIBLE);
                ((TextView) findViewById(id[i])).setText(String.valueOf(i+1));
                findViewById(id[i]).setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i=myjson.length();i<30;i++) {
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
    public void button_enter_question_1(View view) {
        String question_id="";
        for(int i=0;i<myjson.length();i++)
        {
            if(question[i]==view.getId())
            {
                try {
                    question_id=myjson.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Intent intent = new Intent(this,Class_question.class);
        intent.putExtra("ID",question_id);
        startActivity(intent);
    }

}
