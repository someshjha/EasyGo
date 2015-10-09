package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.easygo.rbcdev.easygo.Utility.AlertUtility;
import com.easygo.rbcdev.easygo.adapters.ItemsListAdapter;
import com.easygo.rbcdev.easygo.models.Cart;
import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Item;
import com.easygo.rbcdev.easygo.models.Items;
import com.easygo.rbcdev.easygo.models.ShoppingHistory;
import com.easygo.rbcdev.easygo.widgets.CustomField;
import com.easygo.rbcdev.easygo.widgets.Header;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;


public class CartActivity extends Activity {

    private Spinner mLstStoreList;
    private Header mHeader;
    private ArrayAdapter<CharSequence> storeAdapter;
    private String loginType;
    private TextView mEmptyList;
    private String loggedInUser;
    private ArrayAdapter<CharSequence> itemAdapter;
    private ArrayList<Cart> retrievedItems;
    private ListView mSelectedItems;
    private CustomField mSubTotal;
    private CustomField mTax;
    private CustomField mTotal;
    private Button mSubmit;
    private String strSubTotal = "";
    private String strTax = "";
    private String strTotal = "";
    private Intent i;

    private View.OnClickListener backPressListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(retrievedItems.isEmpty()){
                displayError("Cart is empty!");
            }
            else {
                String date = new Date().toString();
                String store = mLstStoreList.getSelectedItem().toString();
                String email = loggedInUser;
                String amount = strTotal;

                ShoppingHistory history = new ShoppingHistory();
                history.setDate(date);
                history.setStore(store);
                history.setCustomerEmail(loggedInUser);
                history.setTotal(amount);
                history.save();

                showOrderSubmitted();
                goToShoppingHome();
            }
        }
    };

    private void goToShoppingHome() {
        Intent intent = new Intent(this,ShoppingHome.class);
        intent.putExtra(Constants.CUSTOMER_EMAIL,loggedInUser);
        intent.putExtra(Constants.LOGIN_TYPE, Constants.SOBEYS_CUSTOMER);
        startActivity(intent);
    }

    private void showOrderSubmitted() {
        Toast.makeText(this,"Order Submitted",Toast.LENGTH_LONG).show();
    }

    private void displayError(String message) {
        AlertUtility.displayAlert(message,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        i = getIntent();
        loginType = i.getStringExtra(Constants.LOGIN_TYPE);
        loggedInUser = i.getStringExtra(Constants.CUSTOMER_EMAIL);
        initializeUI();
        populateLists();
        retrieveItems();
    }

    private void retrieveItems() {
        retrievedItems = new ArrayList<Cart>();
        retrievedItems = Cart.getCart(loggedInUser);
        ArrayList<Item> cartItems = new ArrayList<Item>();

        if(!retrievedItems.isEmpty() || retrievedItems != null) {
            mSelectedItems.setVisibility(View.VISIBLE);
            mEmptyList.setVisibility(View.GONE);
            for(Cart cartItem:retrievedItems) {
                Item singleItem = new Item();
                singleItem.setItemName(cartItem.getItemName());
                singleItem.setItemPrice(cartItem.getItemPrice());
                cartItems.add(singleItem);
            }

            displayItems(cartItems);
            calculateTotals(cartItems);
        } else {
            mSelectedItems.setVisibility(View.GONE);
            mEmptyList.setVisibility(View.VISIBLE);
        }
    }

    private void calculateTotals(ArrayList<Item> itemList) {
        double subtotal = 0.0;
        double tax = 0.0;
        double total = 0.0;

        for(Item i:itemList) {
            subtotal += Double.parseDouble(i.getItemPrice());
        }

        tax = round(0.15 * subtotal,2);
        total = round(subtotal + tax, 2);

        strSubTotal = String.valueOf(subtotal);
        strTax = String.valueOf(tax);
        strTotal = String.valueOf(total);

        mSubTotal.setValue("$" + strSubTotal);
        mTax.setValue(String.valueOf("$" + strTax));
        mTotal.setValue(String.valueOf("$" + strTotal));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    private void displayItems(ArrayList<Item> itemList) {
        ItemsListAdapter listOfItems = new ItemsListAdapter(this,itemList);
        mSelectedItems.setAdapter(listOfItems);
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
        mSubTotal = (CustomField) findViewById(R.id.cartSubTotalField);
        mTax = (CustomField) findViewById(R.id.cartTaxField);
        mTotal = (CustomField) findViewById(R.id.itemTotalField);
        mSelectedItems = (ListView) findViewById(R.id.lstCartItems);
        mEmptyList = (TextView) findViewById(R.id.emptyList);
        mSubmit = (Button) findViewById(R.id.btnSubmit);
        mSubmit.setOnClickListener(submitListener);
    }

}
