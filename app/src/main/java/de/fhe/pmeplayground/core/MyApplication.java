package de.fhe.pmeplayground.core;

import android.app.Application;
import android.util.Log;

import java.util.List;
import java.util.Random;

import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.storage.Repository;

public class MyApplication extends Application {

    private static final String LOG_TAG = "Scribble Bullet";

    private SettingsHandler settingsHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        this.settingsHandler = new SettingsHandler(this);

        //testDatabase();

        Log.i( LOG_TAG, "On Create finished.");
    }

   public SettingsHandler getSettingsHandler() {
       return settingsHandler;
   }



}
