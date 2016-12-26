package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.utils.Topbar;

/**
 * Created by Administrator on 2016/12/13.
 */
public class PersonalInformationActivity extends Activity{
    Topbar topbar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information);
        textView= (TextView) findViewById(R.id.personal_head_title);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PersonalInformationActivity.this,Login.class);
                startActivity(intent);
            }
        });
        topbar= (Topbar) findViewById(R.id.personal_information_topBar);
        topbar.setOnTopBarClickListener(new Topbar.topBarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }
            @Override
            public void rightClick() {

            }
        });
    }
}
