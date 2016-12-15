package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.utils.HttpTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2016/12/12.
 */

public class Payment extends Activity {

    private ImageView back;
    private String id,price;
    private CheckBox weiXin,zhiFuBao;
    private Button tiJiao;
    private static final String URL="http://139.199.196.199/android/index.php/home/index/changeord?";
    private static final int CODE_ZHI_FU=0;
    private static final int CODE_COUNT_DOWN=1;
    private TextView countDown;
    private long time=1800*1000;
    private TextView fuKuan1,fuKuan2;
    //剩余支付时间： 30:00
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        init();
        back.setOnClickListener(listener);
        weiXin.setOnClickListener(listener);
        zhiFuBao.setOnClickListener(listener);
        tiJiao.setOnClickListener(listener);
        countDown();

    }

    public void init(){
        back= (ImageView) findViewById(R.id.back);
        id=getIntent().getStringExtra("id");
        price=getIntent().getStringExtra("price");
        weiXin= (CheckBox) findViewById(R.id.wei_xin);
        zhiFuBao= (CheckBox) findViewById(R.id.zi_fu_bao);
        tiJiao= (Button) findViewById(R.id.ti_jiao);
        countDown= (TextView) findViewById(R.id.count_down);
        fuKuan1= (TextView) findViewById(R.id.price1);
        fuKuan2= (TextView) findViewById(R.id.price2);
        //订单金额： ￥60.00
        fuKuan1.setText("订单金额： ￥"+price+".00");
        fuKuan2.setText("实付金额： ￥"+price+".00");
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.wei_xin:
                    weiXin.setChecked(true);
                    zhiFuBao.setChecked(false);
                    break;
                case R.id.zi_fu_bao:
                    weiXin.setChecked(false);
                    zhiFuBao.setChecked(true);
                    break;
                case R.id.ti_jiao:
                    tiJiao();
                    break;
            }
        }
    };

    public void tiJiao(){

        String value="id="+id+"&state=1";

        HttpTools tools=new HttpTools(URL+value) {
            @Override
            public void post(String line) {
                try {
                    JSONObject object=new JSONObject(line);
                    if(object.getString("code").equals("1")){
                        handler.sendEmptyMessage(CODE_ZHI_FU);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        tools.runForGet();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case CODE_ZHI_FU:
                    Toast.makeText(Payment.this,"支付成功...即将返回主页面",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Payment.this,HomeMainActivity.class);
                    startActivity(intent);
                    break;
                case CODE_COUNT_DOWN:
                    SimpleDateFormat format=new SimpleDateFormat("mm:ss");
                    countDown.setText("剩余支付时间： "+format.format(new Date(time)));
                    if(time<=0){
                        finish();
                    }
                    break;

            }

        }
    };

    public void countDown(){
        new Thread(){
            public void run(){
                while(time>=0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(CODE_COUNT_DOWN);
                    time-=1000;
                }

            }
        }.start();
    }

}
