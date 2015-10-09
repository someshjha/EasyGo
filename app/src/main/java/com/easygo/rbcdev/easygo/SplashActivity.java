package com.easygo.rbcdev.easygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prepUI();
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


//    //MQA
//    Configuration configuration = new Configuration.Builder(this)
//            .withAPIKey(Constants.APP_KEY) // Provides the quality assurance application APP_KEY
//            .withMode(MQA.Mode.QA) //Selects the quality assurance production mode.  This example is for preproduction mode,
//                    //Use .withMode(Mode.MARKET) for production mode.
//            .withReportOnShakeEnabled(true) // Enables shake report trigger
//            .build();
//
//    MQA.startNewSession(this, configuration);

    private void prepUI() {
        ImageView logo = (ImageView) findViewById(R.id.logo);
        Animation heart_pulse = AnimationUtils.loadAnimation(this,
                R.anim.pulse);

        logo.startAnimation(heart_pulse);
    }
}
