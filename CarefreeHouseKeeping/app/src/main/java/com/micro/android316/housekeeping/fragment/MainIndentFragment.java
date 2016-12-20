package com.micro.android316.housekeeping.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.adapter.WaitpayAdapter;
import com.micro.android316.housekeeping.adapter.WaitserviceAdapter;
import com.micro.android316.housekeeping.model.Waitpay;
import com.micro.android316.housekeeping.model.Waitservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class MainIndentFragment extends Fragment {
    ListView waitpayLiseView,waitserviceListView;
    List<Waitpay> waitpayList = new ArrayList<>();
    List<Waitservice> waitserviceList = new ArrayList<>();
    LinearLayout waitappraiseLayout;
    RadioGroup indentRadioGroup;
    RadioButton[] radioButtons = new RadioButton[4];
    WaitpayAdapter waitpayAdapter;
    WaitserviceAdapter waitserviceAdapter;

    LinearLayout all;
    View space;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.indent_main_layout,null);

        waitpayLiseView = (ListView) v.findViewById(R.id.waitpay_listview);
        waitserviceListView = (ListView) v.findViewById(R.id.waitservice_listview);
        waitappraiseLayout = (LinearLayout) v.findViewById(R.id.waitappraise_view);

        space = v.findViewById(R.id.space);
        all = (LinearLayout) v.findViewById(R.id.all_indent);

        waitpayAdapter = new WaitpayAdapter(getActivity(),waitpayList);
        waitpayAdapter.setWaitpayInterface(new WaitpayAdapter.WaitpayInterface() {
            @Override
            public void cancelClick(int id) {
                //取消订单
                final int po = id;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确定取消此订单吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                    URL url = new URL("http://139.199.196.199/android/index.php/home/index/deleteord?id="+po);
                                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                    http.setRequestMethod("GET");
                                    http.setConnectTimeout(5000);
                                    http.connect();
                                    if (http.getResponseCode()==HttpURLConnection.HTTP_OK){
                                        handler.sendEmptyMessage(0);
                                    }
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }

            @Override
            public void goPay(int id) {
                //去付款
            }
        });
        waitpayLiseView.setAdapter(waitpayAdapter);

        waitserviceAdapter = new WaitserviceAdapter(getActivity(),waitserviceList);
//        View v1 = LayoutInflater.from(getActivity()).inflate(R.layout.)
        waitserviceAdapter.setWaitserviceInterface(new WaitserviceAdapter.WaitserviceInterface() {
            @Override
            public void reminderClick(int id) {
                //催单
            }

            @Override
            public void editTimeClick(int id) {
                //修改时间
            }

            @Override
            public void cancelAndRefund(int id) {
                //取消并退款
            }
        });
        waitserviceListView.setAdapter(waitserviceAdapter);

        indentRadioGroup = (RadioGroup) v.findViewById(R.id.indent_radiogroup);
        radioButtons[0] = (RadioButton) v.findViewById(R.id.all);
        radioButtons[1] = (RadioButton) v.findViewById(R.id.waitpay);
        radioButtons[2] = (RadioButton) v.findViewById(R.id.waitservice);
        radioButtons[3] = (RadioButton) v.findViewById(R.id.waitappraise);

        indentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.all:
                        all.setVisibility(View.VISIBLE);
                        waitpayLiseView.setVisibility(View.VISIBLE);
                        space.setVisibility(View.VISIBLE);
                        waitserviceListView.setVisibility(View.VISIBLE);
                        waitappraiseLayout.setVisibility(View.GONE);
                        radioButtons[0].setTextColor(getResources().getColor(R.color.txt_1));
                        radioButtons[1].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[2].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[3].setTextColor(getResources().getColor(R.color.dark_black));
                        break;
                    case R.id.waitpay:
                        all.setVisibility(View.VISIBLE);
                        waitpayLiseView.setVisibility(View.VISIBLE);
                        space.setVisibility(View.GONE);
                        waitserviceListView.setVisibility(View.GONE);
                        waitappraiseLayout.setVisibility(View.GONE);
                        radioButtons[0].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[1].setTextColor(getResources().getColor(R.color.txt_1));
                        radioButtons[2].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[3].setTextColor(getResources().getColor(R.color.dark_black));
                        break;
                    case R.id.waitservice:
                        all.setVisibility(View.VISIBLE);
                        waitpayLiseView.setVisibility(View.GONE);
                        space.setVisibility(View.GONE);
                        waitserviceListView.setVisibility(View.VISIBLE);
                        waitappraiseLayout.setVisibility(View.GONE);
                        radioButtons[0].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[1].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[2].setTextColor(getResources().getColor(R.color.txt_1));
                        radioButtons[3].setTextColor(getResources().getColor(R.color.dark_black));
                        break;
                    case R.id.waitappraise:
                        all.setVisibility(View.GONE);
                        waitappraiseLayout.setVisibility(View.VISIBLE);
                        radioButtons[0].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[1].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[2].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[3].setTextColor(getResources().getColor(R.color.txt_1));
                        break;
                }
            }
        });
        return v;
    }
    //获取所有订单
    public void getAllIndent(){
        String string = "http://139.199.196.199/android/index.php/home/index/getallord?tel=123456";
        try {
            URL url = new URL(string);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setConnectTimeout(5000);
            http.connect();
            if(http.getResponseCode()==HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
                String s;
                StringBuilder stringBuilder = new StringBuilder();
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.e("全部订单-----",stringBuilder.toString());
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("info");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.optJSONObject(i);

                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //获取未付款订单数据
    public void getWaitpayData(){
        String string = "http://139.199.196.199/android/index.php/home/index/hasnoprice?tel=123456";
        try {
            URL url = new URL(string);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setConnectTimeout(5000);
            http.connect();
            if(http.getResponseCode()==200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
                String s;
                StringBuilder stringBuilder = new StringBuilder();
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.i("Data--------------->",stringBuilder.toString());
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("info");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.optJSONObject(i);
                    Waitpay waitpay = new Waitpay();
                    waitpay.setId(object.optInt("id"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
                    waitpay.setTime(sdf.format(new BigDecimal(object.optString("time"))));
                    waitpay.setPlace(object.optString("address"));
                    waitpay.setRange(object.optString(""));
                    waitpayList.add(waitpay);
                }
               handler.sendEmptyMessage(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    String[] types = {"老人护理","婴幼儿护理","家居保洁","家具保洁","烹饪"};
    //获取已付款待服务的订单
    public void getWaitserviseData(){
        String string = "http://139.199.196.199/android/index.php/home/index/payment?tel=123456";
        try {
            URL url = new URL(string);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setConnectTimeout(5000);
            http.connect();
            if(http.getResponseCode()==200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
                String s1;
                StringBuilder stringBuilder1 = new StringBuilder();
                while ((s1=bufferedReader.readLine())!=null){
                    stringBuilder1.append(s1);
                }
                Log.i("data1------------->",stringBuilder1.toString());
                JSONObject jsonObject = new JSONObject(stringBuilder1.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("info");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.optJSONObject(i);
                    Waitservice waitservice = new Waitservice();
                    waitservice.setPicture(object.optString("head"));
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
                    waitservice.setTimes(sdf1.format(new BigDecimal(object.optString("time"))));
                    waitservice.setCurrentPrice(object.optString("price","10元/小时"));
                    waitservice.setOrginalPrice(object.optString("orginalprice","106元/小时"));
                    waitservice.setRanges(types[object.optInt("type",1)-1]);
                    waitserviceList.add(waitservice);
                }
                handler.sendEmptyMessage(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        waitpayList.clear();
        waitserviceList.clear();
        new Thread(){
            @Override
            public void run() {
                getWaitpayData();
                getWaitserviseData();
            }
        }.start();
    }

    public void setListViewHeight(ListView listView){
        BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount()+1));
        listView.setLayoutParams(params);
    }


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
//            Toast.makeText(getActivity(),"成功取消订单",Toast.LENGTH_SHORT).show();
            setListViewHeight(waitpayLiseView);
            setListViewHeight(waitserviceListView);
            waitpayAdapter.notifyDataSetChanged();
            waitserviceAdapter.notifyDataSetChanged();
            return true;
        }
    });
}
