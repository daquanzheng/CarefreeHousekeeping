package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.utils.Topbar;

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
    Topbar loginTopbar;
    private int state=-1;
    private long loginI=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPhoneEdit= (EditText) findViewById(R.id.login_phone_number);
        loginPasswordEdit= (EditText) findViewById(R.id.login_password);
        loginTopbar= (Topbar) findViewById(R.id.login_topBar);
        loginTopbar.setOnTopBarClickListener(new Topbar.topBarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        loginPhoneEdit.setText(phoneNumber);
        loginCheckBox= (CheckBox) findViewById(R.id.login_checkbox);
        forgetPasswordTV= (TextView) findViewById(R.id.login_forget_password);
        registerTV= (TextView) findViewById(R.id.login_register_text);
        loginBtn= (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(onClickListener);
        registerTV.setOnClickListener(onClickListener);
        loginCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }else {

                }
            }
        });
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                switch (v.getId()){
                    case R.id.login_btn:
                        getData();
                        if(password!=null&&password.length()>0&&password.equals(loginPasswordEdit.getText().toString())){
                            Toast.makeText(Login.this, "恭喜您登录成功", Toast.LENGTH_SHORT).show();
                            state=1;
                            loginI++;
                            phoneNumber=loginPhoneEdit.getText().toString();
                            storagePhoneNumber(loginI);
                            Intent intent = new Intent(Login.this, HomeMainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Login.this,"您输入的用户名或者密码有误", Toast.LENGTH_SHORT).show();
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
        if(loginPhoneEdit!=null) {
            password = sharedPreferences.getString(loginPhoneEdit.getText().toString(), "");
        }
    }
    public void storagePhoneNumber(long loginI){
        SharedPreferences sharedPreferences=getSharedPreferences("phone",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(loginI+"",phoneNumber);
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPhoneEdit.setText(phoneNumber);
    }
}
