package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Item;
import com.easygo.rbcdev.easygo.models.Items;
import com.easygo.rbcdev.easygo.widgets.Header;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class ShoppingHome extends Activity {

    private Header mHeader;
    private Button mBtnStartShopping;
    private String loggedInUser;
    private Items storeItems;
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
        intent.putExtra(Constants.ITEMS,storeItems);
        intent.putExtra(Constants.CUSTOMER_EMAIL,loggedInUser);
        intent.putExtra(Constants.LOGIN_TYPE, Constants.SOBEYS_CUSTOMER);
        startActivity(intent);
    }

    private void goToSettings() {
        Intent intent = new Intent(this,Settings.class);
        intent.putExtra(Constants.LOGIN_TYPE, Constants.SOBEYS_CUSTOMER);
        intent.putExtra(Constants.CUSTOMER_EMAIL, loggedInUser);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_home);
        i = getIntent();
        loggedInUser = i.getStringExtra(Constants.CUSTOMER_EMAIL);
        initializeUI();
        populateItems();
    }

    private void populateItems() {
        String itemString = loadFromJson();
        JSONArray itemArray;

        try {
            itemArray = new JSONArray(itemString);
            storeItems = new Items();
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            ArrayList<Item> loadItems = new Gson().fromJson(itemArray.toString(), listType);
            storeItems.setCurrentItems(loadItems);
        } catch (JSONException e){
            e.getStackTrace();
        }
    }

    private String loadFromJson() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("Json-Response-Items");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void initializeUI() {
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnRightListener(settingsListener);
        mBtnStartShopping = (Button) findViewById(R.id.btnStartShopping);
        mBtnStartShopping.setOnClickListener(startShoppingListener);
    }



}
