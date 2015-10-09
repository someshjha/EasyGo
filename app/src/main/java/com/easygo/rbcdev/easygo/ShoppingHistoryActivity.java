package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.adapters.HistoryListAdapter;
import com.easygo.rbcdev.easygo.adapters.ItemsListAdapter;
import com.easygo.rbcdev.easygo.models.Cart;
import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Item;
import com.easygo.rbcdev.easygo.models.ShoppingHistory;
import com.easygo.rbcdev.easygo.widgets.Header;

import java.util.ArrayList;


public class ShoppingHistoryActivity extends Activity {

    private Header mHeader;
    private ArrayList<ShoppingHistory> retrievedItems;
    private Intent i;
    private String loginType;
    private String loggedInUser;
    private ListView mHistoryItems;
    private TextView mEmpty;

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
        i = getIntent();
        loginType = i.getStringExtra(Constants.LOGIN_TYPE);
        loggedInUser = i.getStringExtra(Constants.CUSTOMER_EMAIL);
        initializeUI();
        retrieveItems();
    }

    private void retrieveItems() {
        retrievedItems = new ArrayList<ShoppingHistory>();
        retrievedItems = ShoppingHistory.getHistory(loggedInUser);

        if(!retrievedItems.isEmpty() && retrievedItems != null) {
            mHistoryItems.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
            displayItems(retrievedItems);
        } else {
            mHistoryItems.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void displayItems(ArrayList<ShoppingHistory> historyList) {
        HistoryListAdapter listOfItems = new HistoryListAdapter(this,historyList);
        mHistoryItems.setAdapter(listOfItems);
    }

    private void initializeUI() {
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnLeftListener(backListener);
        mHistoryItems = (ListView) findViewById(R.id.lstItems);
        mEmpty = (TextView) findViewById(R.id.emptyList);
    }

}
