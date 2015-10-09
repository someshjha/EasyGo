package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.easygo.rbcdev.easygo.Utility.AlertUtility;
import com.easygo.rbcdev.easygo.models.Cart;
import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Item;
import com.easygo.rbcdev.easygo.models.Items;
import com.easygo.rbcdev.easygo.widgets.CustomEditText;
import com.easygo.rbcdev.easygo.widgets.CustomField;
import com.easygo.rbcdev.easygo.widgets.Header;
import com.easygo.rbcdev.easygo.widgets.SquareImageButton;


public class ProductDetailsActivity extends Activity {

    private SquareImageButton mBtnNutrition;
    private Button mBtnAddToCard;
    private Intent i;
    private String loggedInUser;
    private Item currentItem;
    private TextView mTitle;
    private CustomField mPrice;
    private CustomField mCategory;
    private CustomField mUnits;
    private CustomField mServingSize;
    private CustomField mCalories;
    private CustomEditText mQuantity;
    private CustomEditText mWeight;
    private Header mHeader;

    private View.OnClickListener addToCartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(currentItem.getItemUnit().equals("KG")) {
                if(mWeight.getmTxtValue().isEmpty() && mWeight.getVisibility() == View.VISIBLE) {
                    displayError("Please fill out weight");
                } else {
                    addToCart();
                }

            }
            else{
                if(mQuantity.getmTxtValue().isEmpty() && mQuantity.getVisibility() == View.VISIBLE ) {
                    displayError("Please fill out value");
                }
                else {
                    addToCart();
                }
            }
        }
    };

    private void displayError(String message) {
        AlertUtility.displayAlert(message, this);
    }

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private void addToCart() {
        String itemPrice;
        Double price = 0.0;
        Cart itemToAdd = new Cart();

        String weight = mWeight.getmTxtValue();
        String quantity = mQuantity.getmTxtValue();

        if(currentItem.getItemUnit().equals("KG")  && mWeight.getVisibility() == View.VISIBLE) {
            price = Double.parseDouble(currentItem.getItemPrice())
                    * (double) Integer.parseInt(mWeight.getmTxtValue().trim());
        } else {
            if(mQuantity.getVisibility() == View.VISIBLE) {
            price = Double.parseDouble(currentItem.getItemPrice())
                    * (double) Integer.parseInt(mQuantity.getmTxtValue().trim());
            }
        }

        itemToAdd.setItemPrice(String.valueOf(price));
        itemToAdd.setCustomerEmail(loggedInUser);
        itemToAdd.setItemName(currentItem.getItemName());

        Toast.makeText(this, currentItem.getItemName() + " has been added to cart", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        i = getIntent();
        currentItem = (Item) i.getSerializableExtra(Constants.ITEM);
        loggedInUser = i.getStringExtra(Constants.CUSTOMER_EMAIL);

        initializeUI();
    }

    private void initializeUI() {
        mBtnNutrition = (SquareImageButton) findViewById(R.id.btnNutritionIcon);
        mBtnNutrition.setIcon(getResources().getDrawable(R.drawable.ic_nutrition));
        mBtnAddToCard = (Button) findViewById(R.id.btnAddToCart);
        mBtnAddToCard.setOnClickListener(addToCartListener);
        mTitle = (TextView) findViewById(R.id.productDetailsTitle);
        mTitle.setText(currentItem.getItemName());
        mPrice = (CustomField) findViewById(R.id.itemPriceField);
        mPrice.setValue("$" + currentItem.getItemPrice());
        mCategory = (CustomField) findViewById(R.id.itemCategoryField);
        mCategory.setValue(currentItem.getItemCategory());
        mUnits = (CustomField) findViewById(R.id.itemUnitField);
        mUnits.setValue(currentItem.getItemUnit());
        mServingSize = (CustomField) findViewById(R.id.itemServingSizeField);
        mServingSize.setValue(currentItem.getItemServingSize());
        mCalories = (CustomField) findViewById(R.id.itemCalories);
        mCalories.setValue(currentItem.getItemCalories());
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnLeftListener(backListener);
        mQuantity = (CustomEditText) findViewById(R.id.productFieldQuantity);
        mWeight = (CustomEditText) findViewById(R.id.productFieldWeight);
        showHideCustomEditText();
    }

    private void showHideCustomEditText() {
        if(currentItem.getItemUnit().equals("KG")) {
            mQuantity.setVisibility(View.GONE);
            mWeight.setVisibility(View.VISIBLE);
        }
        else {
            mWeight.setVisibility(View.GONE);
            mQuantity.setVisibility(View.VISIBLE);
        }
    }

}
