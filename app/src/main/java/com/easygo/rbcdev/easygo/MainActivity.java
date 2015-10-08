package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.easygo.rbcdev.easygo.models.Constants;

public class MainActivity extends Activity {
    private Button b;
    private Button c;

    private View.OnClickListener businessListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToLogin(Constants.SOBEYS_LOGIN_BUSINESS.toString());
        }
    };

    private View.OnClickListener customerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToLogin(Constants.SOBEYS_LOGIN_CUSTOMER.toString());
        }
    };

    private void goToLogin(String pressed) {
        Intent i = new Intent(this,Login.class);
        i.putExtra("loginType", pressed);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.btnBusiness);
        c = (Button) findViewById(R.id.btnCustomer);
        b.setOnClickListener(businessListener);
        c.setOnClickListener(customerListener);
    }



}
