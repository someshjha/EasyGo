package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.widgets.Header;


public class Settings extends Activity {

    private Header mHeader;
    private Button mBtnProfile;
    private Button mBtnHistory;
    private String mCustomerEmail;

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener profileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToProfile();
        }
    };

    private View.OnClickListener historyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToHistory();
        }
    };

    private void goToHistory() {
        Intent i = new Intent(this,ShoppingHistoryActivity.class);
        startActivity(i);
    }

    private void goToProfile() {
        Intent i = new Intent(this,ProfileActivity.class);
        i.putExtra(Constants.LOGIN_TYPE, Constants.SOBEYS_CUSTOMER);
        i.putExtra(Constants.CUSTOMER_EMAIL,mCustomerEmail);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent i = getIntent();
        mCustomerEmail = i.getStringExtra(Constants.CUSTOMER_EMAIL);
        initializeUI();
    }

    private void initializeUI() {
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnLeftListener(backListener);
        mBtnProfile = (Button) findViewById(R.id.btnProfile);
        mBtnProfile.setOnClickListener(profileListener);
        mBtnHistory = (Button) findViewById(R.id.btnShoppingHistory);
        mBtnHistory.setOnClickListener(historyListener);
    }



}
