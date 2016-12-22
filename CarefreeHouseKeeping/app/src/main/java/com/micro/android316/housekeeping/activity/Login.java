package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;

/**
 * Created by Administrator on 2016/12/19.
 */
public class Login extends Activity{
    EditText loginPhoneEdit;
    EditText loginPasswordEdit;
    CheckBox loginCheckBox;
    TextView forgetPasswordTV;
    TextView registerTV;
    Button loginBtn;
    String phoneNumber;
    String password;
    private int state=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPhoneEdit= (EditText) findViewById(R.id.login_phone_number);
        loginPasswordEdit= (EditText) findViewById(R.id.login_password);
        loginCheckBox= (CheckBox) findViewById(R.id.login_checkbox);
        forgetPasswordTV= (TextView) findViewById(R.id.login_forget_password);
        registerTV= (TextView) findViewById(R.id.login_register_text);
        loginBtn= (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(onClickListener);
        registerTV.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                switch (v.getId()){
                    case R.id.login_btn:
                        getData();
                        if(loginPhoneEdit.getText()!=null&&loginPasswordEdit.getText()!=null
                                &&loginPhoneEdit.getText().length()>0&&loginPasswordEdit.getText().length()>0) {
                            if ((loginPhoneEdit.getText().toString().equals(phoneNumber))
                                    && loginPasswordEdit.getText().toString().equals(password)) {
                                state=1;
                                Toast.makeText(Login.this,"恭喜您登录成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    case R.id.login_register_text:
                        Intent intent=new Intent(Login.this,RegisterActivity.class);
                        startActivity(intent);
                        break;
                }
        }
    };
    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences("Carefree", Context.MODE_PRIVATE);
        phoneNumber=sharedPreferences.getString("phoneNumber","");
        password=sharedPreferences.getString("password","");
    }
}
