package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.easygo.rbcdev.easygo.adapters.ItemsListAdapter;
import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Item;
import com.easygo.rbcdev.easygo.models.Items;
import com.easygo.rbcdev.easygo.widgets.Header;

import java.util.ArrayList;


public class ShoppingItemsActivity extends Activity {

    private Spinner mLstCategoryList;
    private ArrayAdapter<CharSequence> itemAdapter;
    private ListView mAvailableItems;
    private Button mBtnViewCart;
    private Header mHeader;
    private Items items;
    private String loggedInUser;
    private Intent i;

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

    private AdapterView.OnItemSelectedListener filterItems = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(i == 0) {
                displayItems(items.getCurrentItems());
            }
            else {
                String filter = mLstCategoryList.getItemAtPosition(i).toString();
                ArrayList<Item> filteredList = items.filterItems(filter);
                displayItems(filteredList);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private AdapterView.OnItemClickListener itemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(getBaseContext(),ProductDetailsActivity.class);
            Item selected = (Item) mAvailableItems.getItemAtPosition(i);
            intent.putExtra(Constants.ITEM,selected);
            intent.putExtra(Constants.CUSTOMER_EMAIL,loggedInUser);
            intent.putExtra(Constants.LOGIN_TYPE, Constants.SOBEYS_CUSTOMER);
            startActivity(intent);
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
        i = getIntent();
        items = (Items) i.getSerializableExtra(Constants.ITEMS);
        loggedInUser = i.getStringExtra(Constants.CUSTOMER_EMAIL);

        initializeUI();
        populateLists();
    }

    private void populateLists() {
        itemAdapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_list_item_1);
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLstCategoryList.setAdapter(itemAdapter);
        mLstCategoryList.setOnItemSelectedListener(filterItems);
        mAvailableItems = (ListView) findViewById(R.id.lstItems);
        mAvailableItems.setOnItemClickListener(itemSelectedListener);
        displayItems(items.getCurrentItems());
    }

    private void displayItems(ArrayList<Item> itemList) {
        ItemsListAdapter listOfItems = new ItemsListAdapter(this,itemList);
        mAvailableItems.setAdapter(listOfItems);
    }

    private void initializeUI() {
        mLstCategoryList = (Spinner) findViewById(R.id.CategoryList);
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnRightListener(settingsListener);
        mBtnViewCart = (Button) findViewById(R.id.btnViewCartItems);
        mBtnViewCart.setOnClickListener(productDetailsListener);
        mAvailableItems = (ListView) findViewById(R.id.lstItems);
    }


}
