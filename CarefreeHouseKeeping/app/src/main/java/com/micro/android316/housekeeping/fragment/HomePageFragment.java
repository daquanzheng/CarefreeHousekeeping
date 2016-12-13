package com.micro.android316.housekeeping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.micro.android316.housekeeping.R;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_listview,null);
        listView = (ListView) view.findViewById(R.id.home_listview);
        homePageAdapter = new HomePageAdapter(getActivity(),getlists());
        listView.setAdapter(homePageAdapter);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.homepage_header,null);
        listView.addHeaderView(view1);
        return view;
    }

    public List<HomePageModel> getlists() {
        for(int i=0;i<3;i++){
            HomePageModel hpm = new HomePageModel();
            lists.add(hpm);
        }
        return lists;
    }

}
