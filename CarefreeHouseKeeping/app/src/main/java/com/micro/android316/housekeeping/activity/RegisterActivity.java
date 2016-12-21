package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;

/**
 * Created by Administrator on 2016/12/13.
 */
public class RegisterActivity extends Activity{

    EditText phoneEdit;
    EditText verificationCodeEdit;
    TextView getVerificationCodeTv;
    EditText loginPasswordEdit;
    Button registerBtn;
    CheckBox registerCheckbox;

    boolean phoneNumberState,passwordState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
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




        registerBtn.setOnClickListener(onClickListener);
    }
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.register_get_verification_code:


                        break;
                    case R.id.register_btn:
                        storageData();
                        Toast.makeText(RegisterActivity.this,"恭喜您注册成功",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

    public void storageData(){
        SharedPreferences sharedPreferences=getSharedPreferences("Carefree", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("phoneNumber",phoneEdit.getText().toString().trim());
        editor.putString("password",loginPasswordEdit.getText().toString().trim());
        editor.commit();
    }





}
