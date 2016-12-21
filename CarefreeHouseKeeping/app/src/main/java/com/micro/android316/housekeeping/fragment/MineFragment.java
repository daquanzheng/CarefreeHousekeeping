package com.micro.android316.housekeeping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.activity.About;
import com.micro.android316.housekeeping.activity.CommonAddressActivity;
import com.micro.android316.housekeeping.activity.FeedBack;
import com.micro.android316.housekeeping.activity.MessageActivity;
import com.micro.android316.housekeeping.activity.OnlineOnsultant;
import com.micro.android316.housekeeping.activity.PersonalInformationActivity;
import com.micro.android316.housekeeping.activity.SetActivity;

/**
 * Created by Administrator on 2016/12/12.
 */
public class MineFragment extends Fragment{
    ImageView personalInformation;
    ImageView message;
    ImageView address;
    ImageView set;
    ImageView opinon;
    ImageView consult;
    ImageView about;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mine_fragment,null);
        personalInformation= (ImageView) view.findViewById(R.id.mine_personal_information_img);
        message= (ImageView) view.findViewById(R.id.mine_message_img);
        address=(ImageView)view.findViewById(R.id.mine_address_img);
        set=(ImageView)view.findViewById(R.id.mine_set_img);
        opinon=(ImageView)view.findViewById(R.id.mine_opinion_img);
        consult=(ImageView)view.findViewById(R.id.mine_consult_img);
        about=(ImageView)view.findViewById(R.id.mine_about_img);

        personalInformation.setOnClickListener(onClickListener);
        message.setOnClickListener(onClickListener);
        address.setOnClickListener(onClickListener);
        set.setOnClickListener(onClickListener);
        opinon.setOnClickListener(onClickListener);
        consult.setOnClickListener(onClickListener);
        about.setOnClickListener(onClickListener);
        return view;
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mine_personal_information_img:
                    Intent intent=new Intent(getActivity(), PersonalInformationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mine_message_img:
                    Intent intent1=new Intent(getActivity(), MessageActivity.class);
                    startActivity(intent1);

                    break;
                case R.id.mine_address_img:
                    Intent intent2=new Intent(getActivity(), CommonAddressActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mine_set_img:
                    Intent intent3=new Intent(getActivity(), SetActivity.class);
                    startActivity(intent3);
                    break;

                case R.id.mine_opinion_img:
                    Intent intent4=new Intent(getActivity(), FeedBack.class);
                    startActivity(intent4);
                    break;
                case R.id.mine_consult_img:
                    Intent intent5=new Intent(getActivity(), OnlineOnsultant.class);
                    startActivity(intent5);
                    break;
                case R.id.mine_about_img:
                    Intent intent6=new Intent(getActivity(), About.class);
                    startActivity(intent6);
                    break;
            }
        }
    };

}
