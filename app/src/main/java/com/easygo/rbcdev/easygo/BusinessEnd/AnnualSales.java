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

public class AnnualSales extends Activity {

    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual_sales);

        BarChart chart = (BarChart) findViewById(R.id.barChartAnnualSales);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("Annual Sales (value in millions)");
        chart.setDrawBarShadow(true);
        chart.animateXY(2000, 2000);
        chart.invalidate();



    }


    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(12435.66f, 0); // 2005
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(13063.87f, 1); // 2006
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(13336.66f, 2); // 2007
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(14065.00f, 3); // 2008
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(15015.00f, 4); // 2009
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(15675.00f, 5); // 2010
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(16029.00f, 6); // 2011
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(16249.000f, 7); // 2012
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(17400.000f, 8); // 2013
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(20993.000f, 9); // 2014
        valueSet1.add(v1e10);
        BarEntry v1e11 = new BarEntry(23928.000f, 10); // 2015
        valueSet1.add(v1e11);




        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Revenue");
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
        barDataSet1.setColors(colors);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("2005");
        xAxis.add("2006");
        xAxis.add("2007");
        xAxis.add("2008");
        xAxis.add("2009");
        xAxis.add("2010");
        xAxis.add("2011");
        xAxis.add("2012");
        xAxis.add("2013");
        xAxis.add("2014");
        xAxis.add("2015");

        return xAxis;
    }

}
