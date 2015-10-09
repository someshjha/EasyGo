package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Item;
import com.easygo.rbcdev.easygo.widgets.CustomField;
import com.easygo.rbcdev.easygo.widgets.Header;
import com.easygo.rbcdev.easygo.widgets.SquareImageButton;
import com.github.mikephil.charting.charts.PieChart;


public class ProductDetailsActivity extends Activity {

    private Activity mActivity = this;
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
    private Header mHeader;
    private SquareImageButton mGetNutrition;



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
        mGetNutrition = (SquareImageButton)findViewById(R.id.btnNutritionIcon);
        mBtnNutrition.setVisibility(View.VISIBLE);
        mBtnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, GraphItemNutrients.class);
                i.putExtra("item", currentItem);
                startActivity(i);
            }
        });

    }

    private void createPieChart(Item item){
        PieChart pc = new PieChart(mActivity);

        float calcium  = Float.valueOf(item.getItemCalcium());
        float carbohydrate  = Float.valueOf(item.getItemCarbohydrate());
        float cholestrol  = Float.valueOf(item.getItemCholestrol());
        float fat  = Float.valueOf(item.getItemFat());
        float fibre  = Float.valueOf(item.getItemFibre());
        float iron  = Float.valueOf(item.getItemIron());
        float protein  = Float.valueOf(item.getItemProtein());
        float saturatedTransFat  = Float.valueOf(item.getItemSaturatedTransFat());
        float sodium  = Float.valueOf(item.getItemSodium());
        float sugar  = Float.valueOf(item.getItemSugars());
        float vitaminA  = Float.valueOf(item.getItemVitaminA());
        float vitaminC  = Float.valueOf(item.getItemVitaminC());



    }

}
