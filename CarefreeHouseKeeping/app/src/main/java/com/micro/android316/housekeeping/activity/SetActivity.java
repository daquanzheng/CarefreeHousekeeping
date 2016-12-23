package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.os.Bundle;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.utils.Topbar;

/**
 * Created by Administrator on 2016/12/20.
 */
public class SetActivity extends Activity{

    Topbar setTopbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        setTopbar= (Topbar) findViewById(R.id.set_topbar);
        setTopbar.setOnTopBarClickListener(new Topbar.topBarClickListener() {
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
