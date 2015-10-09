package com.easygo.rbcdev.easygo.BusinessEnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.R;


public class BusinessHome extends Activity {

    private Activity mActivity = this;
    private TextView employeeEval;
    private TextView progressReport;
    private TextView quaterlySales;
    private TextView annualSales;
    private TextView leaveRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);
        initialize();
    }

    private void initialize(){

        employeeEval = (TextView)findViewById(R.id.employeeEval);
        employeeEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, EmployeeEvalMain.class));
            }
        });
        progressReport = (TextView)findViewById(R.id.progressReport);
        progressReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressReport.setBackgroundColor(getResources().getColor(R.color.blue_btn_pressed));
            }
        });
        quaterlySales = (TextView)findViewById(R.id.quaterlySales);
        quaterlySales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quaterlySales.setBackgroundColor(getResources().getColor(R.color.sobeys_green));
            }

        });
        annualSales = (TextView)findViewById(R.id.annualSales);
        annualSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annualSales.setBackgroundColor(getResources().getColor(R.color.blue_btn_pressed));
            }
        });
        annualSales.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                quaterlySales.setBackgroundColor(getResources().getColor(R.color.sobeys_green));

                return false;
            }
        });
        leaveRequest = (TextView)findViewById(R.id.leaveRequest);
        leaveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, LeaveRequest.class);
                startActivity(intent);
            }
        });


    }




}
