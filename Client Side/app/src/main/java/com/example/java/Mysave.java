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

public class Mysave extends AppCompatActivity {
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
    int save[]={
            R.id.save1, R.id.save2,
            R.id.save3, R.id.save4,
            R.id.save5, R.id.save6,
            R.id.save7, R.id.save8,
            R.id.save9, R.id.save10,
            R.id.save11,R.id.save12,
            R.id.save13,R.id.save14,
            R.id.save15,R.id.save16,
            R.id.save17,R.id.save18,
            R.id.save19,R.id.save20,
            R.id.save21,R.id.save22,
            R.id.save23,R.id.save24,
            R.id.save25,R.id.save26,
            R.id.save27,R.id.save28,
            R.id.save29,R.id.save30
    };
    int button_save[]={
            R.id.button_save1, R.id.button_save2,
            R.id.button_save3, R.id.button_save4,
            R.id.button_save5, R.id.button_save6,
            R.id.button_save7, R.id.button_save8,
            R.id.button_save9, R.id.button_save10,
            R.id.button_save11,R.id.button_save12,
            R.id.button_save13,R.id.button_save14,
            R.id.button_save15,R.id.button_save16,
            R.id.button_save17,R.id.button_save18,
            R.id.button_save19,R.id.button_save20,
            R.id.button_save21,R.id.button_save22,
            R.id.button_save23,R.id.button_save24,
            R.id.button_save25,R.id.button_save26,
            R.id.button_save27,R.id.button_save28,
            R.id.button_save29,R.id.button_save30
    };
    int del[]={
            R.id.del1, R.id.del2,
            R.id.del3, R.id.del4,
            R.id.del5, R.id.del6,
            R.id.del7, R.id.del8,
            R.id.del9, R.id.del10,
            R.id.del11,R.id.del12,
            R.id.del13,R.id.del14,
            R.id.del15,R.id.del16,
            R.id.del17,R.id.del18,
            R.id.del19,R.id.del20,
            R.id.del21,R.id.del22,
            R.id.del23,R.id.del24,
            R.id.del25,R.id.del26,
            R.id.del27,R.id.del28,
            R.id.del29,R.id.del30
    };
    private JSONArray myjson;
    private JSONObject myjsons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysave);
        String ss=ConnectServer.sendAndReceicveData("[\"fetchQuestionsFollowed\",\""+Login.token+"\"]");
        try {
            myjson = new JSONArray(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<myjson.length();i++) {
            try {
                myjsons = new JSONObject(ConnectServer.sendAndReceicveData("[\"fetchQuestion\",\"" +Login.token+"\",\"" +myjson.getString(i)+"\"]"));
                ((Button) findViewById(save[i])).setText(myjsons.getString("question_text"));
                findViewById(save[i]).setVisibility(View.VISIBLE);
                ((TextView) findViewById(id[i])).setText(String.valueOf(i+1));
                findViewById(id[i]).setVisibility(View.VISIBLE);
                findViewById(del[i]).setVisibility(View.VISIBLE);
                findViewById(button_save[i]).setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i=myjson.length();i<30;i++) {
            (findViewById(save[i])).setVisibility(View.INVISIBLE);
            (findViewById(id[i])).setVisibility(View.INVISIBLE);
            findViewById(button_save[i]).setVisibility(View.INVISIBLE);
            findViewById(del[i]).setVisibility(View.INVISIBLE);
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

    public void button_enter_question_1(View view) {

    }

    public void button_enter_class_1(View view) {
        String ID = "";
        for (int i = 0; i < myjson.length(); i++) {
            if (button_save[i] == view.getId()) {
                try {
                    myjsons = new JSONObject(ConnectServer.sendAndReceicveData("[\"fetchQuestion\",\"" +Login.token+"\",\"" +myjson.getString(i)+"\"]"));
                    ID =myjsons.getString("topic_id");
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

    public void button_enter_delete_1(View view) {
        String ID = "";
        for (int i = 0; i < myjson.length(); i++) {
            if (del[i] == view.getId()) {
                try {
                    myjsons = new JSONObject(ConnectServer.sendAndReceicveData("[\"fetchQuestion\",\"" +Login.token+"\",\"" +myjson.getString(i)+"\"]"));
                    ID =myjsons.getString("question_id");
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        String ss=ConnectServer.sendAndReceicveData("[\"followQuestion\",\"" +Login.token+"\",\"" +ID+"\",\""+"False"+"\"]");
System.out.println("[\"followQuestion\",\"" +Login.token+"\",\"" +ID+"\",\""+"False"+"\"]");
        if(("True").equals(ss)) {
            Toast.makeText(Mysave.this,"删除成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Mysave.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(Mysave.this,"删除失败",Toast.LENGTH_SHORT).show();
        }
    }
}
