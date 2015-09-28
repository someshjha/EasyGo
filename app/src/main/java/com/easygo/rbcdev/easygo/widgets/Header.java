package com.easygo.rbcdev.easygo.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.R;

/**
 * Created by rbcdev on 15-09-22.
 */
public class Header extends RelativeLayout {

    private String mLeftLabel;
    private String mRightLabel;
    private String mTitle;
    private Drawable mLeftIcon;
    private Drawable mRightIcon;
    private SquareImageButton mBtnLeftIcon;
    private SquareImageButton mBtnRightIcon;
    private Button mBtnLeft;
    private Button mBtnRight;
    private TextView mTxtTitle;

    public Header(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_header, this, true);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Header, 0, 0);
        mLeftLabel = a.getString(R.styleable.Header_btnLeft);
        mRightLabel = a.getString(R.styleable.Header_btnRight);
        mTitle = a.getString(R.styleable.Header_headerTitle);
        mLeftIcon = a.getDrawable(R.styleable.Header_leftIcon);
        mRightIcon = a.getDrawable(R.styleable.Header_rightIcon);
        a.recycle();

        mBtnLeft = (Button)findViewById(R.id.btnLeftLink);
        mBtnRight = (Button)findViewById(R.id.btnRightLink);
        mTxtTitle = (TextView) findViewById(R.id.lblTitle);
        mBtnLeftIcon = (SquareImageButton) findViewById(R.id.btnLeftIcon);
        mBtnRightIcon = (SquareImageButton) findViewById(R.id.btnRightIcon);

        setLeftLabel(mLeftLabel);
        setRightLabel(mRightLabel);
        setTitle(mTitle);
        setLeftIcon(mLeftIcon);
        setRightIcon(mRightIcon);

    }

    public void setLeftLabel(String label){
       mLeftLabel = label;
        setField(mLeftLabel, "", mBtnLeft);

        if (mLeftLabel != null) {
            mBtnLeft.setVisibility(View.VISIBLE);
            mBtnLeftIcon.setVisibility(View.GONE);
        }
    }

    public void setRightLabel(String label){
        mRightLabel = label;
        setField(mRightLabel, "", mBtnRight);

        if (mRightLabel != null) {
            mBtnLeft.setVisibility(View.VISIBLE);
            mBtnLeftIcon.setVisibility(View.GONE);
        }
    }

    public void setLeftIcon(Drawable icon) {
        mBtnLeftIcon.setIcon(icon);

        if(mLeftIcon != null) {
            mBtnLeftIcon.setVisibility(View.VISIBLE);
            mBtnLeft.setVisibility(View.GONE);
        }
    }

    public void setRightIcon(Drawable icon) {
        mBtnRightIcon.setIcon(icon);

        if(mRightIcon != null) {
            mBtnRightIcon.setVisibility(View.VISIBLE);
            mBtnRight.setVisibility(View.GONE);
        }
    }

    public void setTitle(String title) {
        mTitle = title;
        setField(mTitle, "", mTxtTitle);
    }

    public void setBtnLeftListener(OnClickListener listener) {
        mBtnLeft.setOnClickListener(listener);
        mBtnLeftIcon.setOnClickListener(listener);
    }

    public void setBtnRightListener(OnClickListener listener) {
        mBtnRight.setOnClickListener(listener);
        mBtnRightIcon.setOnClickListener(listener);
    }

    private void setField(String field, String def, TextView txtView) {
        if (field == null) {
            txtView.setText(def);
        } else {
            txtView.setText(field);
        }
    }

    public void setRightEnabled(boolean isEnabled) {
        mBtnRight.setEnabled(isEnabled);
        mBtnRightIcon.setEnabled(isEnabled);
    }
}
