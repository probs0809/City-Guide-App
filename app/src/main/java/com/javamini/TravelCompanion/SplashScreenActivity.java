package com.javamini.TravelCompanion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchMainActivity();
                finish();
            }
        }, 4000);

//        //Create a runnable to launch the main activity method
//        final Runnable launchMainActivity = new Runnable() {
//            @Override
//            public void run() {
//                launchMainActivity();
//                finish(); // terminates the splash screen process so that a back button press
//                        // the app exit and the splash screen do not display again
//            }
//        };
//        // Create a handler to delay the runnable process for 5secs
//        Handler handler = new Handler();
//        handler.postDelayed(launchMainActivity, 5000);


    }

    public void launchMainActivity() {
        Intent launchMain = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(launchMain);
    }
}
