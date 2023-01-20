package de.fhe.pmeplayground.core;
import android.app.Application;
import android.util.Log;

/**
 * class to execute on Create
 */
public class MyApplication extends Application {

    private static final String LOG_TAG = "Scribble Bullet";

    private SettingsHandler settingsHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        this.settingsHandler = new SettingsHandler(this);

        Log.i( LOG_TAG, "On Create finished.");
    }

   public SettingsHandler getSettingsHandler() {
       return settingsHandler;
   }



}
