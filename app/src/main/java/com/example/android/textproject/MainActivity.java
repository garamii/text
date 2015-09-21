
package com.example.android.textproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ProgressBar mProgressBar;
    private ProgressBar mProgressBar2;
    private TextView mNumberTextView;
    private TextView mNumberTextView2;
    private int i = 0;
    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        mNumberTextView = (TextView) findViewById(R.id.textView);
        mNumberTextView2 = (TextView) findViewById(R.id.textView2);

    }

    private void updateThread() {

        if (j < 100) {
            if (i >= 100) {
                i = 0;
                j= j + 10;
                mProgressBar2.setProgress(j);
                mNumberTextView2.setText(String.valueOf(j));
            } else {
                i++;
                mProgressBar.setProgress(i);
                mNumberTextView.setText(String.valueOf(i));
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateThread();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Thread myThread = new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(10);
                    } catch (Throwable t) {
                    }
                }
            }
        });

        myThread.start();
    }

}
