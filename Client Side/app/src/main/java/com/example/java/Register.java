package com.example.java;
import com.example.java.ConnectServer;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;

public class Register extends AppCompatActivity {

    String users;
    String repeat_password;
    String new_password;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public boolean check(String s)
    {
        for(int i=0;i<s.length();i++)
        {
            if((s.charAt(i)<='9'&&s.charAt(i)>='0')|(s.charAt(i)<='z'&&s.charAt(i)>='a')|(s.charAt(i)<='Z'&&s.charAt(i)>='A'))
            {

            }
            else
            {
                return false;
            }
        }
        return true;
    }
    public boolean isNull(String s){
        if(s.replace(" ","").equals(""))return true;
        return false;
    }
    public void button_confirm(View view) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        users = ((EditText) findViewById(R.id.user)).getText().toString();
        new_password = ((EditText)findViewById(R.id.password1)).getText().toString();
        repeat_password = ((EditText) findViewById(R.id.password2)).getText().toString();
        mail = ((EditText) findViewById(R.id.email)).getText().toString();
        if (isNull(users)|isNull(new_password)|isNull(repeat_password)) {
            Toast.makeText(Register.this,"请输入用户名或密码",Toast.LENGTH_LONG).show();
        }
        else if(!new_password.equals(repeat_password)) {
            //Toast.makeText(Register.this,new_password,Toast.LENGTH_LONG).show();
            //Toast.makeText(Register.this,repeat_password,Toast.LENGTH_LONG).show();
            Toast.makeText(Register.this,"两次密码不一致",Toast.LENGTH_LONG).show();
        }
        else if(new_password.length()<6|!check(new_password)){Toast.makeText(Register.this,"请输入6-16个数字或者字母",Toast.LENGTH_LONG).show();}
        else {
            String success="True";
            String tt="[\"createNewAccount\", \""+mail+"\", \""+users+"\", \""+new_password+"\"]";

            success=ConnectServer.sendAndReceicveData("[\"createNewAccount\", \""+mail+"\", \""+users+"\", \""+new_password+"\"]");

            if(("True").equals(success)) {
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }
            else {
                System.out.println(success);
                Toast.makeText(Register.this, "注册失败", Toast.LENGTH_LONG).show();
            }
        }
    }

}
