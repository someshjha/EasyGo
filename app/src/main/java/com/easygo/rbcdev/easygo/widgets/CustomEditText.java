package com.easygo.rbcdev.easygo.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.R;

/**
 * Created by rbcdev on 15-10-03.
 */
public class CustomEditText extends LinearLayout{

    private String mLabel;
    private TextView mTxtLabel;
    private TextView mTxtValue;

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_custom_edittext, this, true);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomEditText, 0, 0);
        mLabel = a.getString(R.styleable.CustomEditText_txtLabel);
        a.recycle();

        mTxtLabel = (TextView) findViewById(R.id.itemTxtLabel);
        mTxtLabel.setText(mLabel);

        mTxtValue = (EditText) findViewById(R.id.itemTxtValue);

    }

    public void setmTxtValue(String value) {
        mTxtValue.setText(value);
    }

    public String getmTxtValue() {
        return mTxtValue.getText().toString();
    }
}
