package de.fhe.pmeplayground.core;
import android.app.Application;
import android.util.Log;

/**
 * class to execute onCreate
 */
public class MyApplication extends Application {

    private static final String LOG_TAG = "Scribble Bullet";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i( LOG_TAG, "On Create finished.");
    }
}
