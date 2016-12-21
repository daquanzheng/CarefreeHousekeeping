package com.micro.android316.housekeeping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.adapter.AddressAdapter;
import com.micro.android316.housekeeping.model.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class CommonAddressActivity extends Activity{
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_address);
        listView= (ListView) findViewById(R.id.listView_address);
        AddressAdapter addressAdapter=new AddressAdapter(this,getData());
        listView.setAdapter(addressAdapter);
    }

    public List<Address> getData(){
        List<Address> addressList=new ArrayList<>();
        for(int i=0;i<4;i++){
            Address address=new Address();
            address.setName("蓝惠豪");
            address.setPhoneNumber("139****1523");
            address.setAds("重庆 重庆市 垫江县太平镇新风3社56号");
            address.setDelete("删除");
            addressList.add(address);
        }
     return  addressList;
    }
}
