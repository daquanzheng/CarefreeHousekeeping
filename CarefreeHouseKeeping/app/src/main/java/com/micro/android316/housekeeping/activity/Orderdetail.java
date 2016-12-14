package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Orderdetail extends Activity {
    private TextView delete,onlinePerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        init();
    }
    public void init(){
        delete=(TextView)findViewById(R.id.textview_delete_order);
        onlinePerson=(TextView)findViewById(R.id.textview_online_person);

        delete.setOnClickListener(onClickListener);
        onlinePerson.setOnClickListener(onClickListener);
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
