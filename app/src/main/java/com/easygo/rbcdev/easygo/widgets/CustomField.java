package com.easygo.rbcdev.easygo.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.R;

/**
 * Created by rbcdev on 15-10-02.
 */
public class CustomField extends LinearLayout {

    private String mLabel;
    private TextView mTxtLabel;
    private TextView mTxtValue;

    public CustomField(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_custom_field, this, true);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomField, 0, 0);
        mLabel = a.getString(R.styleable.CustomField_label);
        a.recycle();

        mTxtLabel = (TextView) findViewById(R.id.itemLabel);
        mTxtLabel.setText(mLabel);
        mTxtValue = (TextView) findViewById(R.id.itemValue);


    }

    public void setValue(String value) {
        mTxtValue.setText(value);
    }
}
