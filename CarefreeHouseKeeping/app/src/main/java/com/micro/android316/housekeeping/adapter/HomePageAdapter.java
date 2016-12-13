package com.micro.android316.housekeeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;
import com.micro.android316.housekeeping.model.HomePageModel;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/12/12.
 */
public class HomePageAdapter extends BaseAdapter{
    private List<HomePageModel> modelList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    public HomePageAdapter(Context context,List<HomePageModel> modelList){
        this.modelList = modelList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.home_layout_item,null);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.home_recommend_pc);
            viewHolder.name = (TextView) convertView.findViewById(R.id.worker_name);
            viewHolder.workRanges = (TextView) convertView.findViewById(R.id.working_range);
            viewHolder.briefInduction = (TextView) convertView.findViewById(R.id.brief_introduction);
            viewHolder.age = (TextView) convertView.findViewById(R.id.worker_age);
            viewHolder.workYears = (TextView) convertView.findViewById(R.id.working_years);
            viewHolder.browseTimes = (TextView) convertView.findViewById(R.id.browse_times);
            viewHolder.commentTimes = (TextView) convertView.findViewById(R.id.comment_times);
            convertView.setTag(viewHolder);
        }
        return convertView;
    }
    private class ViewHolder{
        ImageView picture;
        TextView name,workRanges,briefInduction,age,workYears,browseTimes,commentTimes;
    }
}
