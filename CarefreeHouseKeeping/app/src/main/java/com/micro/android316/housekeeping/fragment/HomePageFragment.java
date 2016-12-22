package com.micro.android316.housekeeping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.activity.Category;
import com.micro.android316.housekeeping.activity.NannyInformation;
import com.micro.android316.housekeeping.adapter.HomePageAdapter;
import com.micro.android316.housekeeping.model.HomePageModel;
import com.micro.android316.housekeeping.utils.MyLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/12.
 */
public class HomePageFragment extends Fragment{
    List<HomePageModel> lists = new ArrayList<>();
    ListView listView;
    HomePageAdapter homePageAdapter;
    RelativeLayout goNurse;
    TextView clean,cooking;
    ImageView getLocation;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_listview,null);
        listView = (ListView) view.findViewById(R.id.home_listview);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.homepage_header,null);
        goNurse = (RelativeLayout) view1.findViewById(R.id.go_nurse);
        clean = (TextView) view1.findViewById(R.id.clean_text1);
        cooking = (TextView) view1.findViewById(R.id.cooking_text1);
        getLocation = (ImageView) view1.findViewById(R.id.get_position);

        goNurse.setOnClickListener(clickListener);
        clean.setOnClickListener(clickListener);
        cooking.setOnClickListener(clickListener);
        getLocation.setOnClickListener(clickListener);
        listView.addHeaderView(view1);
        homePageAdapter = new HomePageAdapter(getActivity(),lists);
        homePageAdapter.setClickListener(new HomePageAdapter.ClickListener() {
            @Override
            public void convertClick(int id) {
                intent.setClass(getActivity(),NannyInformation.class);
                intent.putExtra("id",id+"");
                startActivity(intent);
            }
        });
        listView.setAdapter(homePageAdapter);
        lists.clear();
        new Thread(){
            @Override
            public void run() {
                getData();
            }
        }.start();
        return view;
    }
    Intent intent = new Intent();
    public LocationClient mLocationClient = null;
    int kk=0;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.get_position:
                    kk++;
                    MyLocation myLocation = new MyLocation(){
                        @Override
                        public void onReceiveLocation(BDLocation location) {
                            super.onReceiveLocation(location);
                        }
                    };
                    mLocationClient = new LocationClient(getActivity());
                    mLocationClient.registerLocationListener(myLocation);
                    initLocation();
                    if(kk%2!=0){
                        mLocationClient.start();
                    }else {
                        mLocationClient.stop();
                    }
                    break;
                case R.id.go_nurse:
                    intent.setClass(getActivity(), Category.class);
                    intent.putExtra("location",1);
                    startActivity(intent);
                    break;
                case R.id.clean_text1:
                    intent.setClass(getActivity(), Category.class);
                    intent.putExtra("location",2);
                    startActivity(intent);
                    break;
                case R.id.cooking_text1:
                    intent.setClass(getActivity(), Category.class);
                    intent.putExtra("location",3);
                    startActivity(intent);
                    break;
            }
        }
    };



    @Override
    public void onResume() {
        super.onResume();
    }

    String[] types = {"老人护理","婴幼儿护理","家居保洁","家具保洁","烹饪"};
    public void getData(){
        String string = "http://139.199.196.199/android/index.php/home/index/getnannysinfomation?type=1";
        try {
            URL url = new URL(string);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setConnectTimeout(5000);
            http.connect();
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
                String s;
                StringBuilder stringBuilder = new StringBuilder();
                while((s=reader.readLine())!=null){
                    stringBuilder.append(s);
                }
//                Log.i("data==========>",stringBuilder.toString());
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("info");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.optJSONObject(i);
                    HomePageModel homePageModel = new HomePageModel();
                    homePageModel.setId(object.optInt("id"));
                    homePageModel.setName(object.optString("name"));
                    homePageModel.setPicture(object.optString("head"));
                    homePageModel.setAge(object.optInt("age"));
                    homePageModel.setBriefInduction(object.optString("introduction"));
                    homePageModel.setWorkRanges(types[object.optInt("type")-1]);
                    homePageModel.setWorkYears(object.optInt("worktime"));
                    homePageModel.setNumStars(object.optDouble("stars"));
                    homePageModel.setBrowseTimes(object.optInt("follow_count"));
                    homePageModel.setCommentTimes(object.optInt("fabulous"));
//                    Log.i("homePageModel----->",homePageModel.toString());
                    lists.add(homePageModel);
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
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            homePageAdapter.notifyDataSetChanged();
            return true;
        }
    });

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
}
