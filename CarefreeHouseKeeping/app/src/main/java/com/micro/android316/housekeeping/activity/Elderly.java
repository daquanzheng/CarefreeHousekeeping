package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.adapter.ElderAdapter;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/12/10.
 */

public class Elderly extends Activity{

    private  ListView listView;
    private ElderAdapter adapter;
    private List list;
    private LayoutInflater inflater;
    private TextView title;
    private Intent intent;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elderly);
        init();
        title.setText(intent.getStringExtra("name"));
        getDate();
        adapter=new ElderAdapter(this,list);
        listView.setAdapter(adapter);
        View view=inflater.inflate(R.layout.elderly_item_head,null);
        listView.addHeaderView(view);
        listViewSetOnclick();
        back.setOnClickListener(listener);

    }

    private void init(){
        listView= (ListView) findViewById(R.id.list_view);
        inflater=LayoutInflater.from(this);
        title= (TextView) findViewById(R.id.title);
        intent=getIntent();
        back= (ImageView) findViewById(R.id.back);
    }

    public void getDate()
    {
        list=new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
    }

    public void listViewSetOnclick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Elderly.this, NannyInformation.class);
                startActivity(intent);
            }
        });

    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
            }
        }
    };
}
