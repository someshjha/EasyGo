package com.easygo.rbcdev.easygo;

import android.app.Application;

// Make sure that you import the quality assurance application (see step 4)
import com.ibm.mqa.MQA;
import com.ibm.mqa.MQA.Mode;
import com.ibm.mqa.config.Configuration;

// Optionally import the quality assurance application's logging functions (see step 5)
import com.ibm.mqa.Log;

public class MyApplication extends Application {

    public static final String APP_KEY = "1ge8bd6d0b4bb3b24bcc66c8dbc29722b1b6c15eaeg0g1g55440e2e";

    @Override
    public void onCreate () {
        super.onCreate ();

        Configuration configuration = new Configuration.Builder(this)
                .withAPIKey(APP_KEY) // Provides the quality assurance application APP_KEY
                .withMode(Mode.QA) //Selects the quality assurance production mode.  This example is for preproduction mode,
                        //Use .withMode(Mode.MARKET) for production mode.
                .withReportOnShakeEnabled(true) // Enables shake report trigger
                .build();

        MQA.startNewSession(this, configuration);
    }
    }