package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.easygo.rbcdev.easygo.widgets.Header;


public class CartActivity extends Activity {

    private Spinner mLstStoreList;
    private Header mHeader;
    private ArrayAdapter<CharSequence> storeAdapter;

    private View.OnClickListener backPressListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initializeUI();
        populateLists();
    }

    private void populateLists() {
        storeAdapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_list_item_1);
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLstStoreList.setAdapter(storeAdapter);
    }

    private void initializeUI() {
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnLeftListener(backPressListener);
        mLstStoreList = (Spinner) findViewById(R.id.StoreList);
    }


}
