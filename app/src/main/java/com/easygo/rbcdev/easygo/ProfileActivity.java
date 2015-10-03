package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class ProfileActivity extends Activity {

    private Button paymentInfo;
    private LinearLayout paymentLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        initialize();
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
