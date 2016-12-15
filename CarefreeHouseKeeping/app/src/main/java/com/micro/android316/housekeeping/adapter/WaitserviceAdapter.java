package com.micro.android316.housekeeping.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.model.Waitservice;
import com.micro.android316.housekeeping.utils.LoadImage;

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
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.waitservice_indent_item,null);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.waitservice_pc);
            viewHolder.range = (TextView) convertView.findViewById(R.id.waitservice_range);
            viewHolder.time = (TextView) convertView.findViewById(R.id.waitservice_time);
            viewHolder.currentPrice = (TextView) convertView.findViewById(R.id.waitservice_current_price);
            viewHolder.orginalPrice = (TextView) convertView.findViewById(R.id.waitservice_orginal_price);
            viewHolder.reminder = (TextView) convertView.findViewById(R.id.reminder);
            viewHolder.editTime = (TextView) convertView.findViewById(R.id.edit_time);
            viewHolder.cancelIndent = (TextView) convertView.findViewById(R.id.cancel_indent);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
//        Waitservice waitservice = waitserviceList.get(position);
//        LoadImage.Load(viewHolder.picture,waitservice.getPicture(),context);
//        viewHolder.range.setText(waitservice.getRanges());
//        viewHolder.time.setText(waitservice.getTimes());
//        viewHolder.currentPrice.setText(waitservice.getCurrentPrice());
//        viewHolder.orginalPrice.setText(waitservice.getOrginalPrice());
        //普通价格加中划线
        viewHolder.orginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //设置抗锯齿，以免失真
        viewHolder.orginalPrice.getPaint().setAntiAlias(true);
        return convertView;
    }
    private class ViewHolder{
        ImageView picture;
        TextView range,time,currentPrice,orginalPrice,reminder,editTime,cancelIndent;
    }
}
