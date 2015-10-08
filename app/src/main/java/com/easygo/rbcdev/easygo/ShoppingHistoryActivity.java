package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.easygo.rbcdev.easygo.widgets.Header;


public class ShoppingHistoryActivity extends Activity {

    private Header mHeader;

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_history);
        initializeUI();
    }

    private void initializeUI() {
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnLeftListener(backListener);
    }



}
