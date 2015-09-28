package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.models.Constants;


public class Login extends Activity {

    private TextView mTxtTitle;
    private String mLoginType;
    private TextView mTxtRegister;
    private Button mSignIn;

    private View.OnClickListener mOnClickSignIn = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            doSignIn();
        }

    };

    private void doSignIn() {
        Intent i;

        if(mLoginType.equals(Constants.SOBEYS_LOGIN_BUSINESS.toString())){
            i = new Intent(this,BusinessHome.class);
        }
        else {
            i = new Intent(this,ShoppingHome.class);
        }

        startActivity(i);
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
        mTxtTitle = (TextView) findViewById(R.id.loginTitle);
        mTxtRegister = (TextView) findViewById(R.id.txtLoginRegister);
        mSignIn = (Button) findViewById(R.id.btnSignIn);
        mSignIn.setOnClickListener(mOnClickSignIn);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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
