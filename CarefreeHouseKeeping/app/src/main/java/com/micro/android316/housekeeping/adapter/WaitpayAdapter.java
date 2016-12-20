package com.micro.android316.housekeeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.model.Waitpay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class WaitpayAdapter extends BaseAdapter {
    Context context;
    List<Waitpay> indentList = new ArrayList<>();
    LayoutInflater inflater;

    WaitpayInterface waitpayInterface;

    public WaitpayInterface getWaitpayInterface() {
        return waitpayInterface;
    }

    public void setWaitpayInterface(WaitpayInterface waitpayInterface) {
        this.waitpayInterface = waitpayInterface;
    }

    public interface WaitpayInterface{
        public void cancelClick(int id);
        public void goPay(int id);
    }
    public WaitpayAdapter(Context context,List<Waitpay> indentList){
        this.context = context;
        this.indentList = indentList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return indentList.size();
    }

    @Override
    public Object getItem(int position) {
        return indentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.waitpay_indent_item,null);
            viewHolder.range = (TextView) convertView.findViewById(R.id.what_service);
            viewHolder.time = (TextView) convertView.findViewById(R.id.what_time);
            viewHolder.place = (TextView) convertView.findViewById(R.id.what_place);
            viewHolder.cancel = (TextView) convertView.findViewById(R.id.cancel_this);
            viewHolder.pay = (TextView) convertView.findViewById(R.id.go_pay);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        Waitpay waitpay = indentList.get(position);
        viewHolder.range.setText(waitpay.getRange());
        viewHolder.time.setText(waitpay.getTime());
        viewHolder.place.setText(waitpay.getPlace());

        final int id = position;
        viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waitpayInterface.cancelClick(id);
            }
        });
        viewHolder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waitpayInterface.goPay(id);
            }
        });

        return convertView;
    }
    private class ViewHolder{
        TextView range,time,place,cancel,pay;
    }
}
