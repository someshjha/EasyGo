package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.widgets.Header;


public class ShoppingHome extends Activity {

    private Header mHeader;
    private Button mBtnStartShopping;
    private String loggedInUser;
    private Intent i;

    private View.OnClickListener settingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           goToSettings();
        }
    };

    private View.OnClickListener startShoppingListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToShopping();
        }
    };

    private void goToShopping() {
        Intent intent = new Intent(this,ShoppingItemsActivity.class);
        startActivity(i);
    }

    private void goToSettings() {
        Intent intent = new Intent(this,Settings.class);
        intent.putExtra(Constants.LOGIN_TYPE,Constants.SOBEYS_CUSTOMER);
        intent.putExtra(Constants.CUSTOMER_EMAIL,loggedInUser);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_home);
        i = getIntent();
        loggedInUser = i.getStringExtra(Constants.CUSTOMER_EMAIL);
        initializeUI();
    }

    private void initializeUI() {
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnRightListener(settingsListener);
        mBtnStartShopping = (Button) findViewById(R.id.btnStartShopping);
        mBtnStartShopping.setOnClickListener(startShoppingListener);
    }



}
