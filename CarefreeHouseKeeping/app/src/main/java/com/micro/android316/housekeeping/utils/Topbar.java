package com.micro.android316.housekeeping.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Topbar extends RelativeLayout{
    private TextView leftTv,rightTv;
    private ImageView leftImgView;
    private TextView tvTitle;

    private int leftTextColor;
    private float leftTextSize;
    private String leftText;

    private int rightTextColor;
    private float rightTextSize;
    private String rightText;

    private Drawable leftImg;

    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private topBarClickListener listener;

    public interface topBarClickListener{
        public void leftClick();
        public void rightClick();
    }

    public void setOnTopBarClickListener(topBarClickListener listener){
        this.listener=listener;
    }

    private LayoutParams leftParams,rightParams,titleParams,leftImgParams;

    public Topbar(Context context) {
        super(context);
    }

    public Topbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        leftTextColor=ta.getColor(R.styleable.Topbar_leftTextColor,0);
        leftTextSize=ta.getDimension(R.styleable.Topbar_leftTextSize,0);
        leftText=ta.getString(R.styleable.Topbar_leftText);

        rightTextColor=ta.getColor(R.styleable.Topbar_rightTextColor,0);
        rightTextSize=ta.getDimension(R.styleable.Topbar_rightTextSize,0);
        rightText=ta.getString(R.styleable.Topbar_rightText);


        leftImg=ta.getDrawable(R.styleable.Topbar_leftImg);

        titleTextSize=ta.getDimension(R.styleable.Topbar_titleTextSize,0);
        Log.i("titleSize", "Topbar: "+titleTextSize);
        titleTextColor=ta.getColor(R.styleable.Topbar_titleTextColor,0);
        title=ta.getString(R.styleable.Topbar_title);

        ta.recycle();

        leftTv=new TextView(context);
        rightTv=new TextView(context);
        tvTitle=new TextView(context);
        leftImgView=new ImageView(context);

        leftTv.setTextColor(leftTextColor);
        leftTv.setTextSize(leftTextSize);
        leftTv.setText(leftText);


        rightTv.setTextColor(rightTextColor);
        rightTv.setTextSize(rightTextSize);
        rightTv.setText(rightText);
        rightTv.setMaxWidth(50);
        if (leftImg!= null) {
            leftImgView.setImageDrawable(leftImg);
            leftImgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }



        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);






        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);

        addView(leftTv,leftParams);



        leftImgParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL,TRUE);
        addView(leftImgView,leftImgParams);


        rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL,TRUE);


        addView(rightTv,rightParams);

        titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);


        addView(tvTitle,titleParams);

        leftImgView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        leftTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        rightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    listener.rightClick();
            }
        });
    }
    public void setLeftTvIsVisible(boolean flag){
        if(flag){
            leftTv.setVisibility(VISIBLE);
        }else {
            leftTv.setVisibility(GONE);
        }
    }

    public void setRightTvIsVisible(boolean flag){
        if(flag){
            rightTv.setVisibility(VISIBLE);
        }else {
            rightTv.setVisibility(GONE);
        }
    }

    public void setLeftImgViewIsVisible(boolean flag){
        if(flag){
            leftImgView.setVisibility(VISIBLE);
        }else {
            leftImgView.setVisibility(GONE);
        }
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        int actualHeight=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
