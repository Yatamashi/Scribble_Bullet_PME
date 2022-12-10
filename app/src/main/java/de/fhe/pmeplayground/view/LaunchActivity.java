package de.fhe.pmeplayground.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Random;

import de.fhe.pmeplayground.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ProgressBar progressBar = findViewById(R.id.activity_launch_progress_bar);
        progressBar.setMax(100);

        MyHandler myHandler = new MyHandler(this, progressBar);
        myHandler.start();
    }

    class MyHandler extends Handler {

        private final Context context;

        private int currentProgress;
        private final ProgressBar progressBar;
        private final Random progressCalculator;

        public MyHandler(Context context, ProgressBar progressBar) {
            super(Objects.requireNonNull(Looper.getMainLooper()));
            this.context = context;
            this.progressBar = progressBar;
            this.progressCalculator = new Random();
        }

        public void start() {
            this.postDelayed( () -> {
                if( currentProgress < 100 ) {
                    currentProgress += progressCalculator.nextInt(15);
                    progressBar.setProgress(currentProgress);
                    start();
                }
                else
                {
                    Intent i = new Intent(context, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 100 + progressCalculator.nextInt(200));
        }


    }


}