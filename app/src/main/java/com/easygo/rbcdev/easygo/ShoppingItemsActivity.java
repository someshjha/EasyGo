package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.easygo.rbcdev.easygo.widgets.Header;


public class ShoppingItemsActivity extends Activity {

    private Spinner mLstCategoryList;
    private ArrayAdapter<CharSequence> itemAdapter;
    private Button mBtnViewCart;
    private Header mHeader;

    private View.OnClickListener settingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToSettings();
        }
    };

    private View.OnClickListener productDetailsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToProductDetails();
        }
    };

    private void goToProductDetails() {
        Intent i = new Intent(this,ProductDetailsActivity.class);
        startActivity(i);
    }

    private void goToSettings() {
        Intent i = new Intent(this,Settings.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_items);
        initializeUI();
        populateLists();
    }

    private void populateLists() {
        itemAdapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_list_item_1);
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLstCategoryList.setAdapter(itemAdapter);
    }

    private void initializeUI() {
        mLstCategoryList = (Spinner) findViewById(R.id.CategoryList);
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnRightListener(settingsListener);
        mBtnViewCart = (Button) findViewById(R.id.btnViewCartItems);
        mBtnViewCart.setOnClickListener(productDetailsListener);
    }


}
