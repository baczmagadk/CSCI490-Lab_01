package com.example.simpletimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
        countDownTimer = new MyCountDownTimer(startTime, interval, timeElapsed);
        text.setText(text.getText() + String.valueOf(startTime));
    }

    @Override
    public void onClick(View v) {
        if(!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText("Start");
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText("Reset");
        }
    }

    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval, long millisUntilFinished) {
            super(millisInFuture, countDownInterval);
            this.millisUntilFinished = millisUntilFinished;
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        private long millisUntilFinished = startTime-timeElapsed;

        public void onFinish() {
            text.setText("Time's up!");
            timeElapsedView.setText("Time Elapsed: " + String.valueOf(startTime));
        }

        public void onTick() {
            text.setText("Time remain: "+millisUntilFinished);
            timeElapsed =startTime -millisUntilFinished;
            timeElapsedView.setText("Time Elapsed: "+String.valueOf(timeElapsed));
        }
    }
}
