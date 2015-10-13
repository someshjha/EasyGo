package com.easygo.rbcdev.easygo.BusinessEnd;

import android.app.Activity;
import android.os.Bundle;

import com.easygo.rbcdev.easygo.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class QuaterlySales extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quaterly_sales);


        BarChart chart = (BarChart) findViewById(R.id.barChartAnnualSales);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("Quaterly Sales (value in millions)");
        chart.setDrawBarShadow(true);
        chart.animateXY(3500, 3500);
        chart.invalidate();



    }


    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

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

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Q1
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(95.000f, 1); // Q2
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(90.000f, 2); // Q3
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(73.000f, 3); // Q4
        valueSet1.add(v1e4);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(130.000f, 0); // Q1
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(100.000f, 1); // Q2
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(93.000f, 2); // Q3
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(79.000f, 3); // Q4
        valueSet2.add(v2e4);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "2013");
        barDataSet1.setColors(colors);
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "2014");
        barDataSet2.setColors(colors);



        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Q1");
        xAxis.add("Q2");
        xAxis.add("Q3");
        xAxis.add("Q4");
        return xAxis;
    }

}
