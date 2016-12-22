package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.adapter.MessageAdapter;
import com.micro.android316.housekeeping.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
public class MessageActivity extends Activity{
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        listView= (ListView) findViewById(R.id.listview_message);
        MessageAdapter messageAdapter=new MessageAdapter(this,getData());
        listView.setAdapter(messageAdapter);
    }
    public List<Message> getData(){
        List<Message> messageList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Message message=new Message();
            message.setTitle("系统消息");
            message.setContent("最新的一条新消息");
            message.setTime("下午1：30");
            messageList.add(message);
        }
        return messageList;
    }

}
