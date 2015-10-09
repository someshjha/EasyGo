package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Item;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphItemNutrients extends Activity {

    private FrameLayout mainLayout;
    private PieChart mChart;
    private Activity mThisActivity = this;
    private float[] yData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_item_nutrients);

        mainLayout = (FrameLayout)findViewById(R.id.graphLayout);
        mChart = new PieChart(this);
        Intent i = getIntent();
        Item item = (Item) i.getSerializableExtra(Constants.ITEM);

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
        float other = 100 - (calcium + carbohydrate + cholestrol + fat + fibre + iron + protein + saturatedTransFat + sodium + sugar + vitaminA + vitaminC);
        float[] yData = {calcium, carbohydrate, cholestrol, fat, fibre, iron, protein, saturatedTransFat, sodium, sugar, vitaminA, vitaminC, other};
        final String[] xData = {"calcium", "carbohydrate", "cholestrol", "fat", "fibre", "iron", "protein", "saturatedTransFat", "sodium", "sugar", "vitaminA", "vitaminC", "other"};


        mainLayout.addView(mChart);
        //configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Nutrients");

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(9);
        mChart.setTransparentCircleRadius(12);

        //enable rotation
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);


        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (e == null) {
                    return;
                }
                Toast.makeText(mThisActivity, xData[(e.getXIndex())] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        ArrayList<Entry> yVals = new ArrayList<>();
        for (int x = 0; x< yData.length; x++ ){
            yVals.add(new Entry(yData[x], x));
        }

        ArrayList<String> xVals = new ArrayList<>();
        for(int x =0; x<xData.length; x++)
            xVals.add(xData[x]);


        PieDataSet pieDataSet = new PieDataSet(yVals, "Nutrients Value");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);


        ArrayList<Integer> colors = new ArrayList<>();

        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.LIBERTY_COLORS)
            colors.add(c);


        for(int c: ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colors);


        //Instantiate Pie Data

        PieData pd = new PieData(xVals, pieDataSet);
        pd.setValueFormatter(new PercentFormatter());
        pd.setValueTextSize(11f);
        pd.setValueTextColor(Color.BLACK);

        mChart.setData(pd);

        mChart.highlightValues(null);

        mChart.invalidate();

        //add Legends

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);


    }





    private void addData(float[] yData, String[] xData){

        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i = 0; i< yData.length; i++ ){
            yVals.add(new Entry(yData[i], i));
        }

        ArrayList<String> xVals = new ArrayList<>();
        for(int i =0; i<xData.length; i++)
            xVals.add(xData[i]);


        PieDataSet pieDataSet = new PieDataSet(yVals, "Nutrient Value");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);


        ArrayList<Integer> colors = new ArrayList<>();

        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.LIBERTY_COLORS)
            colors.add(c);


        for(int c: ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colors);


        //Instantiate Pie Data

        PieData pd = new PieData(xVals, pieDataSet);
        pd.setValueFormatter(new PercentFormatter());
        pd.setValueTextSize(11f);
        pd.setValueTextColor(Color.BLACK);

        mChart.setData(pd);

        mChart.highlightValues(null);

        mChart.invalidate();

    }
}
