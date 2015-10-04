package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
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
