package com.example.java;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Topics extends AppCompatActivity {
    String ids;
    private JSONArray myjson;
   public  int id[]=
            {
                    R.id.id1,R.id.id2,R.id.id3,
                    R.id.id4,R.id.id5,R.id.id6,
                    R.id.id7,R.id.id8,R.id.id9,
                    R.id.id10,R.id.id11,R.id.id12,R.id.id13,
                    R.id.id14,R.id.id15,R.id.id16,
            };
    public  int idss[]=
            {
                    R.id.ids1,R.id.ids2,R.id.ids3,
                    R.id.ids4,R.id.ids5,R.id.ids6,
                    R.id.ids7,R.id.ids8,R.id.ids9,
                    R.id.ids10,R.id.ids11,R.id.ids12,R.id.ids13,
                    R.id.ids14,R.id.ids15,R.id.ids16,
            };
            public int topic[]=
                    {
                            R.id.topic1,R.id.topic2,R.id.topic3,
                            R.id.topic4,R.id.topic5,R.id.topic6,
                            R.id.topic7,R.id.topic8,R.id.topic9,
                            R.id.topic10,R.id.topic11,R.id.topic12,R.id.topic13,
                            R.id.topic14,R.id.topic15,R.id.topic16,

                    };
            public int button[]=
                    {
                            R.id.button1,R.id.button2,R.id.button3,
                            R.id.topic4,R.id.topic5,R.id.button6,
                            R.id.button7,R.id.button8,R.id.button9,
                            R.id.button10,R.id.button11,R.id.button12,R.id.button13,
                            R.id.button14,R.id.button15,R.id.button16,
                    };

    int[] p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        Intent intent = getIntent();
        ids = intent.getStringExtra("ID");

        String ss = ConnectServer.sendAndReceicveData("[\"fetchTopics\", \""+Login.token+"\"]");
        if(ids.equals("1"))
        {
            ((TextView)findViewById(R.id.elective_class)).setText("专业课");
        }
        if(ids.equals("2"))
        {
            ((TextView)findViewById(R.id.elective_class)).setText("英语课");
        }
        if(ids.equals("3"))
        {
            ((TextView)findViewById(R.id.elective_class)).setText("体育课");
        }
        if(ids.equals("4"))
        {
            ((TextView)findViewById(R.id.elective_class)).setText("政治课");
        }
        if(ids.equals("5"))
        {
            ((TextView)findViewById(R.id.elective_class)).setText("通选课");
        }
        if(ids.equals("6"))
        {
            ((TextView)findViewById(R.id.elective_class)).setText("公选课");
        }
        if(ids.equals("7"))
        {
            ((TextView)findViewById(R.id.elective_class)).setText("全部");
        }
        try {
            myjson = new JSONArray(ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int count=0;
        p=new int[myjson.length()+1];
        for(int i=0;i<myjson.length();i++) {
            try {
                JSONObject myjObject = myjson.getJSONObject(i);
                if(myjObject.getString("topic_description").equals(ids)) {
                    ((TextView) findViewById(topic[count])).setText(myjObject.getString("topic_title"));
                    findViewById(topic[count]).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(id[count])).setText(
                            ConnectServer.sendAndReceicveData("[\"getNumberOfUnansweredQuestionsUnderTopic\", " +
                            "\""+Login.token+"\",\""+myjObject.get("topic_id")+"\"]"));
                    System.out.println("%%%%%%%%%%%%"+ConnectServer.sendAndReceicveData("[\"getNumberOfUnansweredQuestionsUnderTopic\", " +
                            "\""+Login.token+"\",\""+myjObject.get("topic_id")+"\"]")+"   "+myjObject.get("topic_id"));
                    findViewById(id[count]).setVisibility(View.VISIBLE);
                    findViewById(idss[count]).setVisibility(View.VISIBLE);

                    p[count]=i;
                   count++;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i=count;i<16;i++) {
            findViewById(id[i]).setVisibility(View.INVISIBLE);
            findViewById(button[i]).setVisibility(View.INVISIBLE);
             findViewById(topic[i]).setVisibility(View.INVISIBLE);
            findViewById(idss[i]).setVisibility(View.INVISIBLE);
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
    public void button_mysave(View view) throws JSONException {

        Intent intent = new Intent(this,Mysave.class);
        startActivity(intent);
    }
    public void button1(View view) throws JSONException {
        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",(myjson.getJSONObject(p[0])).getString("topic_id"));
        startActivity(intent);
    }
    public void button2(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",(myjson.getJSONObject(p[1])).getString("topic_id"));
        startActivity(intent);
    }
    public void button3(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",(myjson.getJSONObject(p[2])).getString("topic_id"));
        startActivity(intent);
    }
    public void button4(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[3]).getString("topic_id"));
        startActivity(intent);
    }
    public void button5(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[4]).getString("topic_id"));
        startActivity(intent);
    }
    public void button6(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[5]).getString("topic_id"));
        startActivity(intent);
    }
    public void button7(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[6]).getString("topic_id"));
        startActivity(intent);
    }
    public void button8(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[7]).getString("topic_id"));
        startActivity(intent);
    }
    public void button9(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[8]).getString("topic_id"));
        startActivity(intent);
    }
    public void button10(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[9]).getString("topic_id"));
        startActivity(intent);
    }
    public void button11(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[10]).getString("topic_id"));
        startActivity(intent);
    }
    public void button12(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[11]).getString("topic_id"));
        startActivity(intent);
    }
    public void button13(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[12]).getString("topic_id"));
        startActivity(intent);
    }
    public void button14(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[13]).getString("topic_id"));
        startActivity(intent);
    }
    public void button15(View view) throws JSONException {

        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[14]).getString("topic_id"));
        startActivity(intent);
    }
    public void button16(View view) throws JSONException {
        Intent intent = new Intent(this,Classes.class);
        intent.putExtra("ID",myjson.getJSONObject(p[15]).getString("topic_id"));
        startActivity(intent);
    }

}
