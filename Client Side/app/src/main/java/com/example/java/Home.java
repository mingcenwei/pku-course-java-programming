package com.example.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        TextView user = findViewById(R.id.user);
        user.setText(Login.user);
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
    public void button_change_information(View view) {
        Intent intent = new Intent(this,Change_info.class);
        startActivity(intent);
    }
    public void button_about_us(View view) {
        Intent intent = new Intent(this,About_us.class);
        startActivity(intent);
    }
    public void button_rank(View view) {
        Intent intent = new Intent(this,Rank.class);
        startActivity(intent);
    }
    public void button_logout(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}

