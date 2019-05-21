package com.example.java;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.java.ConnectServer;
public class Login extends AppCompatActivity {
        public static String token;
        public static String user;
        public static String password;

        public EditText editText1;
        public EditText editText2;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
        }
        public void button_login(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Intent intent = new Intent(this,Mytopic.class);
                try {
                        //获取用户名和密码
                        editText1 = (EditText) findViewById(R.id.user);
                        user = editText1.getText().toString();
                        editText2 = (EditText) findViewById(R.id.password);
                        password = editText2.getText().toString();


                } catch (Exception e) {}
                if(user.replace(" ","").equals("")|
                        password.replace(" ","").equals("")|password.length()<6)
                {
                        Toast.makeText(Login.this,"请输入完整",Toast.LENGTH_LONG).show();
                        Intent intents = new Intent(this,Login.class);

                        editText1.setText(user);
                        editText2.setText(password);
                }
                else {
                       String ss="[\"login\", \""+user+"\",\""+password+"\"]";
                        token=ConnectServer.sendAndReceicveData(ss);
                       if("False".equals(token)) {
                           Toast.makeText(Login.this, "用户不存在，请先注册", Toast.LENGTH_LONG).show();
                       } else{
                                startActivity(intent);
                    }
                }
        }
        public void button_regist(View view) {
                Intent intent = new Intent(this,Register.class);
                startActivity(intent);
        }
        public void button_fogot(View view)
        {
                Toast.makeText(Login.this,"请功能暂且未实现，或许你可以重新注册",Toast.LENGTH_SHORT).show();
        }

}