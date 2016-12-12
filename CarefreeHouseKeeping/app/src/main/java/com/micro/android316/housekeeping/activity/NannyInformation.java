package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.adapter.NannyAdapter;
import com.micro.android316.housekeeping.model.NannyInfo;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/12/12.
 */

public class NannyInformation extends Activity {

    private ListView pingLun;
    private List<NannyInfo> list;
    private NannyAdapter nannyAdapter;
    private LayoutInflater inflater;
    private TextView yuYue;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nanny_information);
        createData();
        init();
        yuYue.setOnClickListener(listener);
        back.setOnClickListener(listener);


    }

    private void init(){
        inflater=LayoutInflater.from(this);
        pingLun= (ListView) findViewById(R.id.pinglun_list);
        nannyAdapter=new NannyAdapter(list,this);
        pingLun.setAdapter(nannyAdapter);
        View view=inflater.inflate(R.layout.nanny_information_head,null);
        yuYue= (TextView) view.findViewById(R.id.li_ji_yu_yue);
        pingLun.addHeaderView(view);
        back= (ImageView) findViewById(R.id.back);

    }

    private void createData(){
        list=new ArrayList<>();
        list.add(new NannyInfo("1","2","3"));
        list.add(new NannyInfo("1","2","3"));
        list.add(new NannyInfo("1","2","3"));
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.li_ji_yu_yue:
                    Intent intent=new Intent(NannyInformation.this,Appointment.class);
                    startActivity(intent);
                    break;
                case R.id.back:
                    finish();
                    break;
            }
        }
    };
}
