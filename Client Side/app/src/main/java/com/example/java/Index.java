package com.example.java;
import com.example.java.ConnectServer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Index extends AppCompatActivity {
    public static int Index_id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
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

    public void button_professional(View view) {
        Intent intent = new Intent(this,Topics.class);
        intent.putExtra("ID", "1");
        startActivity(intent);
    }
    public void button_English(View view) {
        Intent intent = new Intent(this,Topics.class);
        intent.putExtra("ID", "2");
        startActivity(intent);
    }
    public void button_PE(View view) {
        Intent intent = new Intent(this,Topics.class);
        intent.putExtra("ID","3");
        startActivity(intent);
    }
    public void button_political(View view) {
        Intent intent = new Intent(this,Topics.class);
        intent.putExtra("ID", "4");
        startActivity(intent);
    }
    public void button_optional(View view) {
        Intent intent = new Intent(this,Topics.class);
        intent.putExtra("ID","5");
        startActivity(intent);
    }
    public void button_public(View view) {
        Intent intent = new Intent(this,Topics.class);
        intent.putExtra("ID", "6");
        startActivity(intent);
    }
    public void button_all(View view) {
        Intent intent = new Intent(this,Topics.class);
        intent.putExtra("ID", "7");
        startActivity(intent);
    }
}
