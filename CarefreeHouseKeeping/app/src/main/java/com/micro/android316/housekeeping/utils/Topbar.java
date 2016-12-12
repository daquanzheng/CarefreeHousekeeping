package com.micro.android316.housekeeping.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.android316.housekeeping.R;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Topbar extends RelativeLayout{
    private Button leftButton,rightButton;
    private TextView tvTitle;

    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private LayoutParams leftParams,rightParams,titleParams;

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        leftTextColor=ta.getColor(R.styleable.Topbar_leftTextColor,0);
        leftBackground=ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText=ta.getString(R.styleable.Topbar_leftText);

        rightTextColor=ta.getColor(R.styleable.Topbar_rightTextColor,0);
        rightBackground=ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText=ta.getString(R.styleable.Topbar_rightText);

        leftTextColor=ta.getColor(R.styleable.Topbar_leftTextColor,0);
        leftBackground=ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText=ta.getString(R.styleable.Topbar_leftText);

        titleTextSize=ta.getDimension(R.styleable.Topbar_titleTextSize,0);
        titleTextColor=ta.getColor(R.styleable.Topbar_titleTextColor,0);
        title=ta.getString(R.styleable.Topbar_title);

        ta.recycle();

        leftButton=new Button(context);
        rightButton=new Button(context);
        tvTitle=new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0xFFF59563);


        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);

        addView(leftButton,leftParams);

        rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);

        addView(rightButton,rightParams);

        titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);

        addView(tvTitle,titleParams);
    }
}
