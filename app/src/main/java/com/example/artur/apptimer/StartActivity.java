package com.example.artur.apptimer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    private int mRate = 1000;
    private int startDelay = 2000;
    private long currentTime;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void onStart(){
        super.onStart();
        newTimer(startDelay);
        timer.start();
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        startDelay = (int)savedInstanceState.getLong("currentTime");
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("currentTime", currentTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onStop(){
        timer.cancel();
        super.onStop();
    }

    public void newTimer(long newTime){
        timer = new CountDownTimer(newTime, mRate) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
}
