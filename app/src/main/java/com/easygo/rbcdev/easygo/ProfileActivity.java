package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.easygo.rbcdev.easygo.Utility.AlertUtility;
import com.easygo.rbcdev.easygo.models.Constants;
import com.easygo.rbcdev.easygo.models.Customer;
import com.easygo.rbcdev.easygo.models.PaymentInformation;
import com.easygo.rbcdev.easygo.widgets.CustomField;
import com.easygo.rbcdev.easygo.widgets.Header;

import java.util.Random;

import static android.text.TextUtils.isEmpty;


public class ProfileActivity extends Activity {

    private Button paymentInfo;
    private LinearLayout paymentLayout;
    private LinearLayout mLayoutPasswordChange;
    private CustomField mNewPasswordLabel;
    private CustomField mConfirmPasswordLabel;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mAddress;
    private EditText mCity;
    private Spinner mProvince;
    private Spinner mPayMethod;
    private EditText mPassword;
    private EditText mNewPassword;
    private EditText mConfirmPassword;
    private EditText mNameOnCard;
    private EditText mCardNumber;
    private EditText mExpiry;
    private EditText mSecurityCode;
    private Header mHeader;
    private String loginType;
    private Button mBtnSavePersonalInfo;
    private Button mBtnSaveCardInfo;
    private Customer currentCustomer;
    private PaymentInformation currentCustomerPaymentInfo;
    private Intent i;
    private EditText mCustomerOldPasswordEditText;

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener submitInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (loginType.equals(Constants.SOBEYS_GUEST)) {
                registerCustomer();
            } else {
                updatePersonalInfo();
            }
        }
    };

    private void registerCustomer() {

        if(verifyPaymentInfo()) {
            Customer info = new Customer();
            info.setFirstName(mFirstName.getText().toString());
            info.setLastName(mLastName.getText().toString());
            info.setEmail(mEmail.getText().toString());
            info.setPhone(mPhone.getText().toString());
            info.setAddress(mAddress.getText().toString());
            info.setCity(mCity.getText().toString());
            info.setProvince(mProvince.getSelectedItemPosition());
            info.setPassword(mPassword.getText().toString());

            PaymentInformation paymentInfo = new PaymentInformation();
            paymentInfo.setCardType(mPayMethod.getSelectedItemPosition());
            paymentInfo.setCardExpiry(mExpiry.getText().toString());
            paymentInfo.setCardNumber(mCardNumber.getText().toString());
            paymentInfo.setCustomerEmail(info.getEmail());
            paymentInfo.setSecurityCode(mSecurityCode.getText().toString());
            paymentInfo.setNameOnCard(mNameOnCard.getText().toString());

            //generate random customer ID to simulate registration
            Random rand = new Random();
            int value = rand.nextInt(999999) + 1;

            info.setCustomerID(Integer.toString(value));

            //Do not use default spinner values
            if(Integer.valueOf(info.getProvince()) == null || info.getProvince() == 0) {
                AlertUtility.displayAlert("Please select a province",this);
            }
            else if(info.getEmail() == null || info.getEmail().isEmpty()) {
                AlertUtility.displayAlert("Email address can't be empty",this);
            }
            else if((info.getPassword() == null || info.getPassword().isEmpty()) || (mConfirmPassword.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty())) {
                AlertUtility.displayAlert("Password can't be empty. Please enter and confirm password",this);
            }
            else {
                paymentInfo.save();
                info.save();

                Customer retrieved = Customer.getSavedCustomerProfile(info.getEmail());

                if(retrieved != null) {
                    Toast.makeText(this,"Thank you for registering!",Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    AlertUtility.displayAlert("Failed to register",this);
                }
            }
        } else {
            AlertUtility.displayAlert("Complete payment information",this);
        }

    }

    private void updatePersonalInfo() {

        if(verifyPaymentInfo() && currentCustomer != null) {
            currentCustomer.setFirstName(mFirstName.getText().toString());
            currentCustomer.setLastName(mLastName.getText().toString());
            currentCustomer.setEmail(mEmail.getText().toString());
            currentCustomer.setPhone(mPhone.getText().toString());
            currentCustomer.setAddress(mAddress.getText().toString());
            currentCustomer.setCity(mCity.getText().toString());
            currentCustomer.setProvince(mProvince.getSelectedItemPosition());
            currentCustomer.save();

            PaymentInformation paymentInfo = new PaymentInformation();
            paymentInfo.setCardType(mPayMethod.getSelectedItemPosition());
            paymentInfo.setCardExpiry(mExpiry.getText().toString());
            paymentInfo.setCardNumber(mCardNumber.getText().toString());
            paymentInfo.setCustomerEmail(currentCustomer.getEmail());
            paymentInfo.setSecurityCode(mSecurityCode.getText().toString());
            paymentInfo.setNameOnCard(mNameOnCard.getText().toString());
            paymentInfo.save();
        }
        else {
            AlertUtility.displayAlert("Error Updating Profile",this);
        }
    }

    //For registration - force user to enter payment details before registering
    private boolean verifyPaymentInfo() {
        boolean isValid = true;

        if(isEmpty(mNameOnCard.getText().toString()) ||
                isEmpty(mCardNumber.getText().toString()) ||
                isEmpty(mExpiry.getText().toString()) ||
                isEmpty(mSecurityCode.getText().toString())) {
            isValid = false;
        } else if(mPayMethod.getSelectedItem().toString().equals("Select a Method of Pay")) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        i = getIntent();
        loginType = i.getStringExtra(Constants.LOGIN_TYPE);
        initializeUI();
        showHidePasswordFields();
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
            loadCustomerInfo();
            //hide profile info password fields and labels
            mLayoutPasswordChange.setVisibility(View.VISIBLE);
            mNewPasswordLabel.setVisibility(View.GONE);
            mConfirmPasswordLabel.setVisibility(View.GONE);
            mNewPassword.setVisibility(View.GONE);
            mConfirmPassword.setVisibility(View.GONE);
        }

    }

    private void loadCustomerInfo() {

        String customerEmail = i.getStringExtra(Constants.CUSTOMER_EMAIL);
        currentCustomer = Customer.getSavedCustomerProfile(customerEmail);
        currentCustomerPaymentInfo = PaymentInformation.getPaymentInformation(customerEmail);

        mFirstName.setText(currentCustomer.getFirstName());
        mLastName.setText(currentCustomer.getLastName());
        mEmail.setText(currentCustomer.getEmail());
        mPhone.setText(currentCustomer.getPhone());
        mAddress.setText(currentCustomer.getAddress());
        mCity.setText(currentCustomer.getCity());
        mProvince.setSelection(currentCustomer.getProvince());
        mCustomerOldPasswordEditText.setText(currentCustomer.getPassword());

        mPayMethod.setSelection(currentCustomerPaymentInfo.getCardType());
        mNameOnCard.setText(currentCustomerPaymentInfo.getNameOnCard());
        mCardNumber.setText(currentCustomerPaymentInfo.getCardNumber());
        mExpiry.setText(currentCustomerPaymentInfo.getCardExpiry());
        mSecurityCode.setText(currentCustomerPaymentInfo.getSecurityCode());

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

        mBtnSavePersonalInfo = (Button) findViewById(R.id.btnSavePersonalInfo);
        mBtnSavePersonalInfo.setOnClickListener(submitInfoListener);

        mFirstName = (EditText) findViewById(R.id.firstName);
        mLastName = (EditText) findViewById(R.id.lastName);
        mEmail = (EditText) findViewById(R.id.email);
        mPhone = (EditText) findViewById(R.id.customerPhoneEditText);
        mAddress = (EditText) findViewById(R.id.address);
        mCity = (EditText) findViewById(R.id.city);
        mProvince = (Spinner) findViewById(R.id.province);
        mPassword = (EditText) findViewById(R.id.password);

        mPayMethod = (Spinner) findViewById(R.id.payMethod);
        mNameOnCard = (EditText) findViewById(R.id.customerNameOnCardEditText);
        mCardNumber = (EditText) findViewById(R.id.customerCardNumberEditText);
        mExpiry = (EditText) findViewById(R.id.customerExpireEditText);
        mSecurityCode = (EditText) findViewById(R.id.customerCardSecurityEditText);

        mCustomerOldPasswordEditText = (EditText) findViewById(R.id.customerOldPassEditText);
        mBtnSaveCardInfo = (Button) findViewById(R.id.btnSaveCardInfo);
        mBtnSaveCardInfo.setOnClickListener(submitInfoListener);
    }

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