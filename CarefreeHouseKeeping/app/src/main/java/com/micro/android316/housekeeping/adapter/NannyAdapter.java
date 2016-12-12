package com.micro.android316.housekeeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.model.NannyInfo;

import java.util.List;



/**
 * Created by Administrator on 2016/12/12.
 */

public class NannyAdapter extends BaseAdapter {
    private List<NannyInfo> list;
    private LayoutInflater inflater;
    private Context context;

    public NannyAdapter(List<NannyInfo> list, Context context) {
        this.list = list;
        inflater=LayoutInflater.from(context);
        this.context = context;
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
        convertView=inflater.inflate(R.layout.nanny_information_item,null);

        return convertView;
    }
}
