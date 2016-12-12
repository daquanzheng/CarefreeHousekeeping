package com.micro.android316.housekeeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.micro.android316.housekeeping.R;

import java.util.List;



/**
 * Created by Administrator on 2016/12/10.
 */

public class ElderAdapter extends BaseAdapter{
    private Context context;
    private List list;
    private LayoutInflater inflater;
    public ElderAdapter(Context context,List list){
        inflater=LayoutInflater.from(context);
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=inflater.inflate(R.layout.elderly_item,null);


        return convertView;
    }

    class ViewHolder{

    }
}
