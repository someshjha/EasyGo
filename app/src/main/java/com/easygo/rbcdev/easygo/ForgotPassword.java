package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.easygo.rbcdev.easygo.widgets.Header;

public class ForgotPassword extends Activity {

    private Button btnGetPassword;
    private Header mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initialize();


    }

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    private View.OnClickListener forgotPasswordEmail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        setPasswordEmail();

        finish();

        }
    };

    private void setPasswordEmail(){

        Toast.makeText(getApplicationContext(),
                "The link to reset your password has been send to the account " +
                 "associated email address.", Toast.LENGTH_LONG).show();

    }


    private void initialize() {

        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnLeftListener(backListener);

        btnGetPassword = (Button) findViewById(R.id.btnSendMailForgotPassword);
        btnGetPassword.setOnClickListener(forgotPasswordEmail);

    }


}
