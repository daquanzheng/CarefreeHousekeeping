package com.micro.android316.housekeeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.model.Waitservice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public class WaitserviceAdapter extends BaseAdapter{
    Context context;
    List<Waitservice> waitserviceList = new ArrayList<>();
    LayoutInflater inflater;
    public WaitserviceAdapter(Context context,List<Waitservice> waitserviceList){
        this.context = context;
        this.waitserviceList = waitserviceList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return waitserviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return waitserviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(R.layout.waitservice_indent_item,null);
        }
        return convertView;
    }
}
