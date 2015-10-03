package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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
//        mLstStoreList.setAdapter(itemAdapter);
        mLstCategoryList.setAdapter(itemAdapter);
    }

    private void initializeUI() {
//        mLstStoreList = (Spinner) findViewById(R.id.StoreList);
        mLstCategoryList = (Spinner) findViewById(R.id.CategoryList);
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnRightListener(settingsListener);
        mBtnViewCart = (Button) findViewById(R.id.btnViewCartItems);
        mBtnViewCart.setOnClickListener(productDetailsListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
