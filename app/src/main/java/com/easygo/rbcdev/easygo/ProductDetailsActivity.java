package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.easygo.rbcdev.easygo.widgets.SquareImageButton;


public class ProductDetailsActivity extends Activity {

    private SquareImageButton mBtnNutrition;
    private Button mBtnAddToCard;

    private View.OnClickListener addToCartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToCart();
        }
    };

    private void goToCart() {
        Intent i = new Intent(this,CartActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initializeUI();
    }

    private void initializeUI() {
        mBtnNutrition = (SquareImageButton) findViewById(R.id.btnNutritionIcon);
        mBtnNutrition.setIcon(getResources().getDrawable(R.drawable.ic_nutrition));
        mBtnAddToCard = (Button) findViewById(R.id.btnAddToCart);
        mBtnAddToCard.setOnClickListener(addToCartListener);
    }



}
