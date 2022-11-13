package de.fhe.pmeplayground.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.core.MyApplication;
import de.fhe.pmeplayground.core.SettingsHandler;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        MyApplication application = (MyApplication)getApplication();
        SettingsHandler settingsHandler = application.getSettingsHandler();

        View.OnClickListener listener = v -> {
            int counterValue = settingsHandler.getCounterValue();

            if(v.getId() == R.id.activity_second_button_inc)
                counterValue++;
            else if(v.getId() == R.id.activity_second_button_dec)
                counterValue--;

            settingsHandler.saveCounter(counterValue);
        };

        Button incButton = findViewById(R.id.activity_second_button_inc);
        incButton.setOnClickListener(listener);

        Button decButton = findViewById(R.id.activity_second_button_dec);
        decButton.setOnClickListener(listener);
    }
}