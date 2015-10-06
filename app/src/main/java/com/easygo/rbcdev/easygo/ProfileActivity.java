package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.widgets.CustomField;
import com.easygo.rbcdev.easygo.widgets.Header;


public class ProfileActivity extends Activity {

    private Button paymentInfo;
    private LinearLayout paymentLayout;
    private LinearLayout mLayoutPasswordChange;
    private CustomField mNewPasswordLabel;
    private CustomField mConfirmPasswordLabel;
    private EditText mNewPassword;
    private EditText mConfirmPassword;
    private Header mHeader;
    private String loginType;

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i = getIntent();
        loginType = i.getStringExtra(Constants.LOGIN_TYPE);

        initializeUI();
        showHidePasswordFields();
//        initialize();
    }

    private void showHidePasswordFields() {
        if(loginType.equals(Constants.SOBEYS_GUEST)){

            mLayoutPasswordChange.setVisibility(View.GONE);

            //Make profile info password fields and labels visible
            mNewPasswordLabel.setVisibility(View.VISIBLE);

            mConfirmPassword.setVisibility(View.VISIBLE);

            mNewPassword.setVisibility(View.VISIBLE);

            mConfirmPassword.setVisibility(View.VISIBLE);


        }
        else {
            //hide profile info password fields and labels
            mLayoutPasswordChange.setVisibility(View.VISIBLE);

            mNewPasswordLabel.setVisibility(View.GONE);

            mConfirmPasswordLabel.setVisibility(View.GONE);

            mNewPassword.setVisibility(View.GONE);

            mConfirmPassword.setVisibility(View.GONE);
        }

    }

    private void initializeUI() {
        mHeader = (Header) findViewById(R.id.header);
        mHeader.setBtnLeftListener(backListener);
        mLayoutPasswordChange = (LinearLayout) findViewById(R.id.ChangePasswordInformation);
        
        //initialize new password fields here
        mNewPasswordLabel = (CustomField) findViewById(R.id.profileNewPass);

        mConfirmPasswordLabel = (CustomField) findViewById(R.id.profileNewPassConfirm);

        mNewPassword = (EditText) findViewById(R.id.password);

        mConfirmPassword = (EditText) findViewById(R.id.passwordConfirm);


    }

//    private void initialize(){
//
//        paymentInfo = (Button)findViewById(R.id.btnPaymentInfo);
//        paymentLayout = (LinearLayout)findViewById(R.id.paymentInfo);
//        paymentInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                paymentLayout.setVisibility(View.VISIBLE);
//            }
//        });
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
