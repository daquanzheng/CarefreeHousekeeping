package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Orderdetail extends Activity {
    private TextView delete,onlinePerson,type,numtime,price,money,moneyInfact,serviceTime,serviceAddress,orderNum;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        init();
    }
    public void init(){
        delete=(TextView)findViewById(R.id.textview_delete_order);
        onlinePerson=(TextView)findViewById(R.id.textview_online_person);
        back=(ImageView)findViewById(R.id.img_orderdetail_back);
        type=(TextView)findViewById(R.id.textview_od_type);
        numtime=(TextView)findViewById(R.id.textview_od_numtime);
        price=(TextView)findViewById(R.id.textview_od_price);
        money=(TextView)findViewById(R.id.textview_od_money);
        moneyInfact=(TextView)findViewById(R.id.textview_od_moneyinfact);
        serviceTime=(TextView)findViewById(R.id.textview_od_servicetime);
        serviceAddress=(TextView)findViewById(R.id.textview_od_serviecaddress);
        orderNum=(TextView)findViewById(R.id.textview_od_ordernum);

        delete.setOnClickListener(onClickListener);
        onlinePerson.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.textview_delete_order:
                    createDialog();
                    break;
                case R.id.textview_online_person:
                    Intent intent=new Intent(Orderdetail.this,OnlineOnsultant.class);
                    startActivity(intent);
                    break;
                case R.id.img_orderdetail_back:
                    finish();
                    break;

            }
        }
    };
    public void createDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("您确认要删除订单吗？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(){
                            @Override
                            public void run() {
                                String string = "http://139.199.196.199/android/index.php/home/index/deleteord?id=";
                                try {
                                    URL url = new URL(string);
                                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                    http.setRequestMethod("GET");
                                    http.setConnectTimeout(5000);
                                    http.connect();
                                    if (http.getResponseCode()==HttpURLConnection.HTTP_OK){
                                        Log.i("url------------->",string);
                                    }
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        Toast.makeText(Orderdetail.this,"您的订单已删除",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Orderdetail.this,"您的订单已保留",Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
