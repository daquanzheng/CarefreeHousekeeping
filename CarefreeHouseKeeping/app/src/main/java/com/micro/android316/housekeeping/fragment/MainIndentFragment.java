package com.micro.android316.housekeeping.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.adapter.WaitpayAdapter;
import com.micro.android316.housekeeping.adapter.WaitserviceAdapter;
import com.micro.android316.housekeeping.model.Waitpay;
import com.micro.android316.housekeeping.model.Waitservice;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.indent_main_layout,null);

        waitpayLiseView = (ListView) v.findViewById(R.id.waitpay_listview);
        waitserviceListView = (ListView) v.findViewById(R.id.waitservice_listview);
        waitappraiseLayout = (LinearLayout) v.findViewById(R.id.waitappraise_view);

        WaitpayAdapter waitpayAdapter = new WaitpayAdapter(getActivity(),getWaitpayList());
        waitpayLiseView.setAdapter(waitpayAdapter);

        WaitserviceAdapter waitserviceAdapter = new WaitserviceAdapter(getActivity(),getWaitserviceList());
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
                        waitpayLiseView.setVisibility(View.VISIBLE);
                        waitserviceListView.setVisibility(View.VISIBLE);
                        waitappraiseLayout.setVisibility(View.GONE);
                        radioButtons[0].setTextColor(getResources().getColor(R.color.txt_1));
                        radioButtons[1].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[2].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[3].setTextColor(getResources().getColor(R.color.dark_black));
                        break;
                    case R.id.waitpay:
                        waitpayLiseView.setVisibility(View.VISIBLE);
                        waitserviceListView.setVisibility(View.GONE);
                        waitappraiseLayout.setVisibility(View.GONE);
                        radioButtons[0].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[1].setTextColor(getResources().getColor(R.color.txt_1));
                        radioButtons[2].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[3].setTextColor(getResources().getColor(R.color.dark_black));
                        break;
                    case R.id.waitservice:
                        waitpayLiseView.setVisibility(View.GONE);
                        waitserviceListView.setVisibility(View.VISIBLE);
                        waitappraiseLayout.setVisibility(View.GONE);
                        radioButtons[0].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[1].setTextColor(getResources().getColor(R.color.dark_black));
                        radioButtons[2].setTextColor(getResources().getColor(R.color.txt_1));
                        radioButtons[3].setTextColor(getResources().getColor(R.color.dark_black));
                        break;
                    case R.id.waitappraise:
                        waitpayLiseView.setVisibility(View.GONE);
                        waitserviceListView.setVisibility(View.GONE);
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

    private List<Waitpay> getWaitpayList() {
        for(int i=0;i<3;i++){
            Waitpay waitpay = new Waitpay();
            waitpayList.add(waitpay);
        }
        return waitpayList;
    }
    private List<Waitservice> getWaitserviceList() {
        for(int i=0;i<3;i++){
            Waitservice waitservice = new Waitservice();
            waitserviceList.add(waitservice);
        }
        return waitserviceList;
    }
}
