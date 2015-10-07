package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_home, menu);
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
