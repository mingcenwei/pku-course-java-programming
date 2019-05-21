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

public class Rank extends AppCompatActivity {
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
            R.id.number21
    };
    int rank[]={
            R.id.Number1,R.id.Number2,
            R.id.Number3,R.id.Number4,
            R.id.Number5,R.id.Number6,
            R.id.Number7,R.id.Number8,
            R.id.Number9,R.id.Number10,
            R.id.Number11,R.id.Number12,
            R.id.Number13,R.id.Number14,
            R.id.Number15,R.id.Number16,
            R.id.Number17,R.id.Number18,
            R.id.Number19,R.id.Number20,
            R.id.Number21
    };
    private JSONArray myjson;
    private JSONObject myjsons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        String ss=ConnectServer.sendAndReceicveData("[\"getTopTenAccounts\",\""+Login.token+"\"]");
        try {
            myjson = new JSONArray(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<myjson.length();i++) {
            try {

                ((TextView) findViewById(id[i])).setText(myjson.getString(i));
                findViewById(id[i]).setVisibility(View.VISIBLE);
                ((TextView) findViewById(rank[i])).setText(String.valueOf(i+1));
                findViewById(rank[i]).setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i=myjson.length();i<21;i++) {
            (findViewById(rank[i])).setVisibility(View.INVISIBLE);
            (findViewById(id[i])).setVisibility(View.INVISIBLE);
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
}
