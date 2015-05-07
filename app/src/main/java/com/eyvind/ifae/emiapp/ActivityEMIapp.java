package com.eyvind.ifae.emiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityEMIapp extends Activity {
    Timer mTimer;
    TimerTask mTimerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emiapp);
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Intent main = new Intent(ActivityEMIapp.this,MainActivity.class);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                startActivity(main);
                finish();
            }
        };
        mTimer.schedule(mTimerTask,GLOBALS.TIME_END_SPLASH_SCREEN);
    }
}
