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

public class Appointment extends Activity {

    private Button yuYue;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment);
        init();
        yuYue.setOnClickListener(listener);
        back.setOnClickListener(listener);
    }

    private void init(){
        yuYue= (Button) findViewById(R.id.li_ji_yu_yue_button);
        back= (ImageView) findViewById(R.id.back);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.li_ji_yu_yue_button:
                    Intent intent=new Intent(Appointment.this,OrderConfirm.class);
                    startActivity(intent);
                    break;
                case R.id.back:
                    finish();
                    break;
            }
        }
    };
}
