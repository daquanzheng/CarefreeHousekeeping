package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.os.Bundle;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.utils.Topbar;

/**
 * Created by Administrator on 2016/12/13.
 */
public class PersonalInformationActivity extends Activity{
    Topbar topbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information);
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
