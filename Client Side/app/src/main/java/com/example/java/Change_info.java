package com.example.java;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Change_info extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
    }
    public void button_change_head(View view) {

    }
    public void button_confirm(View view) {
        String user;
        String password;
        String new_password;
        String repeat_password;
        user=(((EditText) findViewById(R.id.user)).getText()).toString();
        password= ((EditText) findViewById(R.id.password)).getText().toString();
        new_password = ((EditText) findViewById(R.id.new_password)) .getText().toString();
        repeat_password = ((EditText) findViewById(R.id.repeat_password)).getText().toString();
        if((!user.equals(Login.user)||(!password.equals(Login.password)))){
            Toast.makeText(Change_info.this,"请不要改变原来的登录信息",Toast.LENGTH_LONG).show();
        }
        else if(!repeat_password.equals(new_password)){
            Toast.makeText(Change_info.this,"修改的密码不一致",Toast.LENGTH_LONG).show();
        }
        else {
            String ss=ConnectServer.sendAndReceicveData("[\"modifyPassword\", \""+Login.token+"\",\""+password+"\",\""+new_password+"\"]");
            System.out.println("[\" modifyPassword\", \""+Login.token+"\",\""+password+"\",\""+new_password+"\"]"+ss);
            if(("False").equals(ss)) {
                Toast.makeText(Change_info.this,"失败",Toast.LENGTH_LONG).show();
            }
            if(("True").equals(ss)) {
                Toast.makeText(Change_info.this,"成功",Toast.LENGTH_LONG).show();
            }
        }


    }
}
