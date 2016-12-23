package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.model.MessageVerification;
import com.micro.android316.housekeeping.utils.Topbar;

import cn.smssdk.SMSSDK;


/**
 * Created by Administrator on 2016/12/13.
 */
public class RegisterActivity extends Activity{

    private static final int SUCCESS=100;
    private MessageVerification messageVerification;
    Topbar topbar;
    EditText phoneEdit;
    EditText verificationCodeEdit;
    TextView getVerificationCodeTv;
    EditText loginPasswordEdit;
    Button registerBtn;
    CheckBox registerCheckbox;
    boolean phoneNumberState=false,passwordState=false,isClick=false;
    String isRegister;

    String VerificationCode;
    String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        messageVerification=new MessageVerification(this,handler);
        messageVerification.initInterface();
        topbar= (Topbar) findViewById(R.id.register_topbar);
        topbar.setOnTopBarClickListener(new Topbar.topBarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        phoneEdit= (EditText) findViewById(R.id.register_phone_edit);
        verificationCodeEdit= (EditText) findViewById(R.id.register_verification_code);
        getVerificationCodeTv= (TextView) findViewById(R.id.register_get_verification_code);
        loginPasswordEdit= (EditText) findViewById(R.id.register_login_password);
        registerBtn= (Button) findViewById(R.id.register_btn);
        registerCheckbox= (CheckBox) findViewById(R.id.register_checkbox);
        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().length()==11){
                        phoneNumberState=true;
                    }else {
                        phoneNumberState=false;
                    }
                if(phoneNumberState&&passwordState&&registerCheckbox.isChecked()){
                    registerBtn.setBackgroundResource(R.drawable.register_btn);
                    registerBtn.setClickable(true);
                }else {
                    registerBtn.setBackgroundResource(R.drawable.register_btn_grey);
                    registerBtn.setClickable(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        loginPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().length()>=6){
                        passwordState=true;
                    }else {
                        passwordState=false;
                    }
                if(phoneNumberState&&passwordState&&registerCheckbox.isChecked()){
                    registerBtn.setBackgroundResource(R.drawable.register_btn);
                    registerBtn.setClickable(true);
                }else {
                    registerBtn.setBackgroundResource(R.drawable.register_btn_grey);
                    registerBtn.setClickable(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        registerCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(phoneNumberState&&passwordState&&isChecked){
                    registerBtn.setBackgroundResource(R.drawable.register_btn);
                    registerBtn.setClickable(true);
                }else {
                    registerBtn.setBackgroundResource(R.drawable.register_btn_grey);
                    registerBtn.setClickable(false);
                }
            }
        });
        getVerificationCodeTv.setOnClickListener(onClickListener);
        registerBtn.setOnClickListener(onClickListener);
    }

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {//获取验证码的监听事件
                phoneNumber=phoneEdit.getText().toString();
                VerificationCode=verificationCodeEdit.getText().toString();
                switch (v.getId()){
                    case R.id.register_get_verification_code://获取短信验证码
                        Log.i("re=======", "12112121");
                        if(!isClick) {
                            isClick=true;
                            messageVerification.requestVerificationCode(phoneNumber);
                            waitSend();
                        }
                        break;
                    case R.id.register_btn://提交验证码验证是否通过
                        isRegister(phoneNumber);
                        if(isRegister.length()>0) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("亲！您的手机已注册，是否登录？");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(RegisterActivity.this,Login.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog=builder.create();
                            dialog.show();
                        }else {
                            if (VerificationCode != null&&VerificationCode.length()>0) {
                                messageVerification.commit(phoneNumber, VerificationCode);
                            }else {
                                Toast.makeText(RegisterActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                            }
                        }
                            break;
                }
            }
        };
    public void storageData(){//将手机号与密码储存起来
        SharedPreferences sharedPreferences=getSharedPreferences("Carefree", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(phoneEdit.getText().toString(),loginPasswordEdit.getText().toString());
        editor.commit();
    }
    public void isRegister(String phoneNumber){
        SharedPreferences sharedPreferences=getSharedPreferences("Carefree", Context.MODE_PRIVATE);
        isRegister=sharedPreferences.getString(phoneNumber,"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       messageVerification.Cancel();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 123:
                    int event=msg.arg1;
                    int result=msg.arg2;
                    Object data=msg.obj;
                    if(result==SMSSDK.RESULT_COMPLETE){
                        //回调完成
                        if(event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                            //提交验证码成功
                            Toast.makeText(RegisterActivity.this,"恭喜您注册成功",Toast.LENGTH_SHORT).show();
                            storageData();
                            Intent intent=new Intent(RegisterActivity.this,Login.class);
                            startActivity(intent);
                        }else if(event==SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            //获取验证码成功
                            
                        }else if(event==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                            //返回支持发送验证码的国家列表
                        }
                    }else {
                        ((Throwable)data).printStackTrace();
                        Toast.makeText(RegisterActivity.this,"验证码验证失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    public void waitSend(){
        new Thread(){
            int i=60;
            @Override
            public void run() {
                while (true){
                    Message message=Message.obtain();
                    message.what=SUCCESS;
                    handler.sendMessage(message);
                    if(i==0){
                        isClick=false;
                        break;
                    }
                    i--;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
