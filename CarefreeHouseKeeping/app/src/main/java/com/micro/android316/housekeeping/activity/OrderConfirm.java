package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.micro.android316.housekeeping.R;

/**
 * Created by Administrator on 2016/12/12.
 */

public class OrderConfirm extends Activity {

    private Button submit;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirm);
        init();
        submit.setOnClickListener(listener);
        back.setOnClickListener(listener);
    }


    public void init(){
        submit= (Button) findViewById(R.id.submit);
        back= (ImageView) findViewById(R.id.back);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.submit:
                    Intent intent=new Intent(OrderConfirm.this,Payment.class);
                    startActivity(intent);
                    break;
                case R.id.back:
                    finish();
                    break;

            }
        }
    };
}
