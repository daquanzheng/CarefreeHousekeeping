package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.micro.android316.housekeeping.R;


/**
 * Created by Administrator on 2016/12/12.
 */

public class Payment extends Activity {

    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        init();
        back.setOnClickListener(listener);

    }

    public void init(){
        back= (ImageView) findViewById(R.id.back);
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
