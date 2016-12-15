package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.micro.android316.housekeeping.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/12/12.
 */
public class OnlineOnsultant extends Activity {
    private ImageView back,sounds;
    private EditText chat;
    private TextView send;
    private ListView listView;
    private String strName,strContent,strHead;
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_onsultant);
        init();
    }
    public void init(){
        back=(ImageView)findViewById(R.id.img_online_back);
        sounds=(ImageView)findViewById(R.id.img_online_sounds);
        chat=(EditText)findViewById(R.id.edit_online_words);
        send=(TextView)findViewById(R.id.textview_online_send);
        listView=(ListView)findViewById(R.id.listview);

        back.setOnClickListener(onClickListener);
        sounds.setOnClickListener(onClickListener);
        send.setOnClickListener(onClickListener);
    }
    //http://192.168.7.2/Picture/mall01.png
    //http://192.168.7.2//index.php/Home/Index/chat?name=
    public void saveDate(){
        String httpURL="http://192.168.7.2//index.php/Home/Index/chat?name="+strName+"&content="+strContent+"&head="+strHead;

        try {
            URL url=new URL(httpURL);
            StringBuilder stringBuilder=new StringBuilder();
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
                String str;
                if((str=bufferedReader.readLine().toString())!=null){
                   stringBuilder.append(str);
                }
                JSONObject jsonObject=new JSONObject(stringBuilder.toString());
                if (jsonObject.getString("status").equals("1")){
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++){

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
