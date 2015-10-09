package com.easygo.rbcdev.easygo.BusinessEnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.R;


public class BusinessHome extends Activity {

    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);
        initialize();
    }

    private void initialize(){

        TextView employeeEval = (TextView)findViewById(R.id.employeeEval);
        employeeEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, EmployeeEvalMain.class));
            }
        });
        TextView progressReport = (TextView)findViewById(R.id.progressReport);
        progressReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ProgressReport.class);
                startActivity(intent);            }
        });
        TextView quaterlySales = (TextView)findViewById(R.id.quaterlySales);
        quaterlySales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, QuaterlySales.class);
                startActivity(intent);
            }

        });
        TextView annualSales = (TextView)findViewById(R.id.annualSales);
        annualSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, AnnualSales.class);
                startActivity(intent);
            }
        });
        TextView leaveRequest = (TextView)findViewById(R.id.leaveRequest);
        leaveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, LeaveRequest.class);
                startActivity(intent);
            }
        });


    }




}
