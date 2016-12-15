package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.micro.android316.housekeeping.R;

/**
 * Created by Administrator on 2016/12/12.
 */
public class FeedBack extends Activity {
    private TextView commit;
    private ImageView add,back;
    private EditText idea,phone;
    private String strIdea,strPhone;
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.img_feedback_back:
                    finish();
                    break;
                case R.id.img_feedback_add:
                    break;
                case R.id.textview_feedback_commit:
                    strIdea=idea.getText().toString();
                    strPhone=phone.getText().toString();
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            saveDate();
                        }
                    }.start();
                    break;
            }
        }
    };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.arg1){
               case 1:
                   Toast.makeText(FeedBack.this,msg.obj.toString(),Toast.LENGTH_LONG).show();
                   break;
               case -1:
                   Toast.makeText(FeedBack.this,msg.obj.toString(),Toast.LENGTH_SHORT).show();
                   break;
           }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init();
    }
    public void init(){
        commit=(TextView)findViewById(R.id.textview_feedback_commit);
        back=(ImageView)findViewById(R.id.img_feedback_back);
        add=(ImageView)findViewById(R.id.img_feedback_add);
        idea=(EditText)findViewById(R.id.edit_feedback_idea);
        phone=(EditText)findViewById(R.id.edit_feedback_phone);

        add.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
        commit.setOnClickListener(onClickListener);
    }
    public void saveDate(){
        String httpURL="http://192.168.7.3//index.php/Home/Index/feedback?idea="+strIdea+"&phone="+strPhone;
        try {
            StringBuilder stringBuilder=new StringBuilder();
            URL url=new URL(httpURL);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200){
                String str;
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
                if((str=bufferedReader.readLine())!=null){
                    stringBuilder.append(str);
                }
                JSONObject jsonObject=new JSONObject(stringBuilder.toString());
                String code=jsonObject.getString("status");
                if(code.equals("1")){
                    Message message=new Message();
                    message.arg1=1;
                    message.obj="您的建议或问题我们已经收到,十分感谢您";
                    handler.sendMessage(message);
                }else {
                    Message message=new Message();
                    message.arg1=-1;
                    message.obj="提交失败，请重试";
                    handler.sendMessage(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }


    }
}
