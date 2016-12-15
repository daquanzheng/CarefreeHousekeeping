package com.micro.android316.housekeeping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.activity.Category;
import com.micro.android316.housekeeping.adapter.HomePageAdapter;
import com.micro.android316.housekeeping.model.HomePageModel;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_listview,null);
        listView = (ListView) view.findViewById(R.id.home_listview);
        homePageAdapter = new HomePageAdapter(getActivity(),getlists());
        listView.setAdapter(homePageAdapter);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.homepage_header,null);
        goNurse = (RelativeLayout) view1.findViewById(R.id.go_nurse);
        clean = (TextView) view1.findViewById(R.id.clean_text1);
        cooking = (TextView) view1.findViewById(R.id.cooking_text1);

        goNurse.setOnClickListener(clickListener);
        clean.setOnClickListener(clickListener);
        cooking.setOnClickListener(clickListener);
        listView.addHeaderView(view1);
        return view;
    }
    Intent intent = new Intent();
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
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

    public List<HomePageModel> getlists() {
        for(int i=0;i<3;i++){
            HomePageModel hpm = new HomePageModel();
            lists.add(hpm);
        }
        return lists;
    }

}
