package com.example.artur.apptimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;
    private boolean mBool;
    private CountDownTimer timer;
    private int time;
    private long currentTime;
    private long t0;
    private String strStart;
    private String strStop;
    private int maxTime = 1002;
    private int mRate = 1000;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.textView);
        mButton = (Button)findViewById(R.id.button);
        strStart = getString(R.string.start_button);
        strStop = getString(R.string.stop_button);
        mButton.setText(strStart);
        time = maxTime*mRate;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            t0 = savedInstanceState.getLong("currentTime");
            mBool = savedInstanceState.getBoolean("mBool");
            if(mBool) {
                mButton.setText(strStop);
                newTimer(time - t0);
                timer.start();
            }
        }
        else{
            t0 = time;
            mBool = false;
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putBoolean("mBool", mBool);
        savedInstanceState.putLong("currentTime", currentTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onStop(){
        if(mBool) {
            timer.cancel();
            mButton.setText(strStart);
            mBool = false;
            mTextView.setText("");
        }
            super.onStop();
    }

    public void onButtonClick(View view){
        if(mBool) {
            timer.cancel();
            mButton.setText(strStart);
            mBool = false;
            mTextView.setText("");
        }
        else{
            t0 = 0;
            newTimer(time);
            timer.start();
            mButton.setText(strStop);
            mBool = true;
        }
    }

    public void newTimer(long newTime){
        timer = new CountDownTimer(newTime, mRate) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime = time - millisUntilFinished;
                writeNum((short)((time - millisUntilFinished)/mRate));
            }

            @Override
            public void onFinish() {
                mTextView.setText("");
                mButton.setText(strStart);
                mBool = false;
            }
        };
    }

    public void writeNum(short n){
        String str = "";
        short n3, n2, n1;
        n1 = (short)(n%10);
        n2 = (short)((n-n1)%100);
        n3 = (short)((n-n2-n1)%1000);
        if(n2 == 10){
            n1 += n2;
            n2 = 0;
        }
        if(n == 1000) str = "Тысяча";
        else {
            switch (n3) {
                case 100: str = "Сто"; break;
                case 200: str = "Двести"; break;
                case 300: str = "Триста"; break;
                case 400: str = "Четыреста"; break;
                case 500: str = "Пятьсот"; break;
                case 600: str = "Шестьсот"; break;
                case 700: str = "Семьсот"; break;
                case 800: str = "Восемьсот"; break;
                case 900: str = "Девятьсот"; break;
            }
            switch (n2) {
                case 20: str += " двадцать"; break;
                case 30: str += " тридцать"; break;
                case 40: str += " сорок"; break;
                case 50: str += " пятьдесят"; break;
                case 60: str += " шестьдесят";break;
                case 70: str += " семдесят"; break;
                case 80: str += " восемдесят"; break;
                case 90: str += " девяносто"; break;
            }
            switch (n1) {
                case 1: str += " один"; break;
                case 2: str += " два"; break;
                case 3: str += " три"; break;
                case 4: str += " четыре"; break;
                case 5: str += " пять";break;
                case 6: str += " шесть"; break;
                case 7: str += " семь"; break;
                case 8: str += " восемь"; break;
                case 9: str += " девять"; break;
                case 10: str += " десять"; break;
                case 11: str += " одинадцать"; break;
                case 12: str += " двенадцать"; break;
                case 13: str += " тринадцать";break;
                case 14: str += " четырнадцать"; break;
                case 15: str += " пятнадцать"; break;
                case 16: str += " шестнадцать"; break;
                case 17: str += " семнадцать"; break;
                case 18: str += " восемнадцать"; break;
                case 19: str += " девятнадцать"; break;
            }
        }
        mTextView.setText(str);
    }
}
