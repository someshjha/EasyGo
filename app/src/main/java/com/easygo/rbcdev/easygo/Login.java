package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.BusinessEnd.BusinessHome;
import com.easygo.rbcdev.easygo.Utility.AlertUtility;
import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Customer;


public class Login extends Activity {

    private Activity mActivity = this;
    private TextView mTxtTitle;
    private String mLoginType;
    private TextView mTxtRegister;
    private EditText mTxtUsername;
    private EditText mTxtPassword;
    private Button mSignIn;

    private TextView mTxtForgotPassword;

    private TextView mForgotPassword;

    private View.OnClickListener mOnClickSignIn = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            doSignIn();
        }

    };

    private View.OnClickListener mOnClickRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doRegistration();
        }
    };

    // Forgot Password
    private View.OnClickListener mOnClickGetPassword = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goGetNewPassword();
        }
    };

    private void goGetNewPassword() {
        Intent i = new Intent(this,ForgotPassword.class);
        startActivity(i);
    }

    // Forgot Password

    private void doRegistration() {
        Intent i = new Intent(this,ProfileActivity.class);
        i.putExtra(Constants.LOGIN_TYPE, Constants.SOBEYS_GUEST);
        startActivity(i);
    }

    private void doSignIn() {
        Intent i;
        String loginEmail = mTxtUsername.getText().toString();
        String loginPassword = mTxtPassword.getText().toString();

        if(mLoginType.equals(Constants.SOBEYS_LOGIN_BUSINESS.toString())){
            i = new Intent(this,BusinessHome.class);
            startActivity(i);
        }
        else {
            i = new Intent(this,ShoppingHome.class);
        }

        if(loginEmail.isEmpty() || loginPassword.isEmpty()) {
            AlertUtility.displayAlert("Please enter email and password",this);
        }
        else {
            Customer loggedIn = Customer.getSavedCustomer(loginEmail,loginPassword);
            if(loggedIn != null) {
                i.putExtra(Constants.LOGIN_TYPE,Constants.SOBEYS_CUSTOMER);
                i.putExtra(Constants.CUSTOMER_EMAIL,loggedIn.getEmail());
                startActivity(i);
            } else {
                AlertUtility.displayAlert("Customer login failed",this);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();
        mLoginType = i.getStringExtra("loginType");
        initializeUI();
    }

    private void initializeUI() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main);

        if(mLoginType.equals(Constants.SOBEYS_LOGIN_BUSINESS.toString())){
            layout.setBackgroundResource(R.drawable.business_bg);
        }
        else {
            layout.setBackgroundResource(R.drawable.produce_bg);
        }

        mForgotPassword = (TextView)findViewById(R.id.txtLoginForgotPassword);
        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ForgotPassword.class));
            }
        });
        mTxtTitle = (TextView) findViewById(R.id.loginTitle);
        mTxtRegister = (TextView) findViewById(R.id.txtLoginRegister);
        mTxtRegister.setOnClickListener(mOnClickRegister);

        mTxtForgotPassword = (TextView) findViewById(R.id.txtLoginForgotPassword);
        mTxtForgotPassword.setOnClickListener(mOnClickGetPassword);

        mSignIn = (Button) findViewById(R.id.btnSignIn);
        mSignIn.setOnClickListener(mOnClickSignIn);

        mTxtUsername = (EditText) findViewById(R.id.txtUsername);
        mTxtPassword = (EditText) findViewById(R.id.txtPassword);

        checkLoginType();
    }

    private void checkLoginType() {
        if(mLoginType.equals(Constants.SOBEYS_LOGIN_BUSINESS.toString())){
            mTxtTitle.setText(getResources().getText(R.string.btnBusiness));
            mTxtRegister.setVisibility(View.GONE);
        }
        else {
            mTxtTitle.setText(getResources().getText(R.string.btnCustomer));
        }

    }



}
