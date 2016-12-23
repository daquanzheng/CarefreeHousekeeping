package com.micro.android316.housekeeping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.activity.BaiduMapActivity;
import com.micro.android316.housekeeping.activity.Category;
import com.micro.android316.housekeeping.activity.NannyInformation;
import com.micro.android316.housekeeping.adapter.HomePageAdapter;
import com.micro.android316.housekeeping.model.HomePageModel;

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
    LinearLayout clean,cooking;
    TextView city;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_listview,null);
        listView = (ListView) view.findViewById(R.id.home_listview);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.homepage_header,null);
        goNurse = (RelativeLayout) view1.findViewById(R.id.go_nurse);
        clean = (LinearLayout) view1.findViewById(R.id.clean_layout);
        cooking = (LinearLayout) view1.findViewById(R.id.cook_layout);
        city = (TextView) view1.findViewById(R.id.city);

        goNurse.setOnClickListener(clickListener);
        clean.setOnClickListener(clickListener);
        cooking.setOnClickListener(clickListener);
        city.setOnClickListener(clickListener);
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
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.city:
                    intent.setClass(getActivity(), BaiduMapActivity.class);
                    startActivityForResult(intent,112);
                    break;
                case R.id.go_nurse:
                    intent.setClass(getActivity(), Category.class);
                    intent.putExtra("location",1);
                    startActivity(intent);
                    break;
                case R.id.clean_layout:
                    intent.setClass(getActivity(), Category.class);
                    intent.putExtra("location",2);
                    startActivity(intent);
                    break;
                case R.id.cook_layout:
                    intent.setClass(getActivity(), Category.class);
                    intent.putExtra("location",3);
                    startActivity(intent);
                    break;
            }
        }
    };

    //定位之后回调，显示位置精确到市
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==112){
            String s = data.getStringExtra("location");
            int j = 0,k = 0;
            for(int i=s.length()-1;i>=0;i--){
                if(s.charAt(i)=='市'){
                    k = i;
                }else if(s.charAt(i)=='国'){
                    j = i;
                }
            }
            String ss = s.substring(j+1,k);
            city.setText(ss);
            Toast.makeText(getActivity(),"已定位到"+s,Toast.LENGTH_SHORT).show();
        }
    }

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
}
