package de.fhe.pmeplayground.core;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsHandler {

    private static final String STORE_NAME = "my_store";
    private static final String STORE_COUNTER_KEY = "counter";
    private static final int COUNTER_DEFAULT_VALUE = 0;

    private final MyApplication application;

    public SettingsHandler(MyApplication application) {
        this.application = application;
    }

    private SharedPreferences getPreferences() {
        return this.application.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
    }

    public void saveCounter(int value) {
        this.getPreferences()
                .edit()
                .putInt(STORE_COUNTER_KEY, value)
                .apply();
    }

    public int getCounterValue() {
        return this.getPreferences()
                .getInt(STORE_COUNTER_KEY, COUNTER_DEFAULT_VALUE);
    }
}
