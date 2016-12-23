package com.micro.android316.housekeeping.model;

import android.content.Context;
import android.os.*;
import android.os.Message;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/12/21.
 */
public class MessageVerification {
    private static final String APP_KEY="1a1833a7473c6";
    private static final String APP_SECRET="d3c2e87c6bce09baa702f26a7c7e1e70";
    private static final String COUNTRY="86";
    private Handler handler;
    private Context context;
    public MessageVerification(Context context,Handler handler){
        this.handler=handler;
        this.context=context;
    }
    EventHandler eventHandler=new EventHandler(){
        @Override
        public void afterEvent(int i, int i1, Object o) {
            Message message=Message.obtain();
            message.arg1=i;
            message.arg2=i1;
            message.obj=o;
            message.what=123;
            handler.sendMessage(message);
        }
    };
    public void initInterface(){//初始化接口
        SMSSDK.initSDK(context,APP_KEY,APP_SECRET);
        SMSSDK.registerEventHandler(eventHandler);
    }
    public void Cancel(){//注销回调接口
        SMSSDK.unregisterEventHandler(eventHandler);
    }
    public void requestVerificationCode(String phoneNumber){//请求验证码到手机上

        SMSSDK.getVerificationCode(COUNTRY,phoneNumber, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {

                return false;
            }
        });
    }
    public void commit(String phoneNumber,String code){
        SMSSDK.submitVerificationCode(COUNTRY,phoneNumber,code);
    }
}
