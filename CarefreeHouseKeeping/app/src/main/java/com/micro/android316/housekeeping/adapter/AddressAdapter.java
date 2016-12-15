package com.micro.android316.housekeeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.model.Address;

import java.util.List;
/**
 * Created by Administrator on 2016/12/13.
 */
public class AddressAdapter extends BaseAdapter{
    public List<Address> addressList;
    public Context context;
    public LayoutInflater layoutInflater;


    public AddressAdapter(Context context,List<Address> addressList){
        this.context=context;
        this.addressList=addressList;
        this.layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {

        return addressList.size();
    }

    @Override
    public Object getItem(int position) {

        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_address,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Address address=addressList.get(position);
        viewHolder.nameTv.setText(address.getName());
        viewHolder.phoneTv.setText(address.getPhoneNumber());
        viewHolder.addressTv.setText(address.getAds());
        viewHolder.deleteTv.setText(address.getDelete());
        return convertView;
    }

    private class ViewHolder{
        private TextView nameTv;
        private TextView phoneTv;
        private TextView addressTv;
        private TextView deleteTv;

        public ViewHolder(View view){
            nameTv= (TextView) view.findViewById(R.id.item_address_name);
            phoneTv= (TextView) view.findViewById(R.id.item_address_phone);
            addressTv= (TextView) view.findViewById(R.id.item_address);
            deleteTv= (TextView) view.findViewById(R.id.item_address_delete);
        }
    }

}
