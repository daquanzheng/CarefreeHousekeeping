package com.micro.android316.housekeeping.model;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/12/13.
 */
public class ChatContent {
    String chat;
    String name;
    Bitmap head;

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getHead() {
        return head;
    }

    public void setHead(Bitmap head) {
        this.head = head;
    }
}
